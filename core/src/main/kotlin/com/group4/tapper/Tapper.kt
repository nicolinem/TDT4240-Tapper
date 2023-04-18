package com.group4.tapper

import com.badlogic.gdx.assets.AssetManager
import com.group4.tapper.View.LoadingView
import com.group4.tapper.View.MainView
import com.group4.tapper.View.NewGameView
import ktx.app.KtxGame
import ktx.app.KtxScreen

class Tapper : KtxGame<KtxScreen>() {

    override fun create() {
        addScreen(NewGameView(this))
        addScreen(MainView(this))
        addScreen(LoadingView(this, AssetManager()))
        setScreen<LoadingView>()
    }
}



