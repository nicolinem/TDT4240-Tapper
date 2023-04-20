package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener

import com.group4.tapper.Model.Puzzle
import com.group4.tapper.Tapper
import ktx.assets.disposeSafely
import ktx.scene2d.*

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.viewport.FitViewport
import com.group4.tapper.Tapper
import com.group4.tapper.View.Objects.Circle
import ktx.app.clearScreen

import kotlin.random.Random


class GameView(game: Tapper) : View(game) {


    private var points: Int = 1000
    private var height:Int
    private var width:Int

    private var circleRadius:Float
    private var puzzleList: MutableList<Int>
    private var puzzleListCopy: MutableList<Int>
    private val coordinates: MutableList<Pair<Float, Float>>
    private val puzzle: Puzzle = Puzzle()

    private lateinit var textButton1:TextButton
    private lateinit var textButton2:TextButton
    private lateinit var textButton3:TextButton
    private lateinit var textButton4:TextButton
    private lateinit var textButton5:TextButton
    private lateinit var textButton6:TextButton

    private lateinit var numberButton1:TextButton
    private lateinit var numberButton2:TextButton
    private lateinit var numberButton3:TextButton
    private lateinit var numberButton4:TextButton
    private lateinit var numberButton5:TextButton
    private lateinit var numberButton6:TextButton

    init {
        // Get a list of random numbers
        puzzleList = MutableList(6) { Random.nextInt(0, 9) }
        puzzleListCopy = puzzleList.toMutableList()

        //Create coordinates for buttons
        height = Gdx.graphics.height
        width = Gdx.graphics.width
        circleRadius = textButton1.width/2
        coordinates = puzzle.createCoordinates(width,height,circleRadius)

        // Draw UI
        setupUI()

        //Add listeners to buttons. Make them clickable.
        numberButton1.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(numberButton1.text.toString() == puzzleList[0].toString()){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(numberButton1,true)

                }
                else{
                    triggerError()
                }
            }
        })

        numberButton2.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(numberButton2.text.toString() == puzzleList[0].toString()){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(numberButton2,true)

                }
                else{
                    triggerError()
                }
            }
        })

        numberButton3.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(numberButton3.text.toString() == puzzleList[0].toString()){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(numberButton3,true)

                }
                else{
                    triggerError()
                }
            }
        })

        numberButton4.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(numberButton4.text.toString() == puzzleList[0].toString()){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(numberButton4,true)

                }
                else{
                    triggerError()
                }
            }
        })

        numberButton5.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(numberButton5.text.toString() == puzzleList[0].toString()){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(numberButton5,true)

                }
                else{
                    triggerError()
                }
            }
        })

        numberButton6.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(numberButton6.text.toString() == puzzleList[0].toString()){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(numberButton6,true)

                }
                else{
                    triggerError()
                }
            }
        })
    }

    override fun setupUI() {

        stage.actors {
            table {
                setFillParent(true)
                defaults().pad(50f)
                top()

                // Add Round and Pin text
                row().expandX().fillX().pad(150f,75f, 75f, 75f)
                table {
                    row().expandX()
                    label("Round: 1", "white_bigger"){
                        it.left()
                    }
                    label("1234", "pink_bigger"){
                        it.right()
                    }
                }


                // Add upper line
                row().pad(0f)
                image(Texture(Gdx.files.internal("Images/Line.png")))

                // Add circles
                row()
                table {
                    defaults().pad(0f, 10f, 0f, 10f)

                    textButton1 = textButton(puzzleListCopy[0].toString(), "round_bigger")
                    textButton2 = textButton(puzzleListCopy[1].toString(), "round_bigger")
                    textButton3 = textButton(puzzleListCopy[2].toString(), "round_bigger")
                    textButton4 = textButton(puzzleListCopy[3].toString(), "round_bigger")
                    textButton5 = textButton(puzzleListCopy[4].toString(), "round_bigger")
                    textButton6 = textButton(puzzleListCopy[5].toString(), "round_bigger")
                }

                // Add lower line
                row().pad(0f)
                image(Texture(Gdx.files.internal("Images/Line.png")))
            }

            // Add all number-buttons
            numberButton1 =  textButton(puzzleListCopy[5].toString(), "round_bigger"){
                setPosition(coordinates[5].first, coordinates[5].second)
            }
            numberButton2 =  textButton(puzzleListCopy[4].toString(), "round_bigger"){
                setPosition(coordinates[4].first, coordinates[4].second)
            }
            numberButton3 =  textButton(puzzleListCopy[3].toString(), "round_bigger"){
                setPosition(coordinates[3].first, coordinates[3].second)
            }
            numberButton4 =  textButton(puzzleListCopy[2].toString(), "round_bigger"){
                setPosition(coordinates[2].first, coordinates[2].second)
            }
            numberButton5 =  textButton(puzzleListCopy[1].toString(), "round_bigger"){
                setPosition(coordinates[1].first, coordinates[1].second)
            }
            numberButton6 =  textButton(puzzleListCopy[0].toString(), "round_bigger"){
                setPosition(coordinates[0].first, coordinates[0].second)
            }

        }
    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }
   

    fun triggerError(){
        Gdx.input.vibrate(500)
    }

    fun checkVictory(){
        if(puzzleList.isNotEmpty()){
            points -=1
        }
    }
    override fun dispose() {
        batch.disposeSafely()
        uiDispose()
    }

}
