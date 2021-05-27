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
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class YourSubsAdapter  extends RecyclerView.Adapter<YourSubsAdapter.ViewHolder> {

    //local vars
    Context context;
    int articleImage;
    int logo;
    ArrayList localArrayList;

    public YourSubsAdapter (Context ct, ArrayList arrayList){
        context = ct;
        localArrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.your_subs_cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YourSubsAdapter.ViewHolder holder, int position) {
        DaSubActivity daSubActivity = (DaSubActivity) localArrayList.get(position);
        logo = daSubActivity.getLogoImage();
        Picasso.get().load(logo).resize(120, 120).centerCrop().into(holder.yourSubsLogo);
    }


    @Override
    public int getItemCount() {
        return localArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView yourSubsLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            yourSubsLogo = itemView.findViewById(R.id.yourSubsIconImageView);
        }
    }



}
