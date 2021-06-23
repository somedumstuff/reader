package com.example.readerzeropointone;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProcessRss_Background extends AsyncTask<Integer, Void, Exception> {

    private final MainActivity mainActivity;
    ProgressDialog progressDialog = null;
    URL url = null;
    Exception exception = null;
    String headline = null;
    String subtitle = null;
    String author = null;
    Date time = null;
    String date = null;
    String pubDate = null;
    String link = null;
    String articleImageLink = null;
    String logoLink = null;
    int num = 1;

    public ProcessRss_Background(MainActivity mainActivity, String url) {
        this.mainActivity = mainActivity;
        this.progressDialog = new ProgressDialog(mainActivity);
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            Log.v("RSS", "MalformedURLException");
            e.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Loading feed.....");
        progressDialog.show();
    }

    @Override
    protected Exception doInBackground(Integer... integers) {

        try {
            if(url == null)
                return exception;
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);

            XmlPullParser xpp = factory.newPullParser();
            Log.v("RSS", String.valueOf(url));
            xpp.setInput(getInputStream(url), "UTF_8");

            boolean insideItem = false;
            boolean foundLogo = false;
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT && num < 10) {
                if (eventType == XmlPullParser.START_TAG) {
//                        Log.v("RSS", xpp.getName() + " getAttributeCount(): " + xpp.getAttributeCount());
                    if (xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = true;
                    }
                    else if (xpp.getName().equalsIgnoreCase("title") && insideItem) {
                        headline = xpp.nextText();
                        Log.v("RSS", headline);
                    }
                    else if (xpp.getName().equalsIgnoreCase("description") && insideItem) {
                        subtitle = xpp.nextText();
//                        Log.v("RSS", subtitle);
                    }
                    else if (xpp.getName().equalsIgnoreCase("link") && insideItem) {
                        link = xpp.nextText();
//                        Log.v("RSS", link);
                    }
                    else if (xpp.getName().equalsIgnoreCase("pubDate") && insideItem) {
                        pubDate = xpp.nextText();
                        String day = fullDay(pubDate.substring(0, 3));
                        String time = pubDate.substring(17, 22);
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss Z");
                        try {
                            this.time = format.parse(pubDate);
                        } catch (ParseException e) { e.printStackTrace();}
                        date = pubDate.substring(5,16);
                        pubDate = time + " | " + day;
                        Log.v("RSS", pubDate);
                    }
                    else if ((xpp.getName().equalsIgnoreCase("dc:creator") || (xpp.getName().equalsIgnoreCase("author"))) && insideItem) {
                        author = xpp.nextText();
//                        Log.v("RSS", author);
                    }
                    else if (xpp.getName().equalsIgnoreCase("media:content") && insideItem) {
//                      Log.v("RSS", "name: " + xpp.getAttributeName(2) + " |namespace:  " + xpp.getAttributeNamespace(2));
                        Log.v("RSS", xpp.getAttributeValue("", "url"));
                        articleImageLink = xpp.getAttributeValue("", "url");
                    }
                    else if (xpp.getName().equalsIgnoreCase("image") && !insideItem) {
                        foundLogo = true;
                    }
                    else if (xpp.getName().equalsIgnoreCase("url") && foundLogo) {
                        logoLink = xpp.nextText();
                    }
                }
                else if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equalsIgnoreCase("item") && insideItem && author != "") {
                        insideItem = false;
                        //create a card and add to an adapter
                        mainActivity.tempList.add(new DaCardActivity(mainActivity.count++, time, date, pubDate, author,
                                logoLink, articleImageLink, headline, subtitle, link));
                        num++; time = null; pubDate = null; author = null; articleImageLink = null; headline = null; subtitle = null; link = null;
                    }
                }
                eventType = xpp.next();
            }
        } catch (XmlPullParserException | IOException e) {
            exception = e;
        }
        return exception;
    }

    @Override
    protected void onPostExecute(Exception e) {
        super.onPostExecute(e);
        progressDialog.dismiss();
    }

    private String fullDay(String pre) {
        switch (pre) {
            case "Tue":
                return "Tuesday";
            case "Wed":
                return "Wednesday";
            case "Thu":
                return "Thursday";
            case "Fri":
                return "Friday";
            case "Sat":
                return "Saturday";
            case "Sun":
                return "Sunday";
            default:
                return "Monday";

        }
    }

    private InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

}
