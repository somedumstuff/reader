package com.example.readerzeropointone;

import android.content.Intent;
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
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.realm.Realm;

public class AddSubActivity extends AppCompatActivity {
    boolean menuOpen = false;
    Button todayButton, savedButton, subsButton, addNewSubButton, addThisLinkButton;
    ImageButton mainMenuButton;
    ImageView yepBlow;
    EditText queryEntry;

    private Realm realm;


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
                //todo yo add link backend

                boolean validLink = true;
                String link = queryEntry.getText().toString();

                // add code to check if link is valid rss link or not
                // change validLink to false if its not valid
                validLink = validateLink(link);

                if(validLink) {
                    //TODO : check for duplicates

                    LinkDBHelper helper = new LinkDBHelper(realm);
                    helper.addNewLinkDB(link);
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

    private void toast(String str) {
        Toast.makeText(AddSubActivity.this, str, Toast.LENGTH_SHORT).show();
    }
}
