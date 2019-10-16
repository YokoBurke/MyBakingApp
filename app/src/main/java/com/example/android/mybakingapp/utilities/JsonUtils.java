package com.example.android.mybakingapp.utilities;

import com.example.android.mybakingapp.data.BakingRecipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    List<BakingRecipe> myBakingRecipe;

    public JsonUtils() {
        myBakingRecipe = new ArrayList<BakingRecipe>();
    }

    public static List<BakingRecipe> parseJson(String response){

        Gson gson = new GsonBuilder().create();
        List<BakingRecipe> list = gson.fromJson(response, new TypeToken<List<BakingRecipe>>(){}.getType());

        return list;

    }
}
