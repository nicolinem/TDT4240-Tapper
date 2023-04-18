package com.group4.tapper.View

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.group4.tapper.Tapper
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.graphics.use
import ktx.scene2d.actors
import ktx.scene2d.label
import ktx.scene2d.table
import ktx.scene2d.textButton

class MainView(game: Tapper) : View(game) {

    override fun setupUI() {
        stage.actors {
            table{
                defaults().fillX().expandX()
                defaults().pad(50f)
                row().expand().bottom().left()
                label("Hello!<3")
                textButton("Click Me!").addListener(object : ChangeListener() {
                    override fun changed(event: ChangeEvent?, actor: Actor?) {
                        game.setScreen<NewGameView>()
                    }
                })
                // Table-options
                setFillParent(true)
                top()
                pack()
            }

        }
    }




    override fun dispose() {
        batch.disposeSafely()
        uiDispose()
    }
    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }

}
