package com.example.readerzeropointone;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class LinkDB extends RealmObject {

    @PrimaryKey
    @Required
    private String rssLink;

    public LinkDB() {}

    public LinkDB(String rssLink) {
        this.rssLink = rssLink;
    }

    public String getRssLink() { return this.rssLink;}
    public void setRssLink(String rssLink) { this.rssLink = rssLink;}


}
