package com.example.readerzeropointone;

import android.icu.text.SimpleDateFormat;

import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
public class YourResultCard implements Serializable {

    //id
    final private int cardID;
    //logo
    final private String resultSiteLogo;
    //rss
    final private String resultRssLink;
    //site link
    final private String resultSiteLink;
    //site description
    final private String resultSiteDesc;
    public YourResultCard(int cardID, String resultSiteLogo, String resultRssLink,
                          String resultSiteLink, String resultSiteDec) {
        this.cardID = cardID;
        this.resultSiteLogo = "https://source.android.com/setup/images/Android_symbol_green_RGB.png";
        this.resultRssLink = resultRssLink;
        this.resultSiteLink = resultSiteLink;
        this.resultSiteDesc = resultSiteDec;
    }

    public int getCardID() {
        return cardID;
    }

    public String getResultSiteLogo() {
        return resultSiteLogo;
    }

    public String getResultRssLink() {
        return resultRssLink;
    }

    public String getResultSiteLink() {
        return resultSiteLink;
    }

    public String getResultSiteDesc() {
        return resultSiteDesc;
    }

}
