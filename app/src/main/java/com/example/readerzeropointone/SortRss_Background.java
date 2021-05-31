package com.example.readerzeropointone;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;

public class SortRss_Background extends AsyncTask<Integer, Void, Void> {

    private final MainActivity mainActivity;
    private ProgressDialog progressDialog = null;

    public SortRss_Background(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.progressDialog = new ProgressDialog(mainActivity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Processing....");
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        Log.v("sort", "sorting....");
        Collections.sort(mainActivity.tempList, new SortByDate());
        Log.v("sort", "sorted!");
        int count = 1;
        for(DaCardActivity card: mainActivity.tempList) {
            mainActivity.articlesList.add(new DaCardActivity(count++, card.getCardPreviewDate(), card.getCardPreviewTimeTextView(),
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
        progressDialog.dismiss();
    }

    private static class SortByDate implements Comparator<DaCardActivity> {

        @Override
        public int compare(DaCardActivity card1, DaCardActivity card2) {
            Log.v("sort", String.valueOf(card1.getCardPreviewDate().compareTo(card2.getCardPreviewDate())));
            return card1.getCardPreviewDate().compareTo(card2.getCardPreviewDate());
        }
    }
}
