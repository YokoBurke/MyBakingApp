package com.example.android.mybakingapp;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class MainActivityBasicTest {
    @Rule public ActivityTestRule<MainActivity> mainActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickRecipeCard(){

        //onData(anything()).inAdapterView(withId(R.id.recycle_recipe_card)).atPosition(1).perform(click());
        //onView(withId(R.id.recipe_name)).check(matches(isDisplayed()));

    }
}
