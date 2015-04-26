package com.evanlennick.meanfgc.dao;

import com.evanlennick.meanfgc.dao.models.MongoModel;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.net.UnknownHostException;

abstract public class MongoDao<T extends MongoModel> {

    private Jongo jongo;

    private MongoCollection collection;

    public MongoDao(String collectionName) {
        try {
            DB db = new MongoClient().getDB("meanfgc-dev"); //todo move this into a config file or something
            jongo = new Jongo(db);
        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        }

        collection = jongo.getCollection(collectionName); //todo get collection name from MongoModel instance instead of through the dao constructor like this
    }

    protected void saveModel(T model) {
        System.out.println("saving model = " + model);
        collection.save(model);
    }

    protected MongoCollection getCollection() {
        return collection;
    }
}
