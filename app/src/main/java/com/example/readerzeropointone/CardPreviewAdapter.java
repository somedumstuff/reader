package com.example.readerzeropointone;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.realm.Realm;

public class CardPreviewAdapter  extends RecyclerView.Adapter<CardPreviewAdapter.ViewHolder> {

    //local vars
    String headline;
    String subtitle;
    String articleImage;
    String logo;
    String author;
    String time;
    Context context;
    Bitmap bitmap;
    ArrayList<DaCardActivity> localArrayList;
    ContinueReadingAdapter2 adapter2;

    public CardPreviewAdapter(Context ct, ArrayList arrayList, ContinueReadingAdapter2 adapter2){
        context = ct;
        localArrayList = arrayList;
        this.adapter2 = adapter2;
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
        if(subtitle == null || subtitle.equals(""))
            holder.cardPreviewSubtitle.setVisibility(View.INVISIBLE);
        holder.cardPreviewHeadline.setText(headline);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url = daCardActivity.getCardPreviewLink();
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        //Background work here
                        StringBuilder article = new StringBuilder();
                        String queryTag = "p";
                        boolean noClasses = false;

                        if(url.contains("https://indianexpress.com"))
                            noClasses = true;
                        else if (url.contains("https://www.nytimes.com/"))
                            queryTag = "p.css-axufdj.evys1bk0";
                        else if (url.contains("https://www.politico.com/"))
                            queryTag = "p.story-text__paragraph";
                        else if (url.contains("https://www.latimes.com/"))
                            noClasses = true;

                        try {
                            final Document document = Jsoup.connect(url).get();

                            for(Element p : document.select(queryTag)) {
                                if(!noClasses || p.className().equals("")){
                                    article.append(p.text());
                                    Log.v("para", queryTag + p.text());
                                }
                            }
                            Log.v("para", url);
                            Log.v("Jsoup", String.valueOf(document));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //UI Thread work here
                                String articleText = article.toString();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Context context = holder.itemView.getContext();
                                        Intent intent = new Intent(context, ArticleActivity.class);
                                        intent.putExtra("AUTHOR", daCardActivity.getCardPreviewAuthorTextView());
                                        intent.putExtra("TIME", daCardActivity.getCardPreviewDate());
                                        intent.putExtra("SUBTITLE", daCardActivity.getCardPreviewSubtitleTextView());
                                        intent.putExtra("HEADLINE", daCardActivity.getCardPreviewHeadlineTextView());
                                        intent.putExtra("ARTICLE_IMAGE", daCardActivity.getCardPreviewArticleImageUrl());
                                        intent.putExtra("SITE_LOGO", daCardActivity.getCardPreviewSiteLogoUrl());
                                        intent.putExtra("ARTICLE_LINK", daCardActivity.getCardPreviewLink());
                                        intent.putExtra("ARTICLE_PARA", articleText);
                                        context.startActivity(intent);
                                    }
                                }, 800);
                            }
                        });
                    }
                });
            }

//            public void onClick(View v) {
//
//                Uri uri = Uri.parse(daCardActivity.getCardPreviewLink());
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                context.startActivity(intent);
//                CardDBHelper helper = new CardDBHelper(Realm.getDefaultInstance());
//                helper.addCard(daCardActivity.getCardPreviewHeadlineTextView(), daCardActivity.getCardPreviewSubtitleTextView(),
//                        daCardActivity.getCardPreviewTimeTextView(), daCardActivity.getCardPreviewAuthorTextView(), daCardActivity.getCardPreviewSiteLogoUrl(),
//                        daCardActivity.getCardPreviewArticleImageUrl(), daCardActivity.getCardPreviewLink());
//                adapter2.notifyDataSetChanged();
////                int pos = holder.getAbsoluteAdapterPosition();
////                DaCardActivity daCardActivity = (DaCardActivity) localArrayList.get(pos);
////                Context context = holder.itemView.getContext();
////                Intent intent = new Intent(context, ArticleActivity.class);
////                intent.putExtra("AUTHOR", daCardActivity.getCardPreviewAuthorTextView());
////                intent.putExtra("TIME", daCardActivity.getCardPreviewTimeTextView());
////                intent.putExtra("SUBTITLE", daCardActivity.getCardPreviewSubtitleTextView());
////                intent.putExtra("HEADLINE", daCardActivity.getCardPreviewHeadlineTextView());
////                intent.putExtra("ARTICLE_IMAGE", daCardActivity.getCardPreviewArticleImageUrl());
////                intent.putExtra("SITE_LOGO", daCardActivity.getCardPreviewSiteLogoUrl());
////                context.startActivity(intent);
//            }
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
