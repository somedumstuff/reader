package com.example.readerzeropointone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddSubActivity extends AppCompatActivity {
    boolean menuOpen = false;
    Button todayButton, savedButton, subsButton, addNewSubButton, addThisLinkButton;
    ImageButton mainMenuButton;
    ImageView yepBlow;
    EditText queryEntry;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sub_layout);

        //init
        mainMenuButton = findViewById(R.id.mainMenuButton);
        todayButton = findViewById(R.id.todayButton);
        savedButton = findViewById(R.id.savedButton);
        subsButton = findViewById(R.id.yourSubscriptions);
        addNewSubButton = findViewById(R.id.addANewSub);
        yepBlow = findViewById(R.id.blowUpView);
        queryEntry = findViewById(R.id.addSubEditText);
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
                Toast.makeText(AddSubActivity.this, "Added so it doesnt crash", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
