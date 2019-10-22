package com.example.android.mybakingapp.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.mybakingapp.R;
import com.example.android.mybakingapp.data.Ingredients;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder> {

    private static final String LOG_TAG = IngredientsAdapter.class.getSimpleName();
    private List<Ingredients> myIngredients;
    private Context myContext;

    public IngredientsAdapter(Context mContext, List<Ingredients> mIngredients){
        myContext = mContext;
        myIngredients = mIngredients;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView ingredientsTextView;
        public TextView measureTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            measureTextView = (TextView) itemView.findViewById(R.id.text_measure);
            ingredientsTextView = (TextView) itemView.findViewById(R.id.text_ingredient);
        }
    }

    @NonNull
    @Override
    public IngredientsAdapter.MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        int myLayoutId = R.layout.ingredients_item;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(myLayoutId, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder (@NonNull IngredientsAdapter.MyViewHolder holder, int position) {
        String myQuantity = myIngredients.get(position).getQuantity();
        String myMeasure = myIngredients.get(position).getMeasure();
        String myIngredient = myIngredients.get(position).getIngredient();
        holder.measureTextView.setText(myQuantity + " " + myMeasure.toLowerCase());
        holder.ingredientsTextView.setText(myIngredient);

    }

    @Override
    public int getItemCount() {
        if (myIngredients == null){
            return 0;
        }

        return myIngredients.size();
    }
}
