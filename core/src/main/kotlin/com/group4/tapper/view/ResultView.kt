package com.group4.tapper.view;

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.group4.tapper.assets.GameState
import com.group4.tapper.controller.MenuController
import com.group4.tapper.model.IMenuController
import com.group4.tapper.model.Player
import ktx.actors.onClick
import ktx.scene2d.*



class ResultView(val controller: IMenuController): View() {



    private val tableN = Table(Scene2DSkin.defaultSkin)

    private var lastRound:Boolean? =null
    private var localLastRound:Boolean =false
    private lateinit var players:List<Player>
    private var playAgain:Boolean = false
    private var count:Int = 0
    private val prefs : Preferences = Gdx.app.getPreferences("prefs")
    private var hidden: Boolean = true



    fun updatePlayerScoreList(rounds: Int, currentRound: Int, playerList: List<Player>) {

        Gdx.app.postRunnable {
            stage.clear()
            tableN.clear()
            var num = 1

            var roundsList = mutableListOf<Int>()
            var scoreList = mutableListOf<Int>()


        val players = playerList.sortedByDescending { it.score }
        for (p in players){
            tableN.row().padBottom(50f).expandX()
            var label = Label("${num}. ${p.nickname}", Scene2DSkin.defaultSkin)
            var points = Label("${p.score} pt", Scene2DSkin.defaultSkin)
            if (p.id == controller.getPlayerID()) {
                label.setColor(124/255f, 252/255f, 0f, 1f)
                points.setColor(124/255f, 252/255f, 0f, 1f)
            }
            tableN.add(label).left()
            tableN.add(points).right()
            num++
            roundsList.add(p.currentRound)
            scoreList.add(p.score)

            if(p.id.equals(prefs.getString("playerID"))){
                if(p.currentRound == rounds){
                    localLastRound = true
                }
            }
        }

            setupUI()
            count++
        }
    }

    fun subscribeToPlayerScoreUpdates( updatePlayerScoreList: (Int,Int,List<Player>) -> Unit) {
        controller.subscribeToPlayerScoreUpdates(updatePlayerScoreList)
    }
    override fun show() {
        super.show()
        subscribeToPlayerScoreUpdates(::updatePlayerScoreList)
        hidden = false

    }
    override fun setupUI(){
        stage.actors {
            // Start of table
            val gameState = controller.checkGameState()
            println(gameState.toString())

            if (gameState == GameState.PLAY_AGAIN && !hidden)
            {
            controller.handleChangeToWaitRoom()
            resetStats()
        }

            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                row().top().left().pad(100f, 100f, 100f, 0f)
                label("Results", "white_bigger")

                // Print player names on screen
                row().expand().fillX().pad(0f, 100f, 0f, 100f).top()
                add(tableN).top()


                //CHECK IF ITS THE LAST ROUND
                if(lastRound == null ){

                    //DO NOTHING
                    row()
                }

                if(gameState == GameState.FINISHED){
                    // Finish Game button
                    row().bottom()
                    textButton("Finish", "selection") {
                        onClick {
                            controller.handleFinishGame()
                            resetStats()
                        }
                    }

                    row()
                    textButton("Play Again", "selection") {
                        onClick {
                            resetStats()
                            controller.playAgain()
                        }
                    }

                }
                else if( gameState == GameState.IN_PROGRESS){
                    row().bottom()
                    textButton("Next Round", "new_game") {
                        onClick { controller.handleChangeToGameView() }
                    }
                }
                else{
                    row()
                    label("Waiting for other players").setAlignment(1)
                }

                setFillParent(true)
                top()
                pack()
            }
        }
    }

    fun resetStats(){
        localLastRound = false
        lastRound = null
        playAgain = false
        count = 0
    }

    override fun hide() {
        super.hide()
        hidden = true
    }


}
