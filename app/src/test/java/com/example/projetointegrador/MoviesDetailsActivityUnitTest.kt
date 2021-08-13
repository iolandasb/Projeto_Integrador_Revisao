package com.example.projetointegrador

import junit.framework.Assert.assertEquals
import org.junit.Test

class MoviesDetailsActivityUnitTest {

    @Test
    fun `validates the action of getting the year of a date`(){
        val result = getYear("2021-03-05")
        assertEquals("2021", result)
    }

    fun getYear(date: String) : String {
        return date.take(4)
    }

    @Test
    fun `validates the transformation of a period in minutes to hours and minutes`(){
        val result = movieRuntime(132)
        assertEquals("2h 12min", result)
    }

    fun movieRuntime(runtime: Int) : String {
        val hours = runtime/60
        val minutes = runtime%60

        return "%dh %dmin".format(hours, minutes)
    }

    @Test
    fun `validates a movie rating percentage`(){
        val result = movieRating(8.1)
        assertEquals("81%", result)
    }

    fun movieRating(userRating: Double) : String {
        return "${"%.0f".format((userRating * 10.0))}%"
    }

}