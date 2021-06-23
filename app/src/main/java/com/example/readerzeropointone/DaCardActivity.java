package com.example.readerzeropointone;

import java.util.Date;

public class DaCardActivity {

    //time
    final private int cardID;
    //time
    final private String cardPreviewTimeTextView;
    //author
    final private String cardPreviewAuthorTextView;
    //site logo
    final private String cardPreviewSiteLogoUrl;
    //article image
    final private String cardPreviewArticleImageUrl;
    //Headline
    final private String cardPreviewHeadlineTextView;
    //Subtitle
    final private String cardPreviewSubtitleTextView;
    //link
    final private String cardPreviewLink;
    //time for sorting
    final private Date cardPreviewTime;

    final private String cardPreviewDate;

    public DaCardActivity(int cardID, Date cardPreviewTime, String cardPreviewDate, String cardPreviewTimeTextView, String cardPreviewAuthorTextView,
                          String cardPreviewSiteLogoUrl, String cardPreviewArticleImageUrl, String cardPreviewHeadlineTextView,
                          String cardPreviewSubtitleTextView, String cardPreviewLink) {
        this.cardID = cardID;
        this.cardPreviewTime = cardPreviewTime != null? cardPreviewTime : new Date();
        this.cardPreviewTimeTextView = cardPreviewTimeTextView;
        this.cardPreviewAuthorTextView = cardPreviewAuthorTextView;
        this.cardPreviewSiteLogoUrl = cardPreviewSiteLogoUrl != null? cardPreviewSiteLogoUrl: "https://source.android.com/setup/images/Android_symbol_green_RGB.png";
        this.cardPreviewArticleImageUrl = cardPreviewArticleImageUrl != null? cardPreviewArticleImageUrl: "https://www.wallpapertip.com/wmimgs/64-649248_background-for-clean.jpg";
        this.cardPreviewHeadlineTextView = cardPreviewHeadlineTextView;
        this.cardPreviewSubtitleTextView = cardPreviewSubtitleTextView;
        this.cardPreviewLink = cardPreviewLink;
        this.cardPreviewDate = cardPreviewDate;
    }

    public int getCardID() {
        return cardID;
    }

    public Date getCardPreviewTime() {
        return cardPreviewTime;
    }

    public String getCardPreviewTimeTextView() {
        return cardPreviewTimeTextView;
    }

    public String getCardPreviewAuthorTextView() {
        return cardPreviewAuthorTextView;
    }

    public String getCardPreviewSiteLogoUrl() {
        return cardPreviewSiteLogoUrl;
    }

    public String getCardPreviewArticleImageUrl() {
        return cardPreviewArticleImageUrl;
    }

    public String getCardPreviewHeadlineTextView() {
        return cardPreviewHeadlineTextView;
    }

    public String getCardPreviewSubtitleTextView() {
        return cardPreviewSubtitleTextView;
    }

    public String getCardPreviewLink() {
        return cardPreviewLink;
    }

    public String getCardPreviewDate() {
        return cardPreviewDate;
    }
}
