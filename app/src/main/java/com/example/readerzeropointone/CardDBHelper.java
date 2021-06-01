package com.example.readerzeropointone;

import io.realm.Realm;

public class CardDBHelper {

    Realm realm;

    public CardDBHelper(Realm realm) { this.realm = realm;}

    public void addCard(String headline, String subtitle, String time, String author, String siteLogoImageLink, String articleImageLink, String articleLink) {
        realm.executeTransactionAsync(r -> {
            CardDB card = new CardDB(headline);
            card.setSubtitle(subtitle);
            card.setTime(time);
            card.setAuthor(author);
            if(siteLogoImageLink != null)
                card.setSiteLogoImageLink(siteLogoImageLink);
            if(articleImageLink != null)
                card.setArticleImageLink(articleImageLink);
            if(articleLink != null)
                card.setArticleLink(articleLink);
            r.insertOrUpdate(card);
        });
    }

    public void deleteCard(String headline) {
        realm.executeTransactionAsync(r -> {
            CardDB card = r.where(CardDB.class).equalTo("headline", headline).findFirst();
            if (card != null) {
                card.deleteFromRealm();
            }
        });
    }

}
