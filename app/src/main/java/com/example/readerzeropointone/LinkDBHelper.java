package com.example.readerzeropointone;

import io.realm.Realm;

public class LinkDBHelper {

    Realm realm;

    public LinkDBHelper(Realm realm) {
        this.realm = realm;
    }

    public void addNewLinkDB(String rssLink) {
        realm.executeTransactionAsync(r -> {
            LinkDB link = new LinkDB();
            link.setRssLink(rssLink);
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
}
