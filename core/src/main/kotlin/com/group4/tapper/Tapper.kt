package com.group4.tapper

import com.group4.tapper.controller.GameController
import com.group4.tapper.controller.MenuController
import com.group4.tapper.view.*
import ktx.app.KtxGame
import ktx.app.KtxScreen


class Tapper(IF: FirebaseRepository) : KtxGame<KtxScreen>() {


    val FBIF = IF

    private lateinit var mainView: MainView


    public fun getInterface(): FirebaseRepository {
        return FBIF
    }

    override fun create() {
        val menuController = MenuController(this)
        val gameController = GameController(this)

        addScreen(MainView(menuController))


        addScreen(HowToView(menuController))
        addScreen(NewGameView(menuController))
        addScreen(WaitingView(menuController))
        addScreen(JoinGameView(menuController))
        addScreen(GameView(gameController))



        setScreen<GameView>()

    }


}



