package com.kalpana.prasun.message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Himanshu Singh on 23-Oct-17.
 */

public class ContentRecyclerAdapter extends RecyclerView.Adapter<ContentRecyclerAdapter.ContentViewHolder> {
    private ArrayList<Data> listMessage;
    private Context mContext;
    private ArrayList<Data> mFilteredList;

    public ContentRecyclerAdapter(ArrayList<Data> listMessage, Context mContext){
        this.listMessage=listMessage;
        this.mContext = mContext;
        this.mFilteredList= listMessage;
    }


    public class ContentViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewMessage;


        public ContentViewHolder(View view) {
            super(view);
            textViewMessage = (TextView) view.findViewById(R.id.textView);


        }
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_recycler, parent, false);

        return new ContentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ContentViewHolder holder, int position) {
        holder.textViewMessage.setText(listMessage.get(position).getMessage());
    }


    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

}




