package com.example.android.mybakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredients implements Parcelable {

    String quantity;

    String measure;
    String ingredient;

    public Ingredients (String myQuantity, String myMeasure, String myIngredient){

        quantity = myQuantity;
        measure = myMeasure;
        ingredient = myIngredient;
    }

    private Ingredients (Parcel inParcel){
        quantity = inParcel.readString();
        measure = inParcel.readString();
        ingredient = inParcel.readString();
    }

    public String getQuantity() {
        return quantity;
    }

    public String getMeasure(){
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(quantity);
        parcel.writeString(measure);
        parcel.writeString(ingredient);
    }

    public static final Parcelable.Creator<Ingredients> CREATOR
            = new Parcelable.Creator<Ingredients>(){
        public Ingredients createFromParcel(Parcel in){
            return new Ingredients(in);
        }

        public Ingredients[] newArray(int size){
            return new Ingredients[size];
        }
    };
}
