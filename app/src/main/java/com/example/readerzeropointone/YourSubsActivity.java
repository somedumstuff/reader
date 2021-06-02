package com.example.readerzeropointone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class YourSubsActivity  extends AppCompatActivity {

    boolean menuOpen = false;
    int[] logosPlaceHolder;
    Button todayButton, savedButton, subsButton, addNewSubButton;
    ImageButton mainMenuButton;
    ImageView yepBlow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_subs_layout);

        Intent getterIntent = getIntent();

        ArrayList<DaSubActivity> logosUrl = new ArrayList<>();

        logosPlaceHolder = new int[]{
                R.drawable.iconplaceholder1,
                R.drawable.iconplaceholder2,
                R.drawable.iconplaceholder3,
                R.drawable.iconplaceholder4,
                R.drawable.iconplaceholder5
        };

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


        logosUrl.add(new DaSubActivity(1, R.drawable.iconplaceholder1));
        logosUrl.add(new DaSubActivity(2, R.drawable.iconplaceholder2));
        logosUrl.add(new DaSubActivity(3, R.drawable.iconplaceholder3));
        logosUrl.add(new DaSubActivity(4, R.drawable.iconplaceholder4));
        logosUrl.add(new DaSubActivity(5, R.drawable.iconplaceholder5));
        logosUrl.add(new DaSubActivity(6, R.drawable.iconplaceholder1));
        logosUrl.add(new DaSubActivity(7, R.drawable.iconplaceholder2));
        logosUrl.add(new DaSubActivity(8, R.drawable.iconplaceholder3));
        logosUrl.add(new DaSubActivity(9, R.drawable.iconplaceholder4));
        logosUrl.add(new DaSubActivity(10, R.drawable.iconplaceholder5));

        mainMenuButton.setRotation(90);
        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationHandler obj = new AnimationHandler();
                if(!menuOpen){
                    menuOpen = obj.openMenu(mainMenuButton);
                    obj.clickAnim(mainMenuButton);
                    obj.showMenuItems(yepBlow, todayButton, savedButton, subsButton, addNewSubButton);
                }
                else{
                    menuOpen = obj.closMenu(mainMenuButton);
                    obj.clickAnim(mainMenuButton);
                    obj.closeMenuItems(yepBlow, todayButton, savedButton, subsButton, addNewSubButton);
                }
            }
        });

        RecyclerView yourSubsRecyclerView = findViewById(R.id.yourSubsRecyclerView);
        GridLayoutManager yourSubsGridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        YourSubsAdapter yourSubsAdapter = new YourSubsAdapter(this, logosUrl);
        yourSubsRecyclerView.setLayoutManager(yourSubsGridLayoutManager);
        yourSubsRecyclerView.setAdapter(yourSubsAdapter);

    }
}
