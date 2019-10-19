package com.example.android.mybakingapp.utilities;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.mybakingapp.IngredientStepsActivity;
import com.example.android.mybakingapp.R;
import com.example.android.mybakingapp.data.BakingRecipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    private static final String LOG_TAG = RecipeAdapter.class.getSimpleName();
    private List<BakingRecipe> myBakingRecipe;
    private Context myContext;
    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public RecipeAdapter (Context mContext, List<BakingRecipe> mBakingRecipe, ListItemClickListener mListener) {
        myBakingRecipe = mBakingRecipe;
        myContext = mContext;
        mOnClickListener = mListener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView recipeNameTextView;
        public TextView servingsTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            recipeNameTextView = (TextView) itemView.findViewById(R.id.recipe_name);
            servingsTextView = (TextView) itemView.findViewById(R.id.recipe_serving_number);
            itemView.setOnClickListener(this);

        }

        public void onClick(View v){
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);

            Intent intent = new Intent(myContext, IngredientStepsActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, (Parcelable) myBakingRecipe.get(clickedPosition));

            myContext.startActivity(intent);
        }

    }

    @NonNull
    @Override
    public RecipeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int myLayoutId = R.layout.main_card_item;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(myLayoutId, parent, false);
        MyViewHolder recipeViewHolder = new MyViewHolder(itemView);
        return recipeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.MyViewHolder holder, int position) {

        String myRecipeName = myBakingRecipe.get(position).getName();
        String myServingNumber = Integer.toString(myBakingRecipe.get(position).getServings());

        Log.i(LOG_TAG, myRecipeName + "seriouisly!");

        holder.recipeNameTextView.setText(myRecipeName);
        holder.servingsTextView.setText(myServingNumber);
    }

    @Override
    public int getItemCount() {
        if (myBakingRecipe == null) {
            return 0;
        }
        return myBakingRecipe.size();
    }

}
