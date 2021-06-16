package com.example.readerzeropointone;

import io.realm.Realm;

public class AddedLinkDBHelper {

    Realm realm;

    public AddedLinkDBHelper(Realm realm) { this.realm = realm;}

    public void addNewLink(String rssLink, String category) {
        realm.executeTransactionAsync(r -> {
            AddedLinkDB link = new AddedLinkDB();
            link.setRssLink(rssLink);
            link.setCategory(category);
            r.insertOrUpdate(link);
        });
    }

    public void deleteLinkDB(String rssLink) {
        realm.executeTransactionAsync(r -> {
            AddedLinkDB link = r.where(AddedLinkDB.class).equalTo("rssLink", rssLink).findFirst();
            if (link != null) {
                link.deleteFromRealm();
            }
        });
    }
}
