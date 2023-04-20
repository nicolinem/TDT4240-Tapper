package com.group4.tapper.View;

import com.badlogic.gdx.Gdx
import ktx.scene2d.actors
import ktx.scene2d.table

class ResultView: View() {

    override fun setupUI(){
        val screenWidth = Gdx.graphics.width.toFloat()
        val screenHeight = Gdx.graphics.height.toFloat()

        stage.actors {
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                row()

        }
    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }
}
