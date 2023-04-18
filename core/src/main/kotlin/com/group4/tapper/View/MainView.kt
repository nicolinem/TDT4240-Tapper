package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.group4.tapper.Tapper
import ktx.assets.disposeSafely
import ktx.scene2d.*

class MainView(game: Tapper) : View(game) {

    override fun setupUI() {
        val screenHeight = Gdx.graphics.height.toFloat()
        val screenWidth = Gdx.graphics.width.toFloat()

        stage.actors {
            // Start of table
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                // How to play button
                row().width(screenWidth/2.5f).right()
                textButton("How to play?")

                // Logo
                row().width(screenWidth/1.5f).height(screenWidth/1.5f).padTop(screenHeight/8f)
                image(Texture(Gdx.files.internal("Images/tapper_logo.png")))

                // New Game button
                row()/*.padTop(screenHeight/3f)*/
                textButton("New Game", "new_game"){
                }

                // Join Game button
                row()
                textButton("Join Game", "join_game") {
                }

                // table settings
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
