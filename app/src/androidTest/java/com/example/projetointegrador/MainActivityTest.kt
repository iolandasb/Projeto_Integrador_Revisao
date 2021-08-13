package com.example.projetointegrador

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.projetointegrador.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val mainActivity = ActivityScenarioRule(MainActivity::class.java)


    //On the initial screen, when the user clicks on the button, the effectiveness of this element (button) must be verified.
    //On the initial screen, when the user types a movie in the text box, the effectiveness of this element (text box) must be verified.

    @Test
    fun start_search()  {
        onView(withId(R.id.btnSearch)).perform(click())
        onView(withId(R.id.edtSearch)).perform(typeText("Test"))
    }

    //On the home page, when the user leaves the movie search text box blank and clicks on the search button, it should be sent to the visualization of elements that inform that the movie was not found, and among them there is a fixed text that refers back to the home page. Therefore, when clicking on this fixed text, the effectiveness of this element (fixed text) must be verified.

    @Test
    fun start_tryAgain()  {
        onView(withId(R.id.btnSearch)).perform(click())
        onView(withId(R.id.edtSearch)).perform(typeText(""))
        onView(withId(R.id.txtTryAgain)).perform(click())
    }

}


