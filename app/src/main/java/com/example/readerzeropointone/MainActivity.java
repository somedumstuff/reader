package com.example.readerzeropointone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.FloatArrayEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Interpolator;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.transition.Explode;
import android.transition.Fade;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    boolean menuOpen = false;
    String[] headlinesPlaceHolder, continueReadingHeadlines;
    String[] subtitlePlaceHolder, continueReadingSubtitles;
    int[] logosPlaceHolder, getContinueReadingArticleLogoPreviewPlaceHolder;
    int[] articleImagePreviewPlaceHolder, continueReadingArticleImagePreviewPlaceHolder;
    String[] timePlaceHolder, continueReadingTimePlaceHolder;
    String[] authorPlaceHolder, continueReadingAuthorPlaceHolder;
    RecyclerView previewRecyclerView, continueReadingRecyclerView;
    ScrollView scrollViewActivityMain, continueReadingScrollView;
    Button todayButton, savedButton, subsButton, addNewSubButton;
    ImageButton mainMenuButton;
    ImageView yepBlow;
    View parentActivityMain, buttonContainerActivityMain;
    RenderScript renderScript;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentActivityMain = findViewById(R.id.parentActivityMain);
//      scrollViewActivityMain = findViewById(R.id.scrollViewActivityMain);
        buttonContainerActivityMain = findViewById(R.id.buttonContainer);

        ArrayList<DaCardActivity> arraylist = new ArrayList<DaCardActivity>();

        arraylist.add(new DaCardActivity(1,"11:30 | Monday", "Anaya Parsons", R.drawable.iconplaceholder1, R.drawable.placeholder1,
                "Heres the headline: Everything is so bad i cant even. I mean the world is literally on fire."
                , "Climate change is very real. Do not believe what your grandma says from Facebook."));

        arraylist.add(new DaCardActivity(2,"11:30 | Monday", "Anaya Parsons", R.drawable.iconplaceholder2, R.drawable.placeholder2,
                "Heres the headline: Everything is so bad i cant even. I mean the world is literally on fire."
                , "Climate change is very real. Do not believe what your grandma says from Facebook."));

        arraylist.add(new DaCardActivity(3,"11:30 | Monday", "Anaya Parsons", R.drawable.iconplaceholder3, R.drawable.placeholder3,
                "Heres the headline: Everything is so bad i cant even. I mean the world is literally on fire."
                , "Climate change is very real. Do not believe what your grandma says from Facebook."));

        arraylist.add(new DaCardActivity(4,"11:30 | Monday", "Anaya Parsons", R.drawable.iconplaceholder4, R.drawable.placeholder4,
                "Heres the headline: Everything is so bad i cant even. I mean the world is literally on fire."
                , "Climate change is very real. Do not believe what your grandma says from Facebook."));

        arraylist.add(new DaCardActivity(5,"11:30 | Monday", "Anaya Parsons", R.drawable.iconplaceholder5, R.drawable.placeholder5,
                "Heres the headline: Everything is so bad i cant even. I mean the world is literally on fire."
                , "Climate change is very real. Do not believe what your grandma says from Facebook."));

        logosPlaceHolder = new int[]{
                R.drawable.iconplaceholder1,
                R.drawable.iconplaceholder2,
                R.drawable.iconplaceholder3,
                R.drawable.iconplaceholder4,
                R.drawable.iconplaceholder5
        };
        continueReadingArticleImagePreviewPlaceHolder = new int[]{
                R.drawable.placeholer1,
                R.drawable.placeholer2,
                R.drawable.placeholer3,
                R.drawable.placeholer4,
                R.drawable.placeholer5
        };
        articleImagePreviewPlaceHolder = new int[]{
                R.drawable.placeholder1,
                R.drawable.placeholder2,
                R.drawable.placeholder3,
                R.drawable.placeholder4,
                R.drawable.placeholder5
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

        mainMenuButton.setRotation(90);
        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationHandler obj = new AnimationHandler();
                if(!menuOpen){
                    menuOpen = obj.openMenu(mainMenuButton);
                    Toast.makeText(MainActivity.this, String.valueOf(menuOpen), Toast.LENGTH_SHORT).show();
                    //click(mainMenuButton);
                    obj.clickAnim(mainMenuButton);
                    obj.showMenuItems(yepBlow, todayButton, savedButton, subsButton, addNewSubButton);
                }
                else{
                    menuOpen = obj.closMenu(mainMenuButton);
                    obj.clickAnim(mainMenuButton);
                    Toast.makeText(MainActivity.this, String.valueOf(menuOpen), Toast.LENGTH_SHORT).show();
                    obj.closeMenuItems(yepBlow, todayButton, savedButton, subsButton, addNewSubButton);
                }
            }
        });


        //getting strings
        headlinesPlaceHolder = getResources().getStringArray(R.array.testHeadlines);
        subtitlePlaceHolder = getResources().getStringArray(R.array.testSubtitles);
        timePlaceHolder = getResources().getStringArray(R.array.timePlaceHolders);
        authorPlaceHolder = getResources().getStringArray(R.array.authorPlaceHolders);
        continueReadingAuthorPlaceHolder = getResources().getStringArray(R.array.continueReadingAuthorPlaceHolders);
        continueReadingSubtitles = getResources().getStringArray(R.array.continueReadingSubtitlesPlaceholder);
        continueReadingTimePlaceHolder = getResources().getStringArray(R.array.continueReadingTimePlaceHolders);
        continueReadingHeadlines = getResources().getStringArray(R.array.continueReadingHeadlinesPlaceholder);

        //recycler view today preview
        previewRecyclerView = findViewById(R.id.todayCardPreviewRecycler);
        previewRecyclerView.setItemViewCacheSize(20);
        previewRecyclerView.setDrawingCacheEnabled(true);
        previewRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        previewRecyclerView.setNestedScrollingEnabled(false);
        CardPreviewAdapter cardPreviewAdapter = new CardPreviewAdapter(this, arraylist);
        cardPreviewAdapter.setHasStableIds(true);
        cardPreviewAdapter.notifyDataSetChanged();
        previewRecyclerView.setAdapter(cardPreviewAdapter);
        previewRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        //recycler view continue reading
        continueReadingRecyclerView = findViewById(R.id.continueReadingCardPreviewRecycler);
        continueReadingRecyclerView.setItemViewCacheSize(20);
        continueReadingRecyclerView.setDrawingCacheEnabled(true);
        continueReadingRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        continueReadingRecyclerView.setNestedScrollingEnabled(false);
        ContinueReadingAdapter continueReadingAdapter = new ContinueReadingAdapter(this, continueReadingHeadlines, continueReadingSubtitles, continueReadingAuthorPlaceHolder, continueReadingTimePlaceHolder, continueReadingArticleImagePreviewPlaceHolder, logosPlaceHolder);
        continueReadingRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        continueReadingRecyclerView.setAdapter(continueReadingAdapter);

    }

}