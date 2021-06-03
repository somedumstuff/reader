package com.example.readerzeropointone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.RenderScript;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    boolean menuOpen = false;
//    ExecutorService executorService = Executors.newFixedThreadPool(3);
//    String[] headlinesPlaceHolder, continueReadingHeadlines;
//    String[] subtitlePlaceHolder, continueReadingSubtitles;
//    int[] logosPlaceHolder, getContinueReadingArticleLogoPreviewPlaceHolder;
//    int[] articleImagePreviewPlaceHolder, continueReadingArticleImagePreviewPlaceHolder;
//    String[] timePlaceHolder, continueReadingTimePlaceHolder;
//    String[] authorPlaceHolder, continueReadingAuthorPlaceHolder;
//    ScrollView scrollViewActivityMain, continueReadingScrollView;
//    RenderScript renderScript;
    RecyclerView previewRecyclerView, continueReadingRecyclerView;
    Button todayButton, savedButton, subsButton, addNewSubButton;
    ImageButton mainMenuButton;
    ImageView yepBlow;
    View parentActivityMain, buttonContainerActivityMain;
    private Realm realm;
    ArrayList<DaCardActivity> articlesList = new ArrayList<DaCardActivity>();
    ArrayList<DaCardActivity> tempList = new ArrayList<DaCardActivity>();
    int count = 1; //for cardID in articlesList
    private CardPreviewAdapter cardPreviewAdapter = null;
    private ContinueReadingAdapter2 continueReadingAdapter = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pre-populating linksDB and cardDB
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realm = Realm.getDefaultInstance();
        fillUpDatabase();
        //fetching articles from rss links
        getFeeds();

        parentActivityMain = findViewById(R.id.parentActivityMain);
//      scrollViewActivityMain = findViewById(R.id.scrollViewActivityMain);
        buttonContainerActivityMain = findViewById(R.id.buttonContainer);

        ArrayList<DaCardActivity> arraylist = new ArrayList<DaCardActivity>();
        ArrayList<DaCardActivity> continueReadingList = new ArrayList<>();

        //today list
//        arraylist.add(new DaCardActivity(1,"11:30 | Monday", "Anaya Parsons", R.drawable.iconplaceholder1, R.drawable.placeholder1,
//                "Heres the headline: Everything is so bad i cant even. I mean the world is literally on fire."
//                , "Climate change is very real. Do not believe what your grandma says from Facebook."));
//
//        arraylist.add(new DaCardActivity(2,"11:30 | Monday", "Anaya Parsons", R.drawable.iconplaceholder2, R.drawable.placeholder2,
//                "Heres the headline: Everything is so bad i cant even. I mean the world is literally on fire."
//                , "Climate change is very real. Do not believe what your grandma says from Facebook."));
//
//        arraylist.add(new DaCardActivity(3,"11:30 | Monday", "Anaya Parsons", R.drawable.iconplaceholder3, R.drawable.placeholder3,
//                "Heres the headline: Everything is so bad i cant even. I mean the world is literally on fire."
//                , "Climate change is very real. Do not believe what your grandma says from Facebook."));
//
//        arraylist.add(new DaCardActivity(4,"11:30 | Monday", "Anaya Parsons", R.drawable.iconplaceholder4, R.drawable.placeholder4,
//                "Heres the headline: Everything is so bad i cant even. I mean the world is literally on fire."
//                , "Climate change is very real. Do not believe what your grandma says from Facebook."));
//
//        arraylist.add(new DaCardActivity(5,"11:30 | Monday", "Anaya Parsons", R.drawable.iconplaceholder5, R.drawable.placeholder5,
//                "Heres the headline: Everything is so bad i cant even. I mean the world is literally on fire."
//                , "Climate change is very real. Do not believe what your grandma says from Facebook."));




        //continue reading list
