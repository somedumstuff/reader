package com.example.readerzeropointone;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

public class CardPreviewAdapter  extends RecyclerView.Adapter<CardPreviewAdapter.ViewHolder> {

    //local vars
    String[] headlines;
    String[] subtitles;
    int[] articleImages;
    int[] logos;
    String[] authors;
    String[] times;
    Context context;
    Bitmap bitmap;

    public CardPreviewAdapter(Context ct, String[] headlines, String[] subtitles, String[] authors, String[] times
    , int[] articleImages, int[] logos){
        context = ct;
        this.headlines = headlines;
        this.subtitles = subtitles;
        this.authors = authors;
        this.times = times;
        this.articleImages = articleImages;
        this.logos = logos;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cards, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CardPreviewAdapter.ViewHolder holder, int position) {
        Picasso.get().load(articleImages[position]).resize(1280, 720).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(holder.cardPreviewArticleImage);
        Picasso.get().load(logos[position]).into(holder.cardPreviewSiteLogo);
        holder.cardPreviewAuthorTextView.setText(authors[position]);
        holder.cardPreviewTimeTextView.setText(times[position]);
        holder.cardPreviewSubtitle.setText(subtitles[position]);
        holder.cardPreviewHeadline.setText(headlines[position]);
    }

    @Override
    public int getItemCount() {
        return articleImages.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView cardPreviewHeadline;
        TextView cardPreviewSubtitle;
        TextView cardPreviewTimeTextView;
        TextView cardPreviewAuthorTextView;
        ImageView cardPreviewArticleImage;
        ImageView cardPreviewSiteLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardPreviewArticleImage = itemView.findViewById(R.id.cardPreviewArticleImage);
            cardPreviewSiteLogo = itemView.findViewById(R.id.cardPreviewSiteLogo);
            cardPreviewAuthorTextView = itemView.findViewById(R.id.cardPreviewAuthorTextView);
            cardPreviewTimeTextView = itemView.findViewById(R.id.cardPreviewTimeTextView);
            cardPreviewSubtitle = itemView.findViewById(R.id.cardPreviewSubtitleTextView);
            cardPreviewHeadline = itemView.findViewById(R.id.cardPreviewHeadlineTextView);
        }
    }
}
