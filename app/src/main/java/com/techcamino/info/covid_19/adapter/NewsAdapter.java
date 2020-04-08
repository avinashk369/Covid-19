package com.techcamino.info.covid_19.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techcamino.info.covid_19.R;
import com.techcamino.info.covid_19.details.ArticleDetails;
import com.techcamino.info.covid_19.details.TweetsDetails;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MemberView> {

    private List<ArticleDetails> articleDetailsList;
    private Context context;
    private PlaceInfoListener placeInfoListener;

    public interface PlaceInfoListener {
        void onPopupMenuClick(ArticleDetails articleDetails, View view);
    }

    @NonNull
    @Override
    public MemberView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item_list, viewGroup, false);

        return new MemberView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MemberView unclearedView, final int i) {

        try{
            unclearedView.newsTitle.setText(articleDetailsList.get(i).getTitle());
            unclearedView.newsSource.setText("@"+articleDetailsList.get(i).getSourceDetails().getName());
            unclearedView.newsPublishedAt.setText(articleDetailsList.get(i).getPublishedAt().substring(0,10));
            Picasso.get()
                    .load(articleDetailsList.get(i).getUrlToImage())
                    .into(unclearedView.newsImage);
            unclearedView.newsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    placeInfoListener.onPopupMenuClick(articleDetailsList.get(i),unclearedView.newsLayout);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private String timeString(String createdAt){
        String timeString="";
        //Sat Mar 28 18:44:17 +0000 2020
        String[] yearSplit = createdAt.split("\\+");
        String[] dateSplit = yearSplit[0].split(" ");
        timeString = dateSplit[1]+" "+dateSplit[2]+" "+yearSplit[1].split(" ")[1]+" "+dateSplit[3];
        return timeString;
    }

    @Override
    public int getItemCount() {
        return articleDetailsList.size();
    }

    public class MemberView extends RecyclerView.ViewHolder {

        public TextView newsTitle,newsPublishedAt,newsSource;
        public ImageView newsImage;
        public RelativeLayout newsLayout;

        public MemberView(View view) {
            super(view);

            newsTitle = view.findViewById(R.id.news_title);
            newsPublishedAt = view.findViewById(R.id.news_published_at);
            newsSource =  view.findViewById(R.id.news_source);
            newsImage = view.findViewById(R.id.news_image);
            newsLayout = view.findViewById(R.id.news_layout);

        }
    }

    public NewsAdapter(List<ArticleDetails> articleDetailsList, Context context) {
        this.articleDetailsList = articleDetailsList;
        this.context = context;
        this.placeInfoListener = (PlaceInfoListener)context;
    }


}
