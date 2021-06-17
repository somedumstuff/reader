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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.realm.Realm;

public class YourResultsAdapter extends RecyclerView.Adapter<YourResultsAdapter.ViewHolder>{

    //local vars
    String resultRssLink;
    String resultSiteLink;
    String resultSiteDescritpion;
    String logo;
    Context context;
    ArrayList<YourResultCard> localArrayList;

    public YourResultsAdapter(Context ct, ArrayList<YourResultCard> yourResultsList){
        context = ct;
        localArrayList = yourResultsList;
    }

    @NonNull
    @Override
    public YourResultsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.result_card, parent, false);
        return new YourResultsAdapter.ViewHolder(view);
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
    public void onBindViewHolder(@NonNull final YourResultsAdapter.ViewHolder holder, int position) {
        YourResultCard yourResults = (YourResultCard) localArrayList.get(position);
        resultRssLink = yourResults.getResultRssLink();
        resultSiteLink = yourResults.getResultSiteLink();
        resultSiteDescritpion = yourResults.getResultSiteDesc();
        logo = yourResults.getResultSiteLogo();
        Log.v("logo", logo);

        holder.resultRssLink.setText(resultRssLink);
        holder.resultSiteLink.setText(resultSiteLink);
        Picasso.get().load(logo).into(holder.resultSiteLogo);

        holder.resultAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddedLinkDBHelper helper = new AddedLinkDBHelper(Realm.getDefaultInstance());
                helper.addNewLink(resultRssLink, resultSiteDescritpion);
                Toast.makeText(context.getApplicationContext(), "Added Link", Toast.LENGTH_LONG).show();
                ((YourResults)context).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return localArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView resultRssLink;
        TextView resultSiteLink;
        TextView resultRssDescription;
        ImageView resultSiteLogo;
        ImageView resultAddButton;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            context = itemView.getContext();
            resultSiteLogo = itemView.findViewById(R.id.resultSiteLogo);
            resultRssLink = itemView.findViewById(R.id.resultRssLink);
            resultSiteLink = itemView.findViewById(R.id.resultSiteLink);
            resultRssDescription = itemView.findViewById(R.id.resultRssDescription);
            resultAddButton = itemView.findViewById(R.id.buttonToAdd);
        }
    }
}