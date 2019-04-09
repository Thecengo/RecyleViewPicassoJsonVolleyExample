package com.example.recyleviewjsonvolleyexample;

public class CardViewItem {

    private String mImageUrl;
    private String mCreator;
    private int likeCount;

    public CardViewItem(String mImageUrl,String mCreator,int likeCount){
        this.mImageUrl=mImageUrl;
        this.mCreator=mCreator;
        this.likeCount=likeCount;
    }
    public String getmImageUrl(){
        return mImageUrl;
    }
    public String getmCreator(){
        return this.mCreator;
    }
    public int getLikeCount(){
        return this.likeCount;
    }
}
