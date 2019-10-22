package com.example.android.mybakingapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class BakingRecipe implements Parcelable {

    int id;
    String name;
    ArrayList<Ingredients> ingredients;
    ArrayList<Steps> steps;
    int servings;

    public BakingRecipe (int myID, String myName, ArrayList<Ingredients> myIngredients, ArrayList<Steps> mySteps, int myServings){
        id = myID;
        name = myName;
        ingredients = myIngredients;
        steps = mySteps;
        servings = myServings;
    }

    public BakingRecipe(Parcel inParcel) {
        id = inParcel.readInt();
        name = inParcel.readString();
        ingredients = inParcel.readArrayList(com.example.android.mybakingapp.data.Ingredients.class.getClassLoader());
        steps = inParcel.readArrayList(com.example.android.mybakingapp.data.Steps.class.getClassLoader());
        servings = inParcel.readInt();
    }


    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Ingredients> getIngredients(){
        return ingredients;
    }

    public ArrayList<Steps> getSteps(){
        return steps;
    }

    public int getServings(){
        return servings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeList(ingredients);
        parcel.writeList(steps);
        parcel.writeInt(servings);

    }

    public static final Parcelable.Creator<BakingRecipe> CREATOR = new Parcelable.Creator<BakingRecipe>() {
        public BakingRecipe createFromParcel(Parcel in){
            return new BakingRecipe(in);
        }

        public BakingRecipe[] newArray(int size) {
            return new BakingRecipe[size];
        }

    };
}
