package com.example.readerzeropointone;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;

public class SortRss_Background extends AsyncTask<Integer, Void, Void> {

    private final MainActivity mainActivity;

    public SortRss_Background(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        Log.v("sort", "sorting....");
        Collections.sort(mainActivity.tempList, new SortByDate());
        Log.v("sort", "sorted!");
        int count = mainActivity.tempList.size();
        for(DaCardActivity card: mainActivity.tempList) {
            mainActivity.articlesList.add(new DaCardActivity(count--, card.getCardPreviewTime(), card.getCardPreviewDate(), card.getCardPreviewTimeTextView(),
                    card.getCardPreviewAuthorTextView(), card.getCardPreviewSiteLogoUrl(), card.getCardPreviewArticleImageUrl(),
                    card.getCardPreviewHeadlineTextView(), card.getCardPreviewSubtitleTextView(), card.getCardPreviewLink()));
            Log.v("sort", "tempList" + String.valueOf(card.getCardID()));
            Log.v("sort", "tempList: " + String.valueOf(card.getCardPreviewAuthorTextView()));
        }
        for(DaCardActivity card: mainActivity.articlesList) {
            Log.v("sort", "articlesList: " + String.valueOf(card.getCardID()));
            Log.v("sort", "articlesList: " + String.valueOf(card.getCardPreviewAuthorTextView()));
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void s) {
        super.onPostExecute(s);
        mainActivity.getCardPreviewAdapter().notifyDataSetChanged();
    }

    private static class SortByDate implements Comparator<DaCardActivity> {

        @Override
        public int compare(DaCardActivity card1, DaCardActivity card2) {
            Log.v("sort", String.valueOf(-card1.getCardPreviewTime().compareTo(card2.getCardPreviewTime())));
            return -card1.getCardPreviewTime().compareTo(card2.getCardPreviewTime());
        }
    }
}
