package com.example.android.mybakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.mybakingapp.data.BakingRecipe;
import com.example.android.mybakingapp.data.Ingredients;
import com.example.android.mybakingapp.data.Steps;
import com.example.android.mybakingapp.utilities.IngredientsAdapter;
import com.example.android.mybakingapp.utilities.StepsAdapter;

import java.util.ArrayList;

public class EachRecipeFragment extends Fragment{

    //https://medium.com/@Pang_Yao/android-fragment-use-recyclerview-cardview-4bc10beac446

    final static String CLASS_NAME = EachRecipeFragment.class.getSimpleName();

    private RecyclerView stepsRecyclerView;
    private RecyclerView ingredientsRecyclerView;

    private IngredientsAdapter mIngredientsAdapter;
    private StepsAdapter mStepsAdapter;

    private RecyclerView.LayoutManager stepsLayoutManager;
    private RecyclerView.LayoutManager ingredientsLayoutManager;

    private ArrayList<Ingredients> myIngredients;
    private ArrayList<Steps> mySteps;
    private BakingRecipe myBakingRecipe;

    public EachRecipeFragment() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent childIntent = getActivity().getIntent();
        if (childIntent.hasExtra(Intent.EXTRA_TEXT)) {
            myBakingRecipe = (BakingRecipe) childIntent.getParcelableExtra(Intent.EXTRA_TEXT);

            Log.i(CLASS_NAME, "recipe is " + myBakingRecipe.getName());

            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(myBakingRecipe.getName());

            myIngredients = myBakingRecipe.getIngredients();
            mySteps = myBakingRecipe.getSteps();
            Log.i(CLASS_NAME, "Size of my steps is " + Integer.toString(mySteps.size()));
        }
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

    }


}