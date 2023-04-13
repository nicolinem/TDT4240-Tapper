package com.group4.tapper.View

import com.badlogic.gdx.graphics.Texture
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.graphics.use
import ktx.scene2d.*
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.StretchViewport

class NewGameView: View() {

    private var stage: Stage
    init {
        stage = Stage(StretchViewport(480f, 640f))
        Scene2DSkin.defaultSkin = Skin(Gdx.files.internal("orange/skin/uiskin.json"))
        Gdx.input.inputProcessor = stage
        val myTable = scene2d.table {
            textButton("Click Me")
        }
        myTable.setPosition(100f, 100f)
        stage.addActor(myTable)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.42f, 0.12f, 0.39f, 1f);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act()
        stage.draw()
    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        batch.disposeSafely()
    }
}
