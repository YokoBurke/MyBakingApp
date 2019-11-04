package com.example.android.mybakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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
    private String myRecipeName;

    OnStepClickListener stepCallBack;

    public void setOnStepClickListener (OnStepClickListener callback){
        stepCallBack = callback;
    }


    public interface OnStepClickListener {
        void onStepSelected(int Position);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try {
            stepCallBack = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must imlement OnStepClickListener");
        }
    }



    public EachRecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent childIntent = getActivity().getIntent();
        if (childIntent.hasExtra(Intent.EXTRA_TEXT)) {
            myBakingRecipe = (BakingRecipe) childIntent.getParcelableExtra(Intent.EXTRA_TEXT);
            myRecipeName = myBakingRecipe.getName();
            Log.i(CLASS_NAME, "recipe is " + myRecipeName);

            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(myBakingRecipe.getName());

            myIngredients = myBakingRecipe.getIngredients();
            mySteps = myBakingRecipe.getSteps();
            //Log.i(CLASS_NAME, "Size of my steps is " + Integer.toString(mySteps.size()));
        }
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view =inflater.inflate(R.layout.fragment_each_recipe, container, false);

        ingredientsRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_ingredient);
        ingredientsRecyclerView.setHasFixedSize(false);
        ingredientsRecyclerView.setNestedScrollingEnabled(false);

        stepsRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_steps);
        stepsRecyclerView.setHasFixedSize(false);
        stepsRecyclerView.setNestedScrollingEnabled(false);

            ingredientsLayoutManager = new LinearLayoutManager(getActivity());
            stepsLayoutManager = new LinearLayoutManager(getActivity());
            mIngredientsAdapter = new IngredientsAdapter(getActivity(), myIngredients);

            mStepsAdapter = new StepsAdapter(getActivity(), mySteps, new StepsAdapter.ListItemClickListner() {
                @Override
                public void onListItemClick(int clickedItemIndex) {

                }
            }, myRecipeName);

            Log.i(CLASS_NAME, "Size of my steps is " + Integer.toString(mySteps.size()));

            ingredientsRecyclerView.setLayoutManager(ingredientsLayoutManager);
            ingredientsRecyclerView.setAdapter(mIngredientsAdapter);

            stepsRecyclerView.setLayoutManager(stepsLayoutManager);
            stepsRecyclerView.setAdapter(mStepsAdapter);

            return view;
        }


}