package com.group4.tapper.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.group4.tapper.assets.TextureAsset
import com.group4.tapper.controller.GameController
import ktx.scene2d.*
import ktx.app.clearScreen
import java.text.DecimalFormat
import kotlin.properties.Delegates



class GameView(private val controller: GameController) : View() {


    internal var height by Delegates.notNull<Int>()
    internal var width by Delegates.notNull<Int>()

    internal var circleRadius by Delegates.notNull<Float>()
    internal lateinit var puzzleListCopy: MutableList<Int>
    internal lateinit var coordinates: MutableList<Pair<Float, Float>>


    // Make lists of textbuttons and numberbuttons
    internal var textButtonList: MutableList<TextButton> = List(6) { TextButton("", Scene2DSkin.defaultSkin) }.toMutableList()
    internal var numberButtonList: MutableList<TextButton> = List(6) { TextButton("", Scene2DSkin.defaultSkin) }.toMutableList()

    internal lateinit var pointsLabel :Label

    internal val df = DecimalFormat("#.##")

    internal var timerbool:Boolean = false

    init {
        this.controller.gameView = this
    }
    override fun show() {
        super.show()
        controller.start()
    }

    // Remove start(), triggerError(), and checkVictory() functions here.
    // These methods are moved to GameController.

    // ... (the rest of the code)

    // Update the render function
    override fun render(delta: Float) {
        controller.audioService.update()
        clearScreen(0.42f, 0.12f, 0.39f, 1f)
        pointsLabel.setText(df.format(controller.points).toString())
        stage.act()
        stage.draw()
    }

        fun makeUI() {
        stage.clear()

        stage.actors {
            table {
                setFillParent(true)
                defaults().pad(50f).fillX().expandX()
                top()

                // Add Round and Pin text
                row().pad(150f,75f, 75f, 75f)
                table {
                    row().expandX()
                    label("Round: 1", "white_bigger"){
                        it.left()
                    }
                    pointsLabel = label(df.format(controller.points).toString(), "pink_bigger"){
                        it.right()
                    }
                }


                // Add upper line
                row().pad(0f)
                image(controller.assets[TextureAsset.LINE.descriptor])

                // Add textButtons
                row()
                table {
                    defaults().pad(0f, 10f, 0f, 10f)
                    for (i in 0..5) {
                        textButtonList[i] = textButton(puzzleListCopy[i].toString(), "round_top")
                        if (i == 0) textButtonList[i].isDisabled = true
                    }
                }

                // Add lower line
                row().pad(0f)
                image(controller.assets[TextureAsset.LINE.descriptor])
            }

            // Add numberButtons
            for (i in 0..5){
                numberButtonList[i] = textButton(puzzleListCopy[i].toString(), "round_bottom"){
                    setPosition(coordinates[i].first, coordinates[i].second)
                }
            }

        }
    }

    override fun setupUI() {
       // TODO("Not yet implemented")
    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        uiDispose()
    }

}
