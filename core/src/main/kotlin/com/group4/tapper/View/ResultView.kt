package com.group4.tapper.View;

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.group4.tapper.Controller.MenuController
import com.group4.tapper.Model.Player
import ktx.scene2d.actors
import ktx.scene2d.label
import ktx.scene2d.table
import ktx.scene2d.textButton

class ResultView(val controller: MenuController): View() {

    private val tableN = Table(skin)
    private val prefs : Preferences = Gdx.app.getPreferences("prefs")


    val gameId = "1exQ" // Replace this with the actual game id
    private var lastRound:Boolean? =null
    private var localLastRound:Boolean =false
    private lateinit var players:List<Player>
    private var playAgain:Boolean = false
    private var count:Int = 0



    fun updatePlayerScoreList(rounds:Int,currentRound: Int, players: List<Player>) {
        println("count")
        println(count)
        this.players = players
        stage.clear()
        System.out.println(players)
        tableN.clear()
        var num = 1

        var roundsList = mutableListOf<Int>()
        var scoreList = mutableListOf<Int>()

        for (p in players){
            tableN.row().padBottom(50f).expandX()
            tableN.add(Label("${num}. ${p.nickname}", skin)).left()
            tableN.add(Label("${p.score} pt", skin)).right()
            num++
            roundsList.add(p.currentRound)
            scoreList.add(p.score)

            if(p.id.equals(prefs.getString("playerID"))){
                if(p.currentRound == rounds){
                    localLastRound = true
                }
            }
        }


        //Check which buttons to load
        lastRound = roundsList.sum() == rounds*roundsList.size
        playAgain = scoreList.sum() == 0
        if(playAgain && count > rounds+1){
            count = 0
            controller.handleChangeToWaitRoom()
            localLastRound = false
            lastRound = null
        }


        setupUI()
        count++
    }

    fun subscribeToPlayerScoreUpdates( updatePlayerScoreList: (Int,Int,List<Player>) -> Unit) {
        controller.subscribeToPlayerScoreUpdates(updatePlayerScoreList)
    }
    override fun show() {
        super.show()
        subscribeToPlayerScoreUpdates(::updatePlayerScoreList)


    }
    override fun setupUI(){
        val screenWidth = Gdx.graphics.width.toFloat()
        val screenHeight = Gdx.graphics.height.toFloat()

        stage.actors {
            // Start of table
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

                else if(lastRound == true){
                    // Finish Game button
                    row().bottom()
                    textButton("Finish", "new_game") {
                    }.addListener(object : ClickListener() {
                        override fun clicked(event: InputEvent?, x: Float, y: Float) {
                            controller.handleChangeToMainView()
                            localLastRound = false
                            lastRound = null
                        }
                    })

                    row()
                    textButton("Play Again", "selection") {
                    }.addListener(object : ClickListener() {
                        override fun clicked(event: InputEvent?, x: Float, y: Float) {
                            val playerID = prefs.getString("playerID")
                            var nickname =""
                            for(p in players){
                                if(playerID.equals(p.id)){
                                    nickname = p.nickname
                                }
                            }
                            localLastRound = false
                            lastRound = null
                            playAgain = false
                            count = 0
                            controller.playAgain(players,prefs.getString("gameID"))
                            println("click play again")
                        }
                    })

                }
                else if(!localLastRound){
                        // Resume Game button
                        row().bottom()
                        textButton("Next Round", "new_game") {
                        }.addListener(object : ClickListener() {
                            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                                controller.handleChangeToGameView()
                            }
                        })
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

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }
}
