package com.example.readerzeropointone;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class LinkDBHelper {

    Realm realm;

    public LinkDBHelper(Realm realm) {
        this.realm = realm;
    }

//    public void addNewLinkDB(String rssLink) {
//        realm.executeTransactionAsync(r -> {
//            LinkDB link = new LinkDB();
//            link.setRssLink(rssLink);
//            r.insertOrUpdate(link);
//        });
//    }

    public void addNewLinkDB(String rssLink, String domain, String category, RealmList<String> rssLinkTags) {
        realm.executeTransactionAsync(r -> {
            LinkDB link = new LinkDB();
            link.setRssLink(rssLink);
            link.setDomain(domain);
            if (!category.equals(""))
                link.setCategory(category);
            if (!(rssLinkTags.size() == 0))
                link.setTags(rssLinkTags);
            r.insertOrUpdate(link);
        });
    }

    public void deleteLinkDB(String rssLink) {
        realm.executeTransactionAsync(r -> {
            LinkDB link = r.where(LinkDB.class).equalTo("rssLink", rssLink).findFirst();
            if (link != null) {
                link.deleteFromRealm();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public synchronized Set<String> getAllLinkDB() {

        Set<String> linksSet = new LinkedHashSet<>();
        RealmResults<LinkDB> results = realm.where(LinkDB.class).findAllAsync();

        List<LinkDB> resultList = new ArrayList<>(results);
        resultList.sort(new Comparator<LinkDB>() {
            @Override
            public int compare(LinkDB ele1, LinkDB ele2) {
                return ele1.getDomain().compareTo(ele2.getDomain());
            }
        });
        for(LinkDB res: resultList) {
            linksSet.add(res.getRssLink());
        }

        return linksSet;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public synchronized Set<String> getAllLinkDB(String search) {

        RealmResults<LinkDB> results = realm.where(LinkDB.class)
                .contains("domain", search)
                .findAllAsync();
        RealmResults<LinkDB> results2 = realm.where(LinkDB.class)
                .contains("category", search)
                .findAllAsync();

        List<LinkDB> resultList = new ArrayList<>();
        resultList.addAll(results);
        resultList.addAll(results2);
        resultList.sort((entry1, entry2) -> entry1.getDomain().compareTo(entry2.getDomain()));

        Set<String> linksSet = new LinkedHashSet<>();
        for (LinkDB str : resultList){
            linksSet.add(str.getRssLink());
        }

        return linksSet;
    }
}
