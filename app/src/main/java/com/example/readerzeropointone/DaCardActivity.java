package com.example.readerzeropointone;

import android.widget.ImageView;

public class DaCardActivity {

    //time
    final private String cardPreviewTimeTextView;
    //author
    final private String cardPreviewAuthorTextView;
    //site logo
    final private int cardPreviewSiteLogoUrl;
    //article image
    final private int cardPreviewArticleImageUrl;
    //Headline
    final private String cardPreviewHeadlineTextView;
    //Subtitle
    final private String cardPreviewSubtitleTextView;


    public DaCardActivity(String cardPreviewTimeTextView, String cardPreviewAuthorTextView,
                          int cardPreviewSiteLogoUrl, int cardPreviewArticleImageUrl, String cardPreviewHeadlineTextView,
                          String cardPreviewSubtitleTextView) {
        this.cardPreviewTimeTextView = cardPreviewTimeTextView;
        this.cardPreviewAuthorTextView = cardPreviewAuthorTextView;
        this.cardPreviewSiteLogoUrl = cardPreviewSiteLogoUrl;
        this.cardPreviewArticleImageUrl = cardPreviewArticleImageUrl;
        this.cardPreviewHeadlineTextView = cardPreviewHeadlineTextView;
        this.cardPreviewSubtitleTextView = cardPreviewSubtitleTextView;
    }

    public String getCardPreviewTimeTextView() {
        return cardPreviewTimeTextView;
    }

    public String getCardPreviewAuthorTextView() {
        return cardPreviewAuthorTextView;
    }

    public int getCardPreviewSiteLogoUrl() {
        return cardPreviewSiteLogoUrl;
    }

    public int getCardPreviewArticleImageUrl() {
        return cardPreviewArticleImageUrl;
    }

    public String getCardPreviewHeadlineTextView() {
        return cardPreviewHeadlineTextView;
    }

    public String getCardPreviewSubtitleTextView() {
        return cardPreviewSubtitleTextView;
    }
}