//        continueReadingList.add(new DaCardActivity(1,"11:30 | Monday", "Anaya Parsons", R.drawable.iconplaceholder5, R.drawable.placeholer1,
//                "Heres the headline: Why do you think this card will have some happy news? lol"
//                , "Yeah, go back and sleep i guess."));
//
//        continueReadingList.add(new DaCardActivity(2,"12:30 | Monday", "Yoer Modher", R.drawable.iconplaceholder5, R.drawable.placeholer2,
//                "Nothing new, everything is going down the shitter."
//                , "And the shitter is clogged and overflowing."));
//
//        continueReadingList.add(new DaCardActivity(3,"03:30 | Monday", "Pipo Dab", R.drawable.iconplaceholder5, R.drawable.placeholer3,
//                "LOL BITCOIN DOWN BAD. ETH LOSES 50% OF ITS VALUE!"
//                , "TO THE MOOOOOOON!"));
//
//        continueReadingList.add(new DaCardActivity(4,"01:30 | Monday", "Doggo inc.", R.drawable.iconplaceholder5, R.drawable.placeholer4,
//                "Doggos are dope. They are good frendos. Me like doggos."
//                , "Doggo cute widepeepoHappy"));
//
//        continueReadingList.add(new DaCardActivity(5,"11:00 | Monday", "monkaW", R.drawable.iconplaceholder5, R.drawable.placeholer5,
//                "HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHHAHAHAHAHAHAHAHAHAHAHAHAHHAHAHAH"
//                , "AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"));

        //        logosPlaceHolder = new int[]{
//                R.drawable.iconplaceholder1,
//                R.drawable.iconplaceholder2,
//                R.drawable.iconplaceholder3,
//                R.drawable.iconplaceholder4,
//                R.drawable.iconplaceholder5
//        };
//        continueReadingArticleImagePreviewPlaceHolder = new int[]{
//                R.drawable.placeholer1,
//                R.drawable.placeholer2,
//                R.drawable.placeholer3,
//                R.drawable.placeholer4,
//                R.drawable.placeholer5
//        };
//        articleImagePreviewPlaceHolder = new int[]{
//                R.drawable.placeholder1,
//                R.drawable.placeholder2,
//                R.drawable.placeholder3,
//                R.drawable.placeholder4,
//                R.drawable.placeholder5
//        };


        mainMenuButton = findViewById(R.id.mainMenuButton);
        todayButton = findViewById(R.id.todayButton);
        savedButton = findViewById(R.id.savedButton);
        subsButton = findViewById(R.id.yourSubscriptions);
        addNewSubButton = findViewById(R.id.addANewSub);
        yepBlow = findViewById(R.id.blowUpView);

        todayButton.setVisibility(View.INVISIBLE);
        savedButton.setVisibility(View.INVISIBLE);
        subsButton.setVisibility(View.INVISIBLE);
        addNewSubButton.setVisibility(View.INVISIBLE);
        yepBlow.setVisibility(View.INVISIBLE);

        mainMenuButton.setRotation(90);
        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AnimationHandler obj = new AnimationHandler();
                if(!menuOpen){
                    menuOpen = obj.openMenu(mainMenuButton);
                    obj.clickAnim(mainMenuButton);
                    String date = new SimpleDateFormat("dd MMM", Locale.getDefault()).format(new Date());
                    todayButton.setText(date.substring(0,2) + suffix(date) + date.substring(3));
                    obj.showMenuItems(yepBlow, todayButton, savedButton, subsButton, addNewSubButton);
                    subsButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //TODO need to do anim polishing | add a new fun with click and close | need to add transitions | later
                            obj.closeMenuItems(yepBlow, todayButton, savedButton, subsButton, addNewSubButton);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent openYourSubs = new Intent(MainActivity.this, YourSubsActivity.class);
                                    startActivity(openYourSubs);
                                }
                            }, 800);
                        }
                    });

                    addNewSubButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            obj.closeMenuItems(yepBlow, todayButton, savedButton, subsButton, addNewSubButton);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent openYourSubs = new Intent(MainActivity.this, AddSubActivity.class);
                                    startActivity(openYourSubs);
                                }
                            }, 800);
                        }
                    });

                }
                else{
                    menuOpen = obj.closMenu(mainMenuButton);
                    obj.clickAnim(mainMenuButton);
                    obj.closeMenuItems(yepBlow, todayButton, savedButton, subsButton, addNewSubButton);
                }
            }

            private String suffix(String date) {
                int day = Integer.parseInt(date.substring(0,2));
                switch (day) {
                    case 21:
                    case 31:
                    case 1:
                        return "st";
                    case 2:
                    case 22:
                        return "nd";
                    case 3:
                    case 23:
                        return "rd";
                    default:
                        return "th";
                }
            }
        });


        //getting strings
