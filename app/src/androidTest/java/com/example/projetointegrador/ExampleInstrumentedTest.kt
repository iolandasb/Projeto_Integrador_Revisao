package com.example.projetointegrador

import androidx.test.espresso.Espresso
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.projetointegrador", appContext.packageName)
    }

    /*@get:Rule
    val mainActivity = IntentsTestRule(MoviesAdapter::class.java)

    @Test
    fun when_click_on_FAB_go_to_FormActivity(){
        //ACT
        Espresso.onView(withId(R.id.fabNext)).perform(ViewActions.click())
        //ASSERT
        Intents.intended(hasComponent(MoviesDetailsActivity::class.java.name))
    }*/
}