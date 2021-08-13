package com.example.projetointegrador

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.projetointegrador.presentation.MoviesDetailsActivity

@RunWith(AndroidJUnit4::class)
class MoviesDetailsActivityTest {

    //Na tela inicial, quando o usuário clicar no botão, deve-se verificar a efetividade desse elemento (botão).

    @get:Rule
    val mainActivity = ActivityScenarioRule(MoviesDetailsActivity::class.java)

    @Test
    fun test_button_return()  {
        onView(withId(R.id.btnReturn)).perform(click())
    }

}

