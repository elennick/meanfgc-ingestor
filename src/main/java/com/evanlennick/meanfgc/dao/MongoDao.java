package com.evanlennick.meanfgc.dao;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;

import java.net.UnknownHostException;

abstract public class MongoDao {

    DB db;

    Jongo jongo;

    public MongoDao() {
        try {
            db = new MongoClient().getDB("meanfgc-dev");
            jongo = new Jongo(db);
        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        }
    }
}
