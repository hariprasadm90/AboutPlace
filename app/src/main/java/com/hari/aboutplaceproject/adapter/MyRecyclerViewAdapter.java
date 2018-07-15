package com.hari.aboutplaceproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hari.aboutplaceproject.R;
import com.hari.aboutplaceproject.model.ListItem;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<ListItem> mDataItems;
    private LayoutInflater mLayoutInflater;

    public MyRecyclerViewAdapter(Context context, List<ListItem> dataItem) {
        this.mContext = context;
        this.mDataItems = dataItem;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate view to the viewholder
        View v = mLayoutInflater.inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //getting position of each list item in the arraylist
        ListItem listItem = mDataItems.get(position);
   }

    @Override
    public int getItemCount() {
        return mDataItems.size();
    }

    //viewholder inner class
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView title_text;
        private final TextView description_text;
        private final ImageView display_image;

        public MyViewHolder(View itemView) {
            super(itemView);

            title_text = (TextView) itemView.findViewById(R.id.title_text);
            description_text = (TextView) itemView.findViewById(R.id.description_text);
            display_image = (ImageView) itemView.findViewById(R.id.display_imageView);

        }
    }
}
