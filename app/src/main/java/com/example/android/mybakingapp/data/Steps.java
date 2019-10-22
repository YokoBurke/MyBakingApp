package com.example.android.mybakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Steps implements Parcelable {

    int id;
    String shortDescription;
    String description;
    String videoURL;

    public Steps (int myId, String myShortDescription, String myDescription, String myVideoUrl){
        id = myId;
        shortDescription = myShortDescription;
        description = myDescription;
        videoURL = myVideoUrl;
    }


    public Steps (Parcel inParcel){
        id = inParcel.readInt();
        shortDescription = inParcel.readString();
        description = inParcel.readString();
        videoURL = inParcel.readString();
    }
    public int getId(){
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription(){
        return description;
    }

    public String getVideoURL(){return videoURL;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(shortDescription);
        parcel.writeString(description);
        parcel.writeString(videoURL);
    }


    public static final Parcelable.Creator<Steps> CREATOR
            = new Parcelable.Creator<Steps>(){
        public Steps createFromParcel(Parcel in){
            return new Steps(in);
        }

        public Steps[] newArray(int size) {
            return new Steps[size];        }
    };
}
