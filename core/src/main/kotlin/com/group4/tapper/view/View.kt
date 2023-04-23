package com.group4.tapper.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.app.KtxScreen
import ktx.app.clearScreen


abstract class View() : KtxScreen {
/*   var skin: Skin = Skin(Gdx.files.internal("tapper_skin/tapper_skin_7.json"))*/
    val stage = Stage(FitViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()))
    var screenHeight = Gdx.graphics.height.toFloat()
    var screenWidth = Gdx.graphics.width.toFloat()

    protected val batch = SpriteBatch()

    abstract fun setupUI()

    override fun show() {
        Gdx.input.inputProcessor = stage
        setupUI()
    }

    override fun hide() {
        stage.clear()
    }

    override fun render(delta: Float) {
        clearScreen(0.31f, 0.10f, 0.29f, 1f)
        stage.act()
        stage.draw()
    }

    fun uiDispose(){
        stage.dispose()
    }

    abstract fun update(dt: Float)

}
