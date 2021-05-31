package com.example.readerzeropointone;

import io.realm.Realm;

public class CardDBHelper {

    Realm realm;

    public CardDBHelper(Realm realm) { this.realm = realm;}

    public void addCard(String headline, String subtitle, String time, String author, String siteLogoImageLink, String articleImageLink) {
        realm.executeTransactionAsync(r -> {
            CardDB card = new CardDB();
            card.setHeadline(headline);
            card.setSubtitle(subtitle);
            card.setTime(time);
            card.setAuthor(author);
            if(siteLogoImageLink != null)
            card.setSiteLogoImageLink(siteLogoImageLink);
            if(articleImageLink != null)
            card.setArticleImageLink(articleImageLink);
        });
    }

    public void deleteCard(String headline) {
        realm.executeTransactionAsync(r -> {
            CardDB card = realm.where(CardDB.class).equalTo("headline", headline).findFirst();
            if (card != null) {
                card.deleteFromRealm();
            }
        });
    }

}
