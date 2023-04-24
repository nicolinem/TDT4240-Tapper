package com.group4.tapper.controller

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.group4.tapper.Tapper
import com.group4.tapper.assets.AudioService
import com.group4.tapper.assets.MusicAsset
import com.group4.tapper.assets.SoundAsset
import com.group4.tapper.assets.TextureAsset
import com.group4.tapper.model.GameControllerInterface
import com.group4.tapper.model.Puzzle
import com.group4.tapper.view.GameView
import com.group4.tapper.view.ResultView
import ktx.assets.async.AssetStorage
import java.text.DecimalFormat
import kotlin.properties.Delegates
import java.util.TimerTask
import java.util.Timer
import kotlin.concurrent.schedule

class GameController(
    val tapper: Tapper,
    val assets: AssetStorage,
    val audioService: AudioService,
): GameControllerInterface {


    private val prefs: Preferences = Gdx.app.getPreferences("prefs")
    internal lateinit var puzzle: Puzzle

    override lateinit var gameView: GameView

    internal val df = DecimalFormat("#.##")

    private var pointsReductionPerTick: Int =1 //default is easy mode
    private var pointsReductionPerError:Int = 10 //defualt is easy mode

    var remainingPoints by Delegates.notNull<Int>()
    private lateinit var puzzleList: MutableList<Int>

    private var timer: Timer = Timer()
    private var task: TimerTask? = null




    // Add a setter for the gameView


    override fun start() {
        setDifficulty()

        puzzle = Puzzle()
        remainingPoints = 1000
        task?.cancel()

        // Create a new timer task
        task = object : TimerTask() {
            override fun run() {
                remainingPoints -= pointsReductionPerTick
                checkVictory()
            }
        }

        setupGameView()
        timer.scheduleAtFixedRate(task, 0L, 25)
    }
    private fun setupGameView() {
        puzzleList = puzzle.randomNumbers.toMutableList()
        gameView.setPuzzleList(puzzleList)

        // Create coordinates for buttons
        gameView.apply {
            height = Gdx.graphics.height
            width = Gdx.graphics.width
            circleRadius = 75f
            coordinates = puzzle.createCoordinates(width, height, circleRadius)
        }

        // Draw UI
        gameView.makeUI()

        // Add listeners to buttons. Make them clickable.
        gameView.numberButtonList.forEachIndexed { i, button ->
            button.addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    if (button.text.toString() == puzzleList[0].toString()) {
                        puzzleList.removeAt(0)
                        gameView.stage.actors.removeValue(button, true)
                        checkVictory()
                        gameView.textButtonList[i].isDisabled = false
                        if (i < 5) {
                            gameView.textButtonList[i + 1].isDisabled = true
                        }
                        audioService.play(SoundAsset.CORRECT)
                    } else {
                        button.isDisabled = true
                        triggerError()
                        Timer().schedule(500) {
                            button.isDisabled = false
                        }
                        audioService.play(SoundAsset.INCORRECT)
                    }
                }
            })
        }
    }


    override fun triggerError() {
        Gdx.input.vibrate(500)
        remainingPoints -= pointsReductionPerError
    }

    override fun checkVictory() {
        if (puzzleList.isEmpty()) {
            handleVictory()
        }
        if(remainingPoints==0){
            handleVictory()
        }
    }

    override fun getTextureAsset(asset: TextureAsset): Texture {
        return assets[asset.descriptor]
    }

    override fun handleVictory() {
        println("handleVictory")
        timer.cancel()
        timer = Timer()
        audioService.play(MusicAsset.MENU)
        System.out.println(prefs.getString("playerID"))
        println("Points: $remainingPoints")
        tapper.menuController.game.updatePlayerScore(remainingPoints, prefs.getString("playerID"))
        tapper.setScreen<ResultView>()
    }

    override fun setDifficulty(){
        val gameDifficulty = tapper.menuController.game.difficulty

        when (gameDifficulty) {
            "easy" -> {
                pointsReductionPerError = 10
                pointsReductionPerTick = 1
            }
            "medium" -> {
                pointsReductionPerError = 25
                pointsReductionPerTick = 2
            }
            "hard" -> {
                pointsReductionPerError = 50
                pointsReductionPerTick = 4
            }
        }
    }

    override fun updateAudioService() {
        audioService.update()
    }


    override fun getRemainingPoints(): String {
        return df.format(remainingPoints).toString()
    }


}
