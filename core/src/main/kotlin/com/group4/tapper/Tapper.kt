package com.group4.tapper

import com.group4.tapper.Controller.GameController
import com.group4.tapper.Controller.MenuController
import com.group4.tapper.View.*
import ktx.app.KtxGame
import ktx.app.KtxScreen
import java.awt.Menu


class Tapper(IF: FirebaseRepository) : KtxGame<KtxScreen>() {


    val FBIF = IF

    private lateinit var mainView: MainView

        fun getInterface(): FirebaseRepository {
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



        setScreen<MainView>()

    }


}



