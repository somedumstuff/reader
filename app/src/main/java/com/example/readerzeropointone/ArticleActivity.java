package com.example.readerzeropointone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.transition.Fade;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import static android.view.View.GONE;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_view);
        Fade fade = new Fade();
        Explode explodeTransition = new Explode();
        FastOutSlowInInterpolator interpolator = new FastOutSlowInInterpolator();
        explodeTransition.setInterpolator(interpolator);

        getWindow().setEnterTransition(explodeTransition);
        getWindow().setExitTransition(explodeTransition);

        final CardView articleViewArticleImageContainer = findViewById(R.id.articleViewArticleImageContainer);
        final LinearLayout articleMetadataContainer  = findViewById(R.id.articleMetadataContainer);
        final TextView articleHeadlineTextView = findViewById(R.id.articleHeadlineTextView);
        final TextView articleSubtitleTextView = findViewById(R.id.articleSubtitleTextView);
        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.view_fade_in);

        fadeIn.setInterpolator(interpolator);
        fadeIn.setDuration(1500);

        articleViewArticleImageContainer.setAnimation(fadeIn);
        articleMetadataContainer.setAnimation(fadeIn);
        articleHeadlineTextView.setAnimation(fadeIn);
        articleSubtitleTextView.setAnimation(fadeIn);
        fadeIn.setStartOffset(2);

        articleViewArticleImageContainer.startAnimation(fadeIn);
        articleMetadataContainer.startAnimation(fadeIn);
        articleHeadlineTextView.startAnimation(fadeIn);
        articleSubtitleTextView.startAnimation(fadeIn);


    }

}
