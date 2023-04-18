package com.group4.tapper

import com.group4.tapper.View.MainView
import com.group4.tapper.View.NewGameView
import ktx.app.KtxGame
import ktx.app.KtxScreen

class Tapper(IF: FirebaseRepository) : KtxGame<KtxScreen>() {

    val FBIF = IF

    private lateinit var mainView: MainView


    public fun getInterface(): FirebaseRepository {
        return FBIF
    }

    override fun create() {




        addScreen(MainView(this))
        addScreen(NewGameView(this))
        setScreen<NewGameView>()

    }


}



