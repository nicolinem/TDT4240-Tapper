package com.group4.tapper

import com.group4.tapper.Controller.MenuController
import com.group4.tapper.View.JoinGameView
import com.group4.tapper.View.MainView
import com.group4.tapper.View.NewGameView
import com.group4.tapper.View.WaitingView
import ktx.app.KtxGame
import ktx.app.KtxScreen
import java.awt.Menu


class Tapper(IF: FirebaseRepository) : KtxGame<KtxScreen>() {


    val FBIF = IF

    private lateinit var mainView: MainView


    public fun getInterface(): FirebaseRepository {
        return FBIF
    }

    override fun create() {
        val menuController = MenuController(this)

        addScreen(MainView(menuController))


        addScreen(NewGameView(menuController))
        addScreen(WaitingView(menuController))
        addScreen(JoinGameView(menuController))


        setScreen<MainView>()

    }


}



