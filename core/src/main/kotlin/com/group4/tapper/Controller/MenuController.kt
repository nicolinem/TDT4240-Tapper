package com.group4.tapper.Controller

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.group4.tapper.Model.Game
import com.group4.tapper.Model.Player
import com.group4.tapper.Tapper
import com.group4.tapper.View.*

class MenuController(tapper: Tapper) {

    private val tapper:Tapper
    private var numberOfRounds: Int
    private var difficulty:String
    private var game:Game



    init{
        this.tapper = tapper
        numberOfRounds = 2
        difficulty = "easy"
        game = Game(tapper,numberOfRounds,"TEMP",difficulty,"TEMP")

    }

    fun createNewGame(nickname:String, rounds:Int, difficulty:String){
        game = Game(tapper,rounds,nickname,difficulty)
        game.createGame()

    }

    fun addPlayerToGame(gameID:String,nickname: String){
        game.addPlayer(gameID,nickname)
    }

    fun handleChangeToMainView(){
        tapper.setScreen<MainView>()
    }

     fun handleChangeToJoinGameView(){
        tapper.setScreen<WaitingView>()
    }

     fun handleChangeToNewGameView(){
        tapper.setScreen<NewGameView>()
    }

    fun handleChangeToHowToView() {
        tapper.setScreen<HowToView>()
    }

    fun handleChangeToGameView(){
        tapper.setScreen<GameView>()
    }

    fun subscribeToPlayerScoreUpdates(gameId: String, onPlayerScoreUpdate: (List<Player>) -> Unit) {
        game.subscribeToPlayerScoreUpdates(gameId, onPlayerScoreUpdate)
    }



    fun getTapper(): Tapper {
        return this.tapper
    }

    fun getGameID(): String{
        return game.getGameID()
    }



}
