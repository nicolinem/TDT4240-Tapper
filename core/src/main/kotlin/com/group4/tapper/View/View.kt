package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.FitViewport
import com.group4.tapper.Tapper
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.graphics.use
import ktx.scene2d.Scene2DSkin


abstract class View(val game: Tapper) : KtxScreen {
    private var skin: Skin = Skin(Gdx.files.internal("tapper_skin/tapper_skin.json"))
    val stage = Stage(FitViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()))

    protected val batch = SpriteBatch()

    init {
        Scene2DSkin.defaultSkin = skin

    }

    override fun show() {
        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        clearScreen(0.42f, 0.12f, 0.39f, 1f)
        stage.act()
        stage.draw()
    }

    override fun dispose() {
        batch.disposeSafely()
    }

    abstract fun update(dt: Float)

}
