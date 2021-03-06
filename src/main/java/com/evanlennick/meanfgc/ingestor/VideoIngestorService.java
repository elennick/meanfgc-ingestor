package com.evanlennick.meanfgc.ingestor;

import com.evanlennick.meanfgc.dao.ChannelsDao;
import com.evanlennick.meanfgc.dao.VideosDao;
import com.evanlennick.meanfgc.dao.models.Channel;
import com.evanlennick.meanfgc.dao.models.Video;
import com.evanlennick.meanfgc.ingestor.models.VideoPage;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Singleton
public class VideoIngestorService {

    @Inject
    private VideosDao videoDao;

    @Inject
    private ChannelsDao channelsDao;

    private SimpleDateFormat rfc3339sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    /**
     * Execute ingestion on a single VideoSource.
     */
    public void ingestSource(VideoSource source) {
        String channelId = source.getChannelId();
        String channelName = null;

        try {
            Optional<String> pageTokenOptional = Optional.empty();

            do {
                String pageToken = pageTokenOptional.orElse(null);

                VideoPage videoPage = this.getVideoPageForChannel(channelId, pageToken);
                if(StringUtils.isEmpty(channelName)) {
                    channelName = videoPage.getChannelTitle();
                }

                List<Video> parsedVideos = new ArrayList<>();
                for (Video unparsedVideo : videoPage.getVideos()) {
                    try {
                        Video parsedVideo = source.parseVideo(unparsedVideo);
                        parsedVideos.add(parsedVideo);
                    } catch(Exception e) {
                        System.out.println("error parsing video " + unparsedVideo.getVideoId() + " -> " + e.getMessage());
                        parsedVideos.add(unparsedVideo);
                    }
                }
                videoDao.saveVideos(parsedVideos);
                pageTokenOptional = videoPage.getNextPageToken();

            } while(pageTokenOptional.isPresent());

            Channel channel = new Channel();
            channel.setChannelId(channelId);
            channel.setName(channelName);
            channel.setLastUpdated(new Date());
            channelsDao.save(channel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses through the json for one "page" worth of items returned from youtube.
     */
    public VideoPage getVideoPageForChannel(String channelId, String pageToken) throws IOException {
        VideoPage videoPage = new VideoPage();
        videoPage.setChannelId(channelId);
        videoPage.setVideos(new ArrayList<>());

        //get the search response
        JSONObject jsonObject = this.getJsonForVideoPage(channelId, pageToken);

        //set the next page token if there is one
        Optional<String> nextPageToken;
        if(jsonObject.has("nextPageToken")) {
            nextPageToken = Optional.of(jsonObject.get("nextPageToken").toString());
        } else {
            nextPageToken = Optional.empty();
        }

        videoPage.setNextPageToken(nextPageToken);

        //parse out video data
        if(jsonObject.has("items")) {
            JSONArray itemsJsonArray = jsonObject.getJSONArray("items");
            for (int i = 0, size = itemsJsonArray.length(); i < size; i++) {
                JSONObject itemJson = itemsJsonArray.getJSONObject(i);
                JSONObject itemIdJson = itemJson.getJSONObject("id");
                JSONObject itemSnippetJson = itemJson.getJSONObject("snippet");

                Video video = new Video();

                video.setVideoId(itemIdJson.getString("videoId"));
                video.setDescription(itemSnippetJson.getString("description"));
                video.setTitle(itemSnippetJson.getString("title"));
                video.setPostedBy(itemSnippetJson.getString("channelTitle"));

                if(null == videoPage.getChannelTitle()) {
                    videoPage.setChannelTitle(itemSnippetJson.getString("channelTitle"));
                }

                try {
                    video.setPostDate(rfc3339sdf.parse(itemSnippetJson.getString("publishedAt")));
                } catch(ParseException pe) {
                    System.out.println("error parsing date -> " + pe.getMessage());
                    video.setPostDate(new Date());
                }

                videoPage.getVideos().add(video);
            }
        }

        return videoPage;
    }

    /**
     * Make an api call to the youtube api and return the json.
     */
    private JSONObject getJsonForVideoPage(String channelId, String pageToken) throws IOException {
        CloseableHttpResponse response = null;
        JSONObject jsonObject;

        try {
            CloseableHttpClient httpClient = HttpClients.custom().
                    setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

            String youtubeKey = "AIzaSyAhvqkZmykgopZc990N7NQvWGUkNEDlHes"; //todo move this into config file and don't check into git
            String url = "https://www.googleapis.com/youtube/v3/search?"
                    + "channelId=" + channelId
                    + "&maxResults=50"
                    + "&type=video"
                    + "&videoEmbeddable=true"
                    + "&order=date"
                    + "&part=snippet"
                    + "&key=" + youtubeKey;
//            + "&publishedAfter=" + publishedAfter;

            if (StringUtils.isNotEmpty(pageToken)) {
                url += "&pageToken=" + pageToken;
            }

            HttpGet httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            jsonObject = new JSONObject(result.toString());

            EntityUtils.consume(entity);

            return jsonObject;
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
