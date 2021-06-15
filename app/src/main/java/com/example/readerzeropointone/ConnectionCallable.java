package com.example.readerzeropointone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.util.concurrent.Callable;

public class ConnectionCallable implements Callable<Boolean>{

    public static String CATEGORY = "world";
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

            for (int i = 0; i < doc.childNodeSize()-1; i++) {
                if ( rssElement.attr("version").equals("2.0") )
                    break;
                else if (i == doc.childNodeSize()-1)
                    throw new Exception("reached the end");
                rssElement = rssElement.nextSibling();
            }

            System.out.println("contains version");
            if (rssElement.childNodes().toString().contains("channel")) {
                System.out.println("contains channel");
                return true;
            }

        } catch (Exception e) {
            System.out.println("################ ERROR !!! #####################\n" +
                    "Invalid link: " + link +
                    "\nError message: " + e.getMessage());
            // nothing to worry about the trace
            // e.printStackTrace();
            return false;
        }

        /* TODO make another connection for identifying the category and
            save it in the static category variable */
        /** if no available category set default to this.CATEGORY **/

        return false;
    }
}
