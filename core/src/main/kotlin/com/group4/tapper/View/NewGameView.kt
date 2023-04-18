package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.scene2d.*


class NewGameView: View() {
    private var skin: Skin
    private val stage = Stage(FitViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()))

    init {
        skin = Skin(Gdx.files.internal("tapper_skin/tapper_skin.json"))
        Scene2DSkin.defaultSkin = skin
        Gdx.input.inputProcessor = stage
        setupUI()
    }


    private fun setupUI() {
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
                    textButton("2") {
                        pad(25f, 50f, 25f, 50f)
                    }
                    textButton("3") {
                        pad(25f, 50f, 25f, 50f)
                        it.padLeft(50f)
                        it.padRight(50f)
                    }
                    textButton("4") {
                        pad(25f, 50f, 25f, 50f)
                    }
                }

                // Difficulty-label
                row().left()
                label("Difficulty")

                // Difficulty-buttons
                row().left()
                buttonGroup(1,1){
                    textButton("easy") {
                        pad(25f, 50f, 25f, 50f)
                    }
                    textButton("medium") {
                        pad(25f, 50f, 25f, 50f)
                        it.padLeft(50f)
                        it.padRight(50f)
                    }
                    textButton("hard") {
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

    override fun render(delta: Float) {
        clearScreen(0.42f, 0.12f, 0.39f, 1f)
        stage.act()
        stage.draw()
    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        stage.disposeSafely()
    }
}
