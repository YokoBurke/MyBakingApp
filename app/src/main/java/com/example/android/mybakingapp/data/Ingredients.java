package com.example.android.mybakingapp.data;

public class Ingredients {

    String quantity;

    String measure;
    String ingredient;

    public Ingredients (String myQuantity, String myMeasure, String myIngredient){

        quantity = myQuantity;
        measure = myMeasure;
        ingredient = myIngredient;
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
}
