package com.group4.tapper.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.group4.tapper.assets.TextureAsset
import com.group4.tapper.controller.GameController
import com.group4.tapper.model.GameControllerInterface
import ktx.scene2d.*
import ktx.app.clearScreen
import java.text.DecimalFormat
import kotlin.properties.Delegates



class GameView(private val controller: GameControllerInterface) : View() {


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


    init {
        controller.gameView = this
    }
    override fun show() {
        Gdx.input.inputProcessor = stage
        controller.start()
    }


    override fun render(delta: Float) {
        controller.updateAudioService()
        clearScreen(0.42f, 0.12f, 0.39f, 1f)
        pointsLabel.setText(controller.getRemainingPoints())
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
                    pointsLabel = label(controller.getRemainingPoints(), "pink_bigger"){
                        it.right()
                    }
                }


                // Add upper line
                row().pad(0f)
                image(controller.getTextureAsset(TextureAsset.LINE))


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
                image(controller.getTextureAsset(TextureAsset.LINE))

            }

            // Add numberButtons
            for (i in 0..5){
                numberButtonList[i] = textButton(puzzleListCopy[i].toString(), "round_bottom"){
                    setPosition(coordinates[i].first, coordinates[i].second)
                }
            }

        }
    }

    fun setPuzzleList(list: MutableList<Int>) {
        puzzleListCopy = list
    }

    fun removeNumberButton(index: Int) {
        stage.actors.removeValue(numberButtonList[index], true)
    }

    fun setTopButtonDisabled(index: Int, disabled: Boolean) {
        textButtonList[index].isDisabled = disabled
    }

    fun setNumberButtonDisabled(index: Int, disabled: Boolean) {
        numberButtonList[index].isDisabled = disabled
    }


    override fun setupUI() {
        puzzleListCopy = controller.getPuzzleList()
        coordinates = controller.getCoordinates()

        // Draw UI
        makeUI()

        // Add listeners to buttons. Make them clickable.
        numberButtonList.forEachIndexed { i, button ->
            button.addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    controller.handleButtonClick(i, button.text.toString())
                }
            })
        }
    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        uiDispose()
    }

}
