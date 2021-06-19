package com.example.readerzeropointone;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.realm.Realm;
import io.realm.RealmList;

public class AddSubActivity extends AppCompatActivity {

    public static final String[] SET_VALUES = new String[] { "www", "feed", "feeds", "rss", "com",
            "co", "in", "ca", "uk", "blogspot", "nz", "org", "info", "blog", "ch", "inc", "go",
            "eu", "us", "ng", "io", "gq", "net", "au", "int" };
    public static final Set<String> MY_SET = new HashSet<>(Arrays.asList(SET_VALUES));

    boolean menuOpen = false;
    Button todayButton, savedButton, subsButton, addNewSubButton, addThisLinkButton;
    ImageButton mainMenuButton;
    ImageView yepBlow;
    EditText queryEntry;

    private Realm realm;

    // testing
    Button displayLinks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sub_layout);

        // backend
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        //init
        mainMenuButton = findViewById(R.id.mainMenuButton);
        todayButton = findViewById(R.id.todayButton);
        savedButton = findViewById(R.id.savedButton);
        subsButton = findViewById(R.id.yourSubscriptions);
        addNewSubButton = findViewById(R.id.addANewSub);
        yepBlow = findViewById(R.id.blowUpView);
        queryEntry = findViewById(R.id.addSubEditText); //
        addThisLinkButton = findViewById(R.id.addThisSite);

        //anim default
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
                    obj.showMenuItems(yepBlow, todayButton, savedButton, subsButton, addNewSubButton);
                    subsButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //TODO need to do anim polishing | add a new fun with click and close | need to add transitions | later
                            obj.closeMenuItems(yepBlow, todayButton, savedButton, subsButton, addNewSubButton);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent openYourSubs = new Intent(AddSubActivity.this, YourSubsActivity.class);
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
        });

        addThisLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toast("fetching...");
                boolean validLink = true;
                String link = queryEntry.getText().toString();

                // returns true if the link is valid
                validLink = validateLink(link);

                if(validLink) {

                    LinkDBHelper helper = new LinkDBHelper(realm);
                    helper.addNewLinkDB(link, croppedDomain(link), checkedCategory(), croppedTags(link));

                    Log.v("DB", link + " Added.");
                    queryEntry.setText("");
                    toast("Added to DB");
                }
                else {
                    //user has not entered a rss link
                    toast("Invalid Link");
                    Log.v("DB", link + " is not a valid link!");
                }

            }
        });



        // for testing purposes
        displayLinks = findViewById(R.id.displayLinks);
        displayLinks.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                String query = queryEntry.getText().toString();
                if(query.equals("")) {
                    Set<String> listOrderedSet = new LinkDBHelper(realm).getAllLinkDB();
                    for (String str:listOrderedSet) {
                        Log.v("DB", str);
                    }
                } else {
                    Set<String> set = new LinkDBHelper(realm).getAllLinkDB(query);
                    for (String str:set) {
                        Log.v("DB", str);
                    }
                }
            }
        });
    }

    private synchronized Boolean validateLink(String link) {

        Boolean result = false;
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Boolean> futureCall = executor.submit(new ConnectionCallable(link));

        try {
            result = futureCall.get();
        } catch (Exception e) {
            System.out.println("################ ERROR !!! #####################");
            e.printStackTrace();
        }

        return result;
    }

    public static String croppedDomain(String url) {

        String domain;
        URI uri;

        try {
            uri = new URI(url);
            domain = uri.getHost();
        } catch (URISyntaxException e) {
            System.out.println("################ ERROR !!! #####################");
            e.printStackTrace();
            return null;
        }
        domain  = domain.startsWith("www.") ? domain.substring(4) : domain;
        return domain;
    }

    private String checkedCategory() {
        return "";
    }

    public static RealmList<String> croppedTags(String url){
        RealmList<String> resultTags = new RealmList<>();

        URI uri;
        try {
            uri = new URI(url);
            String domain = uri.getHost();
            String[] split = domain.split("\\.");
            for (String s : split) {
                if (!MY_SET.contains(s))
                    resultTags.add(s);
            }
            return resultTags;

        } catch (URISyntaxException e) {
            System.out.println("################ ERROR !!! #####################");
            e.printStackTrace();
            return null;
        }
    }

    private void toast(String str) {
        Toast.makeText(AddSubActivity.this, str, Toast.LENGTH_SHORT).show();
    }
}
