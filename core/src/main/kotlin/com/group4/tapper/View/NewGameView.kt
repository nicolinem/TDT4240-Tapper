package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.group4.tapper.Tapper
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.scene2d.*


class NewGameView(game: Tapper): View(game) {

     override fun setupUI() {
        val screenWidth = Gdx.graphics.width.toFloat()

        stage.actors {
            // Start of table
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                // Pin
                row().expand().bottom().left()
                label("Pin: 1234"){
                    setFontScale(2f)
                }

                // Nickname-field
                row().size(screenWidth/2, 150f).left()
                textField("Nickname"){
                }

                // Rounds-label
                row()
                label("Rounds")

                // Rounds-buttons
                row().left()
                buttonGroup(1,1){
                    it.fillX().expandX()
                    textButton("2", "toggle") {
                        pad(25f, 50f, 25f, 50f)
                    }
                    textButton("3", "toggle") {
                        pad(25f, 50f, 25f, 50f)
                        it.padLeft(50f)
                        it.padRight(50f)
                    }
                    textButton("4", "toggle") {
                        pad(25f, 50f, 25f, 50f)
                    }
                }.addListener(object : ChangeListener() {
                    override fun changed(event: ChangeEvent?, actor: Actor?) {
                        System.out.println(actor.toString())
                    }
                })

                // Difficulty-label
                row().left()
                label("Difficulty")

                // Difficulty-buttons
                row().left()
                buttonGroup(1,1){
                    textButton("easy", "toggle") {
                        pad(25f, 50f, 25f, 50f)
                    }
                    textButton("medium", "toggle") {
                        pad(25f, 50f, 25f, 50f)
                        it.padLeft(50f)
                        it.padRight(50f)
                    }
                    textButton("hard", "toggle") {
                        pad(25f, 50f, 25f, 50f)
                    }
                }

                // Create game-button
                row()
                textButton("Create Game", "selection") {
                    it.padTop(400f)
                    it.height(150f)
                }

                // Table-options
                setFillParent(true)
                top()
                pack()
            }
        }
    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        stage.disposeSafely()
        uiDispose()
    }
}
