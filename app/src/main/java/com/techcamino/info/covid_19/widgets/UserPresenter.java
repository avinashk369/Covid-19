package com.techcamino.info.covid_19.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.otaliastudios.autocomplete.RecyclerViewPresenter;
import com.techcamino.info.covid_19.R;
import com.techcamino.info.covid_19.details.CountryDetails;

import java.util.ArrayList;


public class UserPresenter extends RecyclerViewPresenter<CountryDetails> {

    @SuppressWarnings("WeakerAccess")
    protected Adapter adapter;

    @SuppressWarnings("WeakerAccess")
    public UserPresenter(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    protected PopupDimensions getPopupDimensions() {
        PopupDimensions dims = new PopupDimensions();
        dims.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        dims.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        return dims;
    }

    @NonNull
    @Override
    protected RecyclerView.Adapter instantiateAdapter() {
        adapter = new Adapter();
        return adapter;
    }

    @Override
    protected void onQuery(@Nullable CharSequence query) {
        ArrayList<CountryDetails> all = User.generateCountries();
        if (TextUtils.isEmpty(query)) {
            adapter.setData(all);
        } else {
            query = query.toString().toLowerCase();
            ArrayList<CountryDetails> list = new ArrayList<>();
            for (CountryDetails u : all) {
                if (u.getName().toLowerCase().contains(query)) {
                    list.add(u);
                }
            }
            adapter.setData(list);
            Log.e("UserPresenter", "found "+list.size()+" users for query "+query);
        }
        adapter.notifyDataSetChanged();
    }

    protected class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        private ArrayList<CountryDetails> data;

        @SuppressWarnings("WeakerAccess")
        protected class Holder extends RecyclerView.ViewHolder {
            private View root;
            private TextView fullname;
            private TextView username;
            Holder(View itemView) {
                super(itemView);
                root = itemView;
                fullname = itemView.findViewById(R.id.fullname);
            }
        }

        @SuppressWarnings("WeakerAccess")
        protected void setData(@Nullable ArrayList<CountryDetails> data) {
            this.data = data;
        }

        @Override
        public int getItemCount() {
            return (isEmpty()) ? 1 : data.size();
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(getContext()).inflate(R.layout.user, parent, false));
        }

        private boolean isEmpty() {
            return data == null || data.isEmpty();
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            if (isEmpty()) {
                holder.fullname.setText("No matching country found!");
                holder.root.setOnClickListener(null);
                return;
            }
            final CountryDetails user = data.get(position);
            holder.fullname.setText(user.getName());
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dispatchClick(user);
                }
            });
        }
    }
}
