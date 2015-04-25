package com.evanlennick.meanfgc.ingestor;

import com.evanlennick.meanfgc.dao.VideoDao;
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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

@Singleton
public class VideoIngestor {

    @Inject
    private VideoDao dao;

    public void ingestSource(VideoSource source) {
        String channelId = source.getChannelId();

        try {
            Map<String, Object> data = this.getVideoDataForChannel(channelId, null);
//            List<Video> videos = source.parseVideoList(videos);
//            dao.saveVideos(videos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> getVideoDataForChannel(String channelId, String nextPageToken) throws IOException {
        Map<String, Object> jsonMap = null;
        CloseableHttpResponse response = null;

        try {
            CloseableHttpClient httpClient = HttpClients.custom().
                    setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

            String youtubeKey = "AIzaSyAhvqkZmykgopZc990N7NQvWGUkNEDlHes"; //todo move this into config file and don't check into git
            String url = "https://www.googleapis.com/youtube/v3/search?"
                    + "channelId=" + channelId
                    + "&maxResults=50"
                    + "&part=snippet" //todo update this to only return the fields we actually need
                    + "&key=" + youtubeKey;
//            + "&publishedAfter=" + publishedAfter;

            if (StringUtils.isNotEmpty(nextPageToken)) {
                url += "&nextPageToken=" + nextPageToken;
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

            JSONObject o = new JSONObject(result.toString());
            System.out.println("o = " + o);

            EntityUtils.consume(entity);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

        return jsonMap;
    }

    public void getChannelIdByName(String channelName) {
        //        CloseableHttpResponse response1 = null;
//        try {
//            CloseableHttpClient httpClient = HttpClients.custom().
//                    setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
//
//            HttpGet httpGet1 = new HttpGet("https://www.googleapis.com/youtube/v3/channels?part=id&forUsername=YogaFlame24&key=AIzaSyAhvqkZmykgopZc990N7NQvWGUkNEDlHes");
//            response1 = httpClient.execute(httpGet1);
//
//            System.out.println(response1.getStatusLine());
//
//            HttpEntity entity1 = response1.getEntity();
//
//            ObjectMapper mapper = new ObjectMapper();
//            Map<String, Object> jsonMap = mapper.readValue(entity1.getContent(), Map.class);
//            System.out.println("jsonMap = " + jsonMap);
//
//            EntityUtils.consume(entity1);
//        } catch(Exception e) {
//            e.printStackTrace();
//        } finally {
//            if(null != response1) {
//                try {
//                    response1.close();
//                } catch(IOException ioe) {
//                    ioe.printStackTrace();
//                }
//            }
//        }
    }
}
