package com.example.recyleviewjsonvolleyexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewAdapterHolder> {

    private Context context;
    private ArrayList<CardViewItem> cardViewItemList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener{
        void  onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener=listener;
    }

    public RecycleViewAdapter(Context context,ArrayList<CardViewItem> cardViewItemList){
        this.context=context;
        this.cardViewItemList=cardViewItemList;

    }

    @NonNull
    @Override
    public RecycleViewAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
        return new RecycleViewAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapterHolder recycleViewAdapterHolder, int position) {
        CardViewItem currentItem = cardViewItemList.get(position);

        String imageUrl = currentItem.getmImageUrl();
        String creatorName = currentItem.getmCreator();
        int likeCount = currentItem.getLikeCount();

        recycleViewAdapterHolder.mTextViewCreator.setText(creatorName);
        recycleViewAdapterHolder.mTextViewLikes.setText("Likes: "+likeCount);
        Picasso.get().load(imageUrl).fit().centerInside().into(recycleViewAdapterHolder.imageViewViewer);


    }

    @Override
    public int getItemCount() {
        return this.cardViewItemList.size();
    }


    public class RecycleViewAdapterHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewViewer;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;

        public RecycleViewAdapterHolder(@NonNull View itemView) {
            super(itemView);
            imageViewViewer = itemView.findViewById(R.id.imageViewViewİmage);
            mTextViewCreator = itemView.findViewById(R.id.textViewCreator);
            mTextViewLikes = itemView.findViewById(R.id.textViewLike);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mOnItemClickListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

}
