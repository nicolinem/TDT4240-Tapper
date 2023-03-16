package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.graphics.use


abstract class View() : KtxScreen {

    protected val batch = SpriteBatch()

    override fun render(delta: Float) {
    }

    override fun dispose() {
        batch.disposeSafely()
    }

    abstract fun update(dt: Float)
    abstract fun render()

}
