package com.group4.tapper

import com.group4.tapper.Controller.GameController
import com.group4.tapper.Controller.MenuController
import com.group4.tapper.View.*
import ktx.app.KtxGame
import ktx.app.KtxScreen
import java.awt.Menu


class Tapper(IF: FirebaseRepository) : KtxGame<KtxScreen>() {

    lateinit var menuController: MenuController
    lateinit var gameController: GameController


    val FBIF = IF

    private lateinit var mainView: MainView

        fun getInterface(): FirebaseRepository {
            return FBIF
        }

    override fun create() {
        menuController = MenuController(this)
        gameController = GameController(this)


        addScreen(MainView(menuController))


        addScreen(HowToView(menuController))
        addScreen(NewGameView(menuController))
        addScreen(ResultView(menuController))
        addScreen(WaitingView(menuController))
        addScreen(JoinGameView(menuController))
        addScreen(GameView(gameController))



        setScreen<MainView>()

    }


}



