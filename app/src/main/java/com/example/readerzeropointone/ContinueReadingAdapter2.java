package com.example.readerzeropointone;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Debug;
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

import java.net.URI;

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
                public void onClick(View v) {
                    try {
                        Uri uri = Uri.parse(card.getArticleLink());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        context.startActivity(intent);
                        CardDBHelper helper = new CardDBHelper(Realm.getDefaultInstance());
                        helper.deleteCard(card.getHeadline());
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Something went wrong....", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
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
