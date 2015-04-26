package com.evanlennick.meanfgc.dao.models;

import org.jongo.marshall.jackson.oid.ObjectId;

abstract public class MongoModel {

    @ObjectId
    protected String _id;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

}
