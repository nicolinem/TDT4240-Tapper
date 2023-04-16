package com.group4.tapper.View

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxScreen
import ktx.assets.disposeSafely


abstract class View() : KtxScreen {

    protected val batch = SpriteBatch()



    override fun render(delta: Float) {
    }

    override fun dispose() {
        batch.disposeSafely()
    }

    abstract fun update(dt: Float)

}
