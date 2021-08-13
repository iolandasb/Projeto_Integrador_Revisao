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

    //Na tela inicial, quando o usuário clicar no botão, deve-se verificar a efetividade desse elemento (botão).
    //Na tela inicial, quando o usuário digitar um filme na caixa de texto, deve-se verificar a efetividade desse elemento (caixa de texto).

    @Test
    fun start_search()  {
        onView(withId(R.id.btnSearch)).perform(click())
        onView(withId(R.id.edtSearch)).perform(typeText("Teste"))
    }

    //Na tela inicial, quando o usuário deixar em branco a caixa de texto de busca de filmes e clicar no botão pesquisar, ele será remetido à visualização do texto fixo que remete à página inicial, portanto ao clicar nesse texto fixo, deve-se verificar a efetividade desse elemento (texto fixo).

    @Test
    fun start_tryAgain()  {
        onView(withId(R.id.btnSearch)).perform(click())
        onView(withId(R.id.edtSearch)).perform(typeText(""))
        onView(withId(R.id.txtTryAgain)).perform(click())
    }

}

/*protected var driver: AndroidDriver<MobileElement>? = null

@Test
fun start_search()  {
    var edtSearch : MobileElement = driver!!.findElement(By.id(R.id.edtSearch.toString()))
    //var btnSearch : MobileElement = driver!!.findElement(By.id(R.id.btnSearch.toString()))


    //onView(withId(R.id.btnSearch)).perform(click())
    onView(withId(R.id.edtSearch))
        .perform(click())
        .check()

    onView(allOf(withId(R.id.btnSearch), withText("Hello!")))

    //.check(matches(withText("Hello Espresso!").toString())

    edtSearch.sendKeys("Sandy")
    btnSearch.click()

    var Z = edtSearch.text
    Assert.assertEquals("Sandy", Z)
}*/



