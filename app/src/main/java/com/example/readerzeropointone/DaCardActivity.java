package com.example.readerzeropointone;

import android.icu.text.SimpleDateFormat;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Comparator;
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
    final private Date cardPreviewDate;

    public DaCardActivity(int cardID, Date cardPreviewDate, String cardPreviewTimeTextView, String cardPreviewAuthorTextView,
                          String cardPreviewSiteLogoUrl, String cardPreviewArticleImageUrl, String cardPreviewHeadlineTextView,
                          String cardPreviewSubtitleTextView, String cardPreviewLink) {
        this.cardID = cardID;
        this.cardPreviewDate = cardPreviewDate != null? cardPreviewDate: new Date();
        this.cardPreviewTimeTextView = cardPreviewTimeTextView;
        this.cardPreviewAuthorTextView = cardPreviewAuthorTextView;
        this.cardPreviewSiteLogoUrl = cardPreviewSiteLogoUrl != null? cardPreviewSiteLogoUrl: "https://source.android.com/setup/images/Android_symbol_green_RGB.png";
        this.cardPreviewArticleImageUrl = cardPreviewArticleImageUrl != null? cardPreviewArticleImageUrl: "https://www.wallpapertip.com/wmimgs/64-649248_background-for-clean.jpg";
        this.cardPreviewHeadlineTextView = cardPreviewHeadlineTextView;
        this.cardPreviewSubtitleTextView = cardPreviewSubtitleTextView;
        this.cardPreviewLink = cardPreviewLink;
    }

    public int getCardID() {
        return cardID;
    }

    public Date getCardPreviewDate() {
        return cardPreviewDate;
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

}
