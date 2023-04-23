package com.group4.tapper.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.group4.tapper.model.Puzzle
import ktx.assets.disposeSafely
import ktx.scene2d.*
import com.group4.tapper.controller.GameController
import ktx.app.clearScreen
import java.text.DecimalFormat
import java.util.TimerTask
import java.util.Timer
import kotlin.properties.Delegates
import kotlin.concurrent.schedule



class GameView(private val controller: GameController) : View() {


    private var points by Delegates.notNull<Int>()
    private var height by Delegates.notNull<Int>()
    private var width by Delegates.notNull<Int>()

    private var circleRadius by Delegates.notNull<Float>()
    private lateinit var puzzleList: MutableList<Int>
    private lateinit var puzzleListCopy: MutableList<Int>
    private lateinit var coordinates: MutableList<Pair<Float, Float>>
    private lateinit var puzzle: Puzzle

    // Make lists of textbuttons and numberbuttons
    private var textButtonList: MutableList<TextButton> = List(6) { TextButton("", Scene2DSkin.defaultSkin) }.toMutableList()
    private var numberButtonList: MutableList<TextButton> = List(6) { TextButton("", Scene2DSkin.defaultSkin) }.toMutableList()

    private lateinit var pointsLabel :Label

    private val df = DecimalFormat("#.##")

    private var timerbool:Boolean = false
    private var timer :Timer = Timer()

    override fun show() {
        super.show()
        start()
        timer = Timer()
        val task = object :TimerTask(){
            override fun run() {
                points -= 1
            }
        }

        //Start timer
        if(!timerbool){
            timer.scheduleAtFixedRate(task,0L,25L)
            timerbool=true
        }
    }


    fun start() {
        points = 1000


        // Get a list of random numbers
        puzzle = Puzzle()
        puzzleList = puzzle.createRandomNumbers()

        puzzleListCopy = puzzleList.toMutableList()
        println(puzzleList)

        //Create coordinates for buttons
        height = Gdx.graphics.height
        width = Gdx.graphics.width
        circleRadius = 75f
        coordinates = puzzle.createCoordinates(width, height, circleRadius)

        // Draw UI
        makeUI()

        //Add listeners to buttons. Make them clickable.
        for (i in 0..5) {
            numberButtonList[i].addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    if (numberButtonList[i].text.toString() == puzzleList[0].toString()) {
                        puzzleList.removeAt(0)
                        stage.actors.removeValue(numberButtonList[i], true)
                        checkVictory()
                        textButtonList[i].isDisabled = false
                        if (i < 5) {
                            textButtonList[i + 1].isDisabled = true
                        }
                    } else {
                        numberButtonList[i].isDisabled = true
                        triggerError()
                        Timer().schedule(500) {
                            numberButtonList[i].isDisabled = false
                        }
                    }
                }
            })
        }
    }

        fun makeUI() {
        stage.clear()

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
                image(Texture(Gdx.files.internal("images/line.png")))

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
                image(Texture(Gdx.files.internal("images/line.png")))
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
            timerbool = false
            controller.handleVictory(points)
        }

    }

    override fun dispose() {
        batch.disposeSafely()
        uiDispose()
    }

}