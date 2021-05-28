package com.example.readerzeropointone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.util.concurrent.Callable;

public class ConnectionCallable implements Callable<Boolean>{

    public static final String USER_AGENT = "Chrome/90.0.4430.212";
    private final String link;

    public ConnectionCallable(String link) {
        this.link = link;
    }

    @Override
    public Boolean call() {

        try {
            System.out.println("fetching connection");
            Document doc = Jsoup.connect(link).userAgent(USER_AGENT).get();
            System.out.println("fetch successful");
            Node rssElement = doc.childNode(1);

            if (rssElement.hasAttr("version")) {
                System.out.println("contains version");
                if (rssElement.childNodes().toString().contains("channel")) {
                    System.out.println("contains channel");
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println("################ ERROR !!! #####################\n" + 
                    "Invalid link: " + link);
            // nothing to worry about the trace
            // e.printStackTrace();
            return false;
        }

        return false;
    }
}
