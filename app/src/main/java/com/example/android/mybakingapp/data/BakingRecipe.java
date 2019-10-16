package com.example.android.mybakingapp.data;

import java.util.ArrayList;

public class BakingRecipe {

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
}
