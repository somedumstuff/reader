package com.example.readerzeropointone;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class ContinueReadingAdapter2 extends RealmRecyclerViewAdapter<CardDB, ContinueReadingAdapter2.ViewHolder>{

    Context context;

    public ContinueReadingAdapter2(Context ct, OrderedRealmCollection<CardDB> data) {
        super(data, true);
        context = ct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.continue_reading_cards, parent, false);
        return new ContinueReadingAdapter2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardDB card = getItem(position);

        if (card != null) {
            Picasso.get().load(card.getArticleImageLink()).resize(1280, 720).centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(holder.cardPreviewArticleImage);
            Picasso.get().load(card.getSiteLogoImageLink()).into(holder.cardPreviewSiteLogo);
            holder.cardPreviewAuthorTextView.setText(card.getAuthor());
            holder.cardPreviewTimeTextView.setText(card.getTime());
            holder.cardPreviewSubtitle.setText(card.getSubtitle());
            holder.cardPreviewHeadline.setText(card.getHeadline());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
//                public void onClick(View v) {
//                    try {
////                        Uri uri = Uri.parse(card.getArticleLink());
////                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
////                        context.startActivity(intent);
//                        getArticle(card.getArticleLink());
//
////                        CardDBHelper helper = new CardDBHelper(Realm.getDefaultInstance());
////                        helper.deleteCard(card.getHeadline());
//                    } catch (ActivityNotFoundException e) {
//                        e.printStackTrace();
//                        Toast.makeText(context, "Something went wrong....", Toast.LENGTH_LONG).show();
//                    }
//                }

                public void onClick(View v) {
                    final String url = card.getArticleLink();
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
                                    if((noClasses && p.className() == "") || !noClasses){
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
                                            intent.putExtra("AUTHOR", card.getAuthor());
                                            intent.putExtra("TIME", card.getTime());
                                            intent.putExtra("SUBTITLE", card.getSubtitle());
                                            intent.putExtra("HEADLINE", card.getHeadline());
                                            intent.putExtra("ARTICLE_IMAGE", card.getArticleImageLink());
                                            intent.putExtra("SITE_LOGO", card.getSiteLogoImageLink());
                                            intent.putExtra("ARTICLE_LINK", card.getArticleLink());
                                            intent.putExtra("ARTICLE_PARA", articleText);
                                            context.startActivity(intent);
                                        }
                                    }, 800);
                                }
                            });
                        }
                    });
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cardPreviewHeadline;
        TextView cardPreviewSubtitle;
        TextView cardPreviewTimeTextView;
        TextView cardPreviewAuthorTextView;
        ImageView cardPreviewArticleImage;
        ImageView cardPreviewSiteLogo;
        public ViewHolder(@NonNull View itemView) {
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
