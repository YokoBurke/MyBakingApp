package com.example.android.mybakingapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.mybakingapp.data.BakingRecipe;
import com.example.android.mybakingapp.utilities.JsonUtils;
import com.example.android.mybakingapp.utilities.NetworkUtils;
import com.example.android.mybakingapp.utilities.RecipeAdapter;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    final static String CLASS_NAME = MainActivity.class.getSimpleName();

    private static final int SEARCH_LOADER = 125;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<BakingRecipe> myRecipeList;
    private RecipeAdapter mAdapter;
    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_recipe_card);
        recyclerView.setHasFixedSize(true);

        isTablet = getResources().getBoolean(R.bool.is_tablet);

        if (isTablet) {
            mLayoutManager = new GridLayoutManager(this, 3);
        } else {
            //mLayoutManager = new LinearLayoutManager(this);
           // mLayoutManager = new GridLayoutManager(this, GridLayoutManager.VERTICAL); }
            mLayoutManager = new GridLayoutManager(this, 1);

        }


        recyclerView.setLayoutManager(mLayoutManager);

        getSupportLoaderManager().initLoader(SEARCH_LOADER, null, this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {

        return new AsyncTaskLoader<String>(this) {
            String mRecipeJson;

            @Override
            public void onStartLoading() {
                if (mRecipeJson != null) {
                    deliverResult(mRecipeJson);
                } else {
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public String loadInBackground() {
                URL recipeUrl = NetworkUtils.checkURL();
                String myString = "";
                try {
                    myString = NetworkUtils.getResponseFromHttpUrl(recipeUrl);


                } catch (IOException e) {
                    Log.e("Main Activity", "Problem making the HTTP request.", e);
                }
                return myString;
            }

            @Override
            public void deliverResult(String myRecipeJson) {
                mRecipeJson = myRecipeJson;
                super.deliverResult(myRecipeJson);

            }

        };

    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        int i = 0;
        myRecipeList = JsonUtils.parseJson(data);
        i = myRecipeList.size();
        Log.i("MainAct", "Recipe Count = " + Integer.toString(i));
        Log.i("Main acct", myRecipeList.get(0).getName());
        Log.i("Main acct", myRecipeList.get(1).getName());
        Log.i("Main acct", myRecipeList.get(2).getName());
        Log.i("Main acct", myRecipeList.get(3).getName());

        mAdapter = new RecipeAdapter(this, myRecipeList, new RecipeAdapter.ListItemClickListener() {
            @Override
            public void onListItemClick(int clickedItemIndex) {

            }
        });

        recyclerView.setAdapter(mAdapter);

        int x = mAdapter.getItemCount();
        Log.i("MainAct", "Adapter Count = " + Integer.toString(x));

    }


    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }


}
