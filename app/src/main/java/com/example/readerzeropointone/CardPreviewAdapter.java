package com.example.readerzeropointone;

import android.content.Context;
import android.content.Intent;
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
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CardPreviewAdapter  extends RecyclerView.Adapter<CardPreviewAdapter.ViewHolder> {

    //local vars
    String headline;
    String subtitle;
    int articleImage;
    int logo;
    String author;
    String time;
    Context context;
    Bitmap bitmap;
    ArrayList<DaCardActivity> localArrayList;


    public CardPreviewAdapter(Context ct, ArrayList arrayList){
        context = ct;
        localArrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cards, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final CardPreviewAdapter.ViewHolder holder, int position) {
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

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
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
