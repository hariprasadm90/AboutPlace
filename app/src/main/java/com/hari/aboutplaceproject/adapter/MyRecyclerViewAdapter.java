package com.hari.aboutplaceproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hari.aboutplaceproject.R;
import com.hari.aboutplaceproject.model.ListItem;
import com.hari.aboutplaceproject.utils.GlideApp;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<ListItem> mDataItems;
    private LayoutInflater mLayoutInflater;
    private ClickListener mClickListener;

    public MyRecyclerViewAdapter(Context context, List<ListItem> dataItem) {
        this.mContext = context;
        this.mDataItems = dataItem;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    // Clean all elements of the recyclerview
    public void clear() {
        mDataItems.clear();
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(ClickListener clickListener) {
        this.mClickListener = clickListener;
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

        //checking for null values in title
        if (listItem.getTitle().equals("null")) {
            holder.title_text.setText(mContext.getResources().getString(R.string.no_title));
        } else {
            //setting the title data to view
            holder.title_text.setText(listItem.getTitle());
        }
        //checking for null values in description
        if (listItem.getDescription().equals("null")) {
            holder.description_text.setText(mContext.getResources().getString(R.string.no_description));
        } else {
            //setting the description data to view
            holder.description_text.setText(listItem.getDescription());
        }
        //setting the image url data to view(lazy loading) using glide image loading library
        GlideApp.with(mContext).load(listItem.getImageHref()).placeholder(ContextCompat.getDrawable(mContext, R.drawable.no_image)).override(mContext.getResources().getInteger(R.integer.image_size)).into(holder.display_image);
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

            title_text = itemView.findViewById(R.id.title_text);
            description_text = itemView.findViewById(R.id.description_text);
            display_image = itemView.findViewById(R.id.display_imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(getAdapterPosition(), v);
                }
            });
        }
    }
    public interface ClickListener {

        void onItemClick(int position, View view);
    }

}
