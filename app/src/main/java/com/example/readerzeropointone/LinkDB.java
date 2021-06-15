package com.example.readerzeropointone;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class LinkDB extends RealmObject {

    @PrimaryKey
    @Required
    private String rssLink;
    @Required
    private String domain;
    private String category;
    private RealmList<String> tags;

    public LinkDB() {}

    public LinkDB(String rssLink) {
        this.rssLink = rssLink;
    }

    public String getRssLink() { return this.rssLink;}
    public void setRssLink(String rssLink) { this.rssLink = rssLink;}
    public String getDomain() { return this.domain; }
    public void setDomain(String domain) { this.domain = domain; }
    public RealmList<String> getTags() { return tags; }
    public void setTags(RealmList<String> tags) { this.tags = tags; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
