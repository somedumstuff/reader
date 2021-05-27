package com.example.readerzeropointone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ContinueReadingAdapter extends RecyclerView.Adapter<ContinueReadingAdapter.ViewHolder>{

    //local vars
    String headline;
    String subtitle;
    int articleImage;
    int logo;
    String author;
    String time;
    Context context;
    ArrayList<DaCardActivity> localArrayList;

    public ContinueReadingAdapter(Context ct, ArrayList continueReadingList){
        context = ct;
        localArrayList = continueReadingList;
    }

    @NonNull
    @Override
    public ContinueReadingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.continue_reading_cards, parent, false);
        return new ContinueReadingAdapter.ViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull final ContinueReadingAdapter.ViewHolder holder, int position) {
        DaCardActivity daCardActivity = (DaCardActivity) localArrayList.get(position);
        headline = daCardActivity.getCardPreviewHeadlineTextView();
        subtitle = daCardActivity.getCardPreviewSubtitleTextView();
        articleImage = daCardActivity.getCardPreviewArticleImageUrl();
        logo = daCardActivity.getCardPreviewSiteLogoUrl();
        author = daCardActivity.getCardPreviewAuthorTextView();
        time = daCardActivity.getCardPreviewTimeTextView();


        Picasso.get().load(articleImage).resize(1280, 720).centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(holder.cardPreviewArticleImage);
        Picasso.get().load(logo).into(holder.cardPreviewSiteLogo);
        holder.cardPreviewAuthorTextView.setText(author);
        holder.cardPreviewTimeTextView.setText(time);
        holder.cardPreviewSubtitle.setText(subtitle);
        holder.cardPreviewHeadline.setText(headline);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAbsoluteAdapterPosition();
                DaCardActivity daCardActivity = (DaCardActivity) localArrayList.get(pos);
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, ArticleActivity.class);
                intent.putExtra("AUTHOR", daCardActivity.getCardPreviewAuthorTextView());
                intent.putExtra("TIME", daCardActivity.getCardPreviewTimeTextView());
                intent.putExtra("SUBTITLE", daCardActivity.getCardPreviewSubtitleTextView());
                intent.putExtra("HEADLINE", daCardActivity.getCardPreviewHeadlineTextView());
                intent.putExtra("ARTICLE_IMAGE", daCardActivity.getCardPreviewArticleImageUrl());
                intent.putExtra("SITE_LOGO", daCardActivity.getCardPreviewSiteLogoUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cardPreviewHeadline;
        TextView cardPreviewSubtitle;
        TextView cardPreviewTimeTextView;
        TextView cardPreviewAuthorTextView;
        ImageView cardPreviewArticleImage;
        ImageView cardPreviewSiteLogo;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            context = itemView.getContext();
            cardPreviewArticleImage = itemView.findViewById(R.id.cardPreviewArticleImage);
            cardPreviewSiteLogo = itemView.findViewById(R.id.cardPreviewSiteLogo);
            cardPreviewAuthorTextView = itemView.findViewById(R.id.cardPreviewAuthorTextView);
            cardPreviewTimeTextView = itemView.findViewById(R.id.cardPreviewTimeTextView);
            cardPreviewSubtitle = itemView.findViewById(R.id.cardPreviewSubtitleTextView);
            cardPreviewHeadline = itemView.findViewById(R.id.cardPreviewHeadlineTextView);
        }
    }
}