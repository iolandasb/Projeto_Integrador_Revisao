package com.example.projetointegrador

import org.junit.Test
import com.example.projetointegrador.presentation.MainActivity
import org.junit.runner.RunWith

import org.junit.Rule
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import com.example.projetointegrador.data.model.Infos
import com.example.projetointegrador.data.repository.Favorites
import com.example.projetointegrador.presentation.SearchFragment
import junit.framework.Assert.assertEquals

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    /*@Test

    fun teste() {
        var x : Infos
        var resultado = addToFavorites(movie = x(id))

    }

    open fun addToFavorites(movie: Infos) {
        favoritesList.putIfAbsent(movie.id, movie)
    }
//436969
    val favoritesList : MutableMap<Int, Infos> = mutableMapOf()*/

    /*    @Test
    fun teste(){
        //val resultado = verificarSaldoUsuario(3000.00)
        //assertEquals("Saldo suficiente para comprar um celular", resultado)

        //ARRANGE
        val personagem = Character(1,
            "Spiderman",
            "Algo",
            Thumbnail("caminhoImagem", "jpg"))

        //ACT
        val resposta = personagem.getCharacterInfo()

        //ASSERT
        assertEquals("Nome: Spiderman Descrição: Algo", resposta)
    }

    @Test
    fun `quando um usuario decidir exibir o seu saldo e tiver mais de mil reais, exibir mensagem de saldo suficiente`(){
        val resultado = verificarSaldoUsuario(1001.00)
        assertEquals("Saldo suficiente para comprar um celular", resultado)
    }

    @Test
    fun `quando um usuario decidir exibir o seu saldo e tiver menos de mil reais, exibir mensagem de saldo insuficiente`(){
        val resultado = verificarSaldoUsuario(900.00)
        assertEquals("Saldo insuficiente", resultado)
    }

    /*
    * Dado que um usuário possui um saldo superior a mil reais
    * Quando ele clicar no botão "Exibir saldo"
    * E esperar 3 segundos
    * Então é exibida uma mensagem "Saldo suficiente para comprar um celular"
    * E o aplicativo se encerra
    *
    * Dado que um usuário possui um saldo inferior a mil reais
    * Quando ele clicar no botão "Exibir saldo"
    * Então é exibida uma mensagem "Saldo insuficiente"
    * */
    fun verificarSaldoUsuario(saldo: Double) : String{
        return if(saldo > 1000.00){
            "Saldo suficiente para comprar um celular"
        } else {
            "Saldo insuficiente"
        }
    }*/

}