//        headlinesPlaceHolder = getResources().getStringArray(R.array.testHeadlines);
//        subtitlePlaceHolder = getResources().getStringArray(R.array.testSubtitles);
//        timePlaceHolder = getResources().getStringArray(R.array.timePlaceHolders);
//        authorPlaceHolder = getResources().getStringArray(R.array.authorPlaceHolders);
//        continueReadingAuthorPlaceHolder = getResources().getStringArray(R.array.continueReadingAuthorPlaceHolders);
//        continueReadingSubtitles = getResources().getStringArray(R.array.continueReadingSubtitlesPlaceholder);
//        continueReadingTimePlaceHolder = getResources().getStringArray(R.array.continueReadingTimePlaceHolders);
//        continueReadingHeadlines = getResources().getStringArray(R.array.continueReadingHeadlinesPlaceholder);


        //recycler view continue reading
        continueReadingRecyclerView = findViewById(R.id.continueReadingCardPreviewRecycler);
        RealmResults<CardDB> cards = realm.where(CardDB.class).findAll();
        continueReadingAdapter = new ContinueReadingAdapter2(this, cards);
        continueReadingAdapter.setHasStableIds(true);
        continueReadingRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        continueReadingRecyclerView.setAdapter(continueReadingAdapter);

        //recycler view today preview
        previewRecyclerView = findViewById(R.id.todayCardPreviewRecycler);
        cardPreviewAdapter = new CardPreviewAdapter(this, tempList, this.continueReadingAdapter); //over here
        cardPreviewAdapter.setHasStableIds(true);
        previewRecyclerView.setAdapter(cardPreviewAdapter);
        previewRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void fillUpDatabase() {
        realm.executeTransactionAsync(realm -> {
            InputStream inputStream = getResources().openRawResource(R.raw.links);
            try {
                realm.createAllFromJson(LinkDB.class, inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                if(realm.isInTransaction())
                    realm.cancelTransaction();
            }
//            InputStream cardStream = getResources().openRawResource(R.raw.cards);
//            try {
//                realm.createAllFromJson(CardDB.class, cardStream);
//            } catch (IOException e) {
//                e.printStackTrace();
//                if(realm.isInTransaction())
//                    realm.cancelTransaction();
//            }
        });
        LinkDBHelper helper = new LinkDBHelper(realm);
        helper.deleteLinkDB("https://www.huffpost.com/section/front-page/feed");
    }

    private void getFeeds() {

        RealmResults<LinkDB> urls = realm.where(LinkDB.class).findAll();
        for(LinkDB url: urls) {
            Log.v("DB", String.valueOf(url));
//            FetchRssFeeds_Background fetchRssFeeds_background = new FetchRssFeeds_Background(this, url);
            new ProcessRss_Background(this, url.getRssLink()).execute(); //stored in tempList
        }
        new SortRss_Background(this).execute(); //sorts and stores in articlesList
    }

    public CardPreviewAdapter getCardPreviewAdapter() { return this.cardPreviewAdapter;}

    public ContinueReadingAdapter2 getContinueReadingAdapter() { return this.continueReadingAdapter;}
}