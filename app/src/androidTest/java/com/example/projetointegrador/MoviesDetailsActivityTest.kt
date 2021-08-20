package com.example.projetointegrador

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.projetointegrador.presentation.moviesdetails.MoviesDetailsActivity

@RunWith(AndroidJUnit4::class)
class MoviesDetailsActivityTest {

    //On the movies details screen, when the user clicks on the button to return to the Home Page, the effectiveness of this element (button) must be verified.

    @get:Rule
    val mainActivity = ActivityScenarioRule(MoviesDetailsActivity::class.java)

    @Test
    fun test_button_return()  {
        onView(withId(R.id.btnReturn)).perform(click())
    }

}

