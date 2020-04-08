package com.techcamino.info.covid_19.feed;

import android.content.Context;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.ChildPosition;
import com.mindorks.placeholderview.annotations.expand.ParentPosition;
import com.techcamino.info.covid_19.R;
import com.techcamino.info.covid_19.feed.data.Info;

@Layout(R.layout.feed_item)
public class InfoView {

    @ParentPosition
    int mParentPosition;

    @ChildPosition
    int mChildPosition;

    @View(R.id.titleTxt)
    TextView titleTxt;

    private Info mInfo;
    private Context mContext;

    public InfoView(Context context, Info info) {
        mContext = context;
        mInfo = info;
    }

    @Resolve
    public void onResolved() {
        titleTxt.setText(mInfo.getTitle());
    }
}
