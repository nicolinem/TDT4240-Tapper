package com.group4.tapper

import com.group4.tapper.View.MainView
import com.group4.tapper.View.NewGameView
import com.group4.tapper.View.ViewManager
import ktx.app.KtxGame
import ktx.app.KtxScreen

class Tapper : KtxGame<KtxScreen>() {

    private lateinit var viewManager: ViewManager
    private lateinit var mainView: MainView
    private lateinit var newGameView: NewGameView

    override fun create() {

        addScreen(NewGameView())
        setScreen<NewGameView>()

    }
}



