package com.example.readerzeropointone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class YourResults extends AppCompatActivity {

    RecyclerView yourResultsRecylerView;
    YourResultsAdapter yourResultsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.your_results_layout);

        ArrayList<YourResultCard> arraylist = new ArrayList<YourResultCard>();
        arraylist = (ArrayList<YourResultCard>) getIntent().getSerializableExtra("searchResultsList");

//        arraylist.add(new YourResultCard(1,"https://source.android.com/setup/images/Android_symbol_green_RGB.png",
//                "rss.cnn.com",
//                "cnn.com"
//                , "This is a description"));
//
//
//        arraylist.add(new YourResultCard(2,"https://source.android.com/setup/images/Android_symbol_green_RGB.png",
//                "rss.cnn.com",
//                "cnn.com"
//                , "This is a description"));
//
//        arraylist.add(new YourResultCard(3,"https://source.android.com/setup/images/Android_symbol_green_RGB.png",
//                "rss.cnn.com",
//                "cnn.com"
//                , "This is a description"));
//
//        arraylist.add(new YourResultCard(4,"https://source.android.com/setup/images/Android_symbol_green_RGB.png",
//                "rss.cnn.com",
//                "cnn.com"
//                , "This is a description"));
//
//        arraylist.add(new YourResultCard(5,"https://source.android.com/setup/images/Android_symbol_green_RGB.png",
//                "rss.cnn.com",
//                "cnn.com"
//                , "This is a description"));

        yourResultsRecylerView = findViewById(R.id.yourResultsRecyclerView);
        yourResultsAdapter = new YourResultsAdapter(this, arraylist); //over here
        yourResultsAdapter.setHasStableIds(true);
        yourResultsRecylerView.setAdapter(yourResultsAdapter);
        yourResultsRecylerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }
}
