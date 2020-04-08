package com.techcamino.info.covid_19.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techcamino.info.covid_19.R;
import com.techcamino.info.covid_19.details.TweetsDetails;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.MemberView> {

    private List<TweetsDetails> tweetsDetailsList;
    private Context context;
    private PlaceInfoListener placeInfoListener;

    public interface PlaceInfoListener {
        void onPopupMenuClick(TweetsDetails tweetsDetails, View view);
    }

    @NonNull
    @Override
    public MemberView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tweeter_item_list, viewGroup, false);

        return new MemberView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberView unclearedView, final int i) {

        try{
            unclearedView.userName.setText(tweetsDetailsList.get(i).getUserDetails().getName());
            unclearedView.userId.setText("@"+tweetsDetailsList.get(i).getUserDetails().getScreen_name());
            unclearedView.tweetTime.setText(timeString(tweetsDetailsList.get(i).getCreatedAt()));
            unclearedView.tweets.setText(tweetsDetailsList.get(i).getText());
            unclearedView.tweetsLikeCount.setText(tweetsDetailsList.get(i).getFavCount());
            Picasso.get()
                    .load(tweetsDetailsList.get(i).getUserDetails().getProfileImageUrl())
                    .into(unclearedView.userImage);
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
        return tweetsDetailsList.size();
    }

    public class MemberView extends RecyclerView.ViewHolder {

        public TextView userName,userId,tweetTime,tweets,tweetsLikeCount;
        public ImageView tweetImage;
        public CircleImageView userImage;

        public MemberView(View view) {
            super(view);

            userName = view.findViewById(R.id.user_name);
            userId = view.findViewById(R.id.user_id);
            tweetTime =  view.findViewById(R.id.tweet_time);
            tweetImage = view.findViewById(R.id.tweet_image);
            userImage = view.findViewById(R.id.user_image);
            tweets = view.findViewById(R.id.tweets);
            tweetsLikeCount = view.findViewById(R.id.tweets_likes_count);

        }
    }

    public TweetsAdapter(List<TweetsDetails> tweetsDetailsList, Context context) {
        this.tweetsDetailsList = tweetsDetailsList;
        this.context = context;
        this.placeInfoListener = (PlaceInfoListener)context;
    }


}
