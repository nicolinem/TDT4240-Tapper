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
import com.group4.tapper.model.IGameController
import com.group4.tapper.model.GameModel
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
    private val tapper: Tapper,
    private val game: GameModel,
    private val assets: AssetStorage,
    private val audioService: AudioService,
): IGameController {


    private val prefs: Preferences = Gdx.app.getPreferences("prefs")
    private lateinit var puzzle: Puzzle

    override lateinit var gameView: GameView

    private val df = DecimalFormat("#.##")

    private var pointsReductionPerTick: Int =1 //default is easy mode
    private var pointsReductionPerError:Int = 10 //defualt is easy mode

    private var remainingPoints by Delegates.notNull<Int>()
    private lateinit var puzzleList: MutableList<Int>

    private var timer: Timer = Timer()
    private var task: TimerTask? = null


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
                        audioService.play(SoundAsset.CORRECT)
                        gameView.textButtonList[i].isDisabled = false
                        if (i < 5) {
                            gameView.textButtonList[i + 1].isDisabled = true
                        }

                    } else {
                        button.isDisabled = true
                        triggerError()
                        audioService.play(SoundAsset.INCORRECT)
                        Timer().schedule(500) {
                            button.isDisabled = false
                        }

                    }
                }
            })
        }

    }


    private fun triggerError() {
        Gdx.input.vibrate(500)
        remainingPoints -= pointsReductionPerError
    }

    fun checkVictory() {
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
        timer.cancel()
        timer = Timer()
        audioService.play(MusicAsset.MENU)
        game.updatePlayerScore(remainingPoints, prefs.getString("playerID"))
        tapper.setScreen<ResultView>()
    }


    override fun setDifficulty(){
        val gameDifficulty = game.difficulty

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

    override fun getPuzzleList(): MutableList<Int> {
        return puzzle.randomNumbers.toMutableList()
    }

    override fun getCoordinates(): MutableList<Pair<Float, Float>> {
        return puzzle.createCoordinates(Gdx.graphics.width, Gdx.graphics.height, 75f)
    }

    override fun handleButtonClick(i: Int, buttonText: String) {
        if (buttonText == puzzleList[0].toString()) {
            puzzleList.removeAt(0)
            gameView.removeNumberButton(i)
            checkVictory()
            gameView.setTopButtonDisabled(i, false)
            if (i < 5) {
                gameView.setTopButtonDisabled(i + 1, true)
            }
            audioService.play(SoundAsset.CORRECT)
        } else {
            gameView.setNumberButtonDisabled(i, true)
            triggerError()
            Timer().schedule(500) {
                gameView.setNumberButtonDisabled(i, false)
            }
            audioService.play(SoundAsset.INCORRECT)
        }
    }

    override fun updateAudioService() {
        audioService.update()
    }


    override fun getRemainingPoints(): String {
        return df.format(remainingPoints).toString()
    }


}
