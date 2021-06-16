package com.example.readerzeropointone;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class AddedLinkDB extends RealmObject {

    @PrimaryKey
    @Required
    private String rssLink;
    private String category;

    public AddedLinkDB() {}

    public AddedLinkDB(String rssLink, String category) {
        this.rssLink = rssLink;
        this.category = category;
    }

    public String getRssLink() { return this.rssLink;}
    public void setRssLink(String rssLink) { this.rssLink = rssLink;}

    public String getCategory() { return this.category; }
    public void setCategory(String category) { this.category = category;}

}
