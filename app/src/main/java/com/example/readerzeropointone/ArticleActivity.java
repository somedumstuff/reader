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

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import static android.view.View.GONE;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_view);

        Intent intent = getIntent();
        String headlineReceived = intent.getStringExtra("HEADLINE");
        String subTitleReceived = intent.getStringExtra("SUBTITLE");
        String authorReceived = intent.getStringExtra("AUTHOR");
        String timeReceived = intent.getStringExtra("TIME");
        int articleImage = intent.getIntExtra("ARTICLE_IMAGE", 0);
        int logoImage = intent.getIntExtra("SITE_LOGO", 0);


        ImageView articleImageView = findViewById(R.id.articleViewArticleImage);
        ImageView articleSiteLogo = findViewById(R.id.articleViewSiteLogo);
        TextView articleHeadlineTextView = findViewById(R.id.articleHeadlineTextView);
        TextView articleSubtitleTextView = findViewById(R.id.articleSubtitleTextView);
        TextView articleAuthorTextView = findViewById(R.id.articleAuthorTextView);
        TextView articleTimeTextView = findViewById(R.id.articleTimeTextView);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.view_fade_in);

        articleAuthorTextView.setText(authorReceived);
        articleHeadlineTextView.setText(headlineReceived);
        articleSubtitleTextView.setText(subTitleReceived);
        articleTimeTextView.setText(timeReceived);

        Picasso.get().load(logoImage).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(articleSiteLogo);
        Picasso.get().load(articleImage).resize(1280, 720).memoryPolicy(MemoryPolicy.NO_STORE).into(articleImageView);

    }

}
