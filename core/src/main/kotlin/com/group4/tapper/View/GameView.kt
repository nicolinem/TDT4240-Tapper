package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener

import com.group4.tapper.Model.Puzzle
import ktx.assets.disposeSafely
import ktx.scene2d.*

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

import com.badlogic.gdx.utils.viewport.FitViewport
import com.group4.tapper.Controller.GameController
import com.group4.tapper.Tapper
import com.group4.tapper.View.Objects.Circle
import ktx.app.clearScreen
import java.text.DecimalFormat
import java.util.TimerTask
import java.util.Timer
import java.util.logging.Handler

import kotlin.random.Random
import java.util.*
import kotlin.concurrent.schedule



class GameView(private val controller: GameController) : View() {


    private var points: Int = 1000
    private var height:Int
    private var width:Int

    private var circleRadius:Float
    private var puzzleList: MutableList<Int>
    private var puzzleListCopy: MutableList<Int>
    private val coordinates: MutableList<Pair<Float, Float>>
    private val puzzle: Puzzle

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
    private lateinit var pointsLabel :Label

    private val df = DecimalFormat("#.##")

    private var timerbool:Boolean = false
    private val timer :Timer = Timer()
    private val task = object :TimerTask(){
        override fun run() {
            points -= 1
        }
    }

    override fun show() {
        super.show()
        //Start timer
        if(!timerbool){
            timer.scheduleAtFixedRate(task,0L,25L)
            timerbool=true
        }
    }

    init {


        // Get a list of random numbers
        puzzle = Puzzle()
        puzzleList = puzzle.createRandomNumbers()

        puzzleListCopy = puzzleList.toMutableList()
        println(puzzleList)

        //Create coordinates for buttons

        height = Gdx.graphics.height
        width = Gdx.graphics.width

        circleRadius = 75f
        coordinates = puzzle.createCoordinates(width,height,circleRadius)

        // Draw UI
        makeUI()

        //Add listeners to buttons. Make them clickable.
        numberButton1.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(numberButton1.text.toString() == puzzleList[0].toString()){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(numberButton1,true)
                    checkVictory()
                    textButton1.isDisabled = false
                    textButton2.isDisabled = true
                }
                else{
                    numberButton1.isDisabled = true
                    triggerError()
                    Timer().schedule(500){
                        numberButton1.isDisabled = false
                    }
                }
            }
        })

        numberButton2.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(numberButton2.text.toString() == puzzleList[0].toString()){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(numberButton2,true)
                    checkVictory()
                    textButton2.isDisabled = false
                    textButton3.isDisabled = true
                }
                else{
                    numberButton2.isDisabled = true
                    triggerError()
                    Timer().schedule(500){
                        numberButton2.isDisabled = false
                    }
                }
            }
        })

        numberButton3.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(numberButton3.text.toString() == puzzleList[0].toString()){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(numberButton3,true)
                    checkVictory()
                    textButton3.isDisabled = false
                    textButton4.isDisabled = true
                }
                else{
                    numberButton3.isDisabled = true
                    triggerError()
                    Timer().schedule(500){
                        numberButton3.isDisabled = false
                    }
                }
            }
        })

        numberButton4.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(numberButton4.text.toString() == puzzleList[0].toString()){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(numberButton4,true)
                    checkVictory()
                    textButton4.isDisabled = false
                    textButton5.isDisabled = true
                }
                else{
                    numberButton4.isDisabled = true
                    triggerError()
                    Timer().schedule(500){
                        numberButton4.isDisabled = false
                    }
                }
            }
        })

        numberButton5.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(numberButton5.text.toString() == puzzleList[0].toString()){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(numberButton5,true)
                    checkVictory()
                    textButton5.isDisabled = false
                    textButton6.isDisabled = true
                }
                else{
                    numberButton5.isDisabled = true
                    triggerError()
                    Timer().schedule(500){
                        numberButton5.isDisabled = false
                    }
                }
            }
        })

        numberButton6.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(numberButton6.text.toString() == puzzleList[0].toString()){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(numberButton6,true)
                    checkVictory()

                }
                else{
                    numberButton6.isDisabled = true
                    triggerError()
                    Timer().schedule(500){
                        numberButton6.isDisabled = false
                    }
                }
            }
        })
    }

        fun makeUI() {

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
                    pointsLabel = label(df.format(points).toString(), "pink_bigger"){
                        it.right()
                    }
                }


                // Add upper line
                row().pad(0f)
                image(Texture(Gdx.files.internal("images/Line.png")))

                // Add circles
                row()
                table {
                    defaults().pad(0f, 10f, 0f, 10f)
                    textButton1 = textButton(puzzleListCopy[0].toString(), "round_top")
                    textButton1.isDisabled = true
                    textButton2 = textButton(puzzleListCopy[1].toString(), "round_top")
                    textButton3 = textButton(puzzleListCopy[2].toString(), "round_top")
                    textButton4 = textButton(puzzleListCopy[3].toString(), "round_top")
                    textButton5 = textButton(puzzleListCopy[4].toString(), "round_top")
                    textButton6 = textButton(puzzleListCopy[5].toString(), "round_top")
                }

                // Add lower line
                row().pad(0f)
                image(Texture(Gdx.files.internal("images/Line.png")))
            }

            // Add all number-buttons
            numberButton1 =  textButton(puzzleListCopy[0].toString(), "round_bottom"){
                setPosition(coordinates[0].first, coordinates[0].second)
            }
            numberButton2 =  textButton(puzzleListCopy[1].toString(), "round_bottom"){
                setPosition(coordinates[1].first, coordinates[1].second)
            }
            numberButton3 =  textButton(puzzleListCopy[2].toString(), "round_bottom"){
                setPosition(coordinates[2].first, coordinates[2].second)
            }
            numberButton4 =  textButton(puzzleListCopy[3].toString(), "round_bottom"){
                setPosition(coordinates[3].first, coordinates[3].second)
            }
            numberButton5 =  textButton(puzzleListCopy[4].toString(), "round_bottom"){
                setPosition(coordinates[4].first, coordinates[4].second)
            }
            numberButton6 =  textButton(puzzleListCopy[5].toString(), "round_bottom"){
                setPosition(coordinates[5].first, coordinates[5].second)
            }

        }
    }

    override fun setupUI() {
       // TODO("Not yet implemented")
    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }

    override fun render(delta: Float) {
        clearScreen(0.42f, 0.12f, 0.39f, 1f)
        pointsLabel.setText(df.format(points).toString()    )
        stage.act()
        stage.draw()
    }


    fun triggerError(){
        Gdx.input.vibrate(500)
        points-= 10
    }

    private fun checkVictory(){
        if(puzzleList.isEmpty()){
            timer.cancel()
            controller.handleVictory(points)
        }

    }

    override fun dispose() {
        batch.disposeSafely()
        uiDispose()
    }

}
