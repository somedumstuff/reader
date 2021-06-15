package com.example.readerzeropointone;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class CardDB extends RealmObject {

    @PrimaryKey
    @Required
    private String headline;
    @Required
    private String subtitle;
    @Required
    private String time;
    @Required
    private String author;
    private String siteLogoImageLink;
    private String articleImageLink;
    private String articleLink;

    public CardDB() {}

    public CardDB(String headline) { this.headline = headline;}

    public void setHeadline(String headline) { this.headline = headline;}
    public String getHeadline() { return headline;}

    public void setSubtitle(String subtitle) { this.subtitle = subtitle;}
    public String getSubtitle() { return subtitle;}

    public void setTime(String time) { this.time = time;}
    public String getTime() { return time;}

    public void setAuthor(String author) { this.author = author;}
    public String getAuthor() { return author;}

    public void setSiteLogoImageLink(String siteLogoImageLink) { this.siteLogoImageLink = siteLogoImageLink;}
    public String getSiteLogoImageLink() { return siteLogoImageLink;}

    public void setArticleImageLink(String articleImageLink) { this.articleImageLink = articleImageLink;}
    public String getArticleImageLink() { return articleImageLink;}

    public void setArticleLink(String articleLink) { this.articleLink = articleLink;}
    public String getArticleLink() { return this.articleLink;}

}
