package com.group4.tapper.model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.group4.tapper.FirebaseRepository
import com.group4.tapper.assets.GameState
import com.group4.tapper.controller.MenuController
import java.util.*

class Game(private val firebaseRepository:
           FirebaseRepository,
           ): GameModel {

    private val prefs : Preferences = Gdx.app.getPreferences("prefs")

    val playerScores:MutableMap<String,Player> = mutableMapOf()
    var rounds: Int = 3
    var currentRound:Int = 1



    override var gameID: String = generatePin()
        set(value) {
            field = value
            prefs.putString("gameID", value)
            prefs.flush()
        }



    override var difficulty: String = ""
        get() = field


    override fun removePlayer(playerID: String){
        playerScores.remove(playerID)
        println(playerScores)
        firebaseRepository.removePlayer(gameID, playerScores)
    }


    override fun putGame() {
        firebaseRepository.createGame(this)
    }

    override fun addPlayer(player: Player){
        playerScores[player.id] = player
    }

    override fun joinGame(player: Player) {
        playerScores[player.id] = player
        firebaseRepository.joinGame(this.gameID, player)
        prefs.putString("playerID",player.id)
        prefs.flush()
    }

    override fun updatePlayerScore(points:Int, playerID: String){
        playerScores[playerID]?.incrementRound()
        playerScores[playerID]?.updateScore(points)
        playerScores[playerID]?.let { firebaseRepository.joinGame(this.gameID, it) }
    }
    override fun resetPlayerStats(playerID: String){
        playerScores[playerID]?.resetRounds()
        playerScores[playerID]?.let { firebaseRepository.joinGame(this.gameID, it) }
    }

    override fun doesGameExist(pin:String, callback:(Int, Boolean) -> Boolean){
        firebaseRepository.checkIfGameExists(pin,callback)
    }

    override fun checkIfLastRound(method:(Boolean) -> Unit){
        if (rounds == currentRound) {
            method(true)
        } else {
            method(false)
        }

       /* firebaseRepository.checkIfLastRound(gameID,method)*/
    }
    override fun playAgain(){
        for ((key) in playerScores){
            playerScores[key]?.resetScore()
        }
        putGame()
    }


private fun getGame(players: List<Player>, rounds:Int, diff:String, currentRound: Int) {
    for (p in players){
            playerScores[p.id] = p
    }
    this.rounds = rounds
    this.difficulty = diff
    this.currentRound = currentRound
}

    override fun checkGameState(playerID: String): GameState {
        val localPlayer = playerScores[playerID]
        val players = playerScores.filterKeys { it != playerID }.values

        val allPlayersFinished = playerScores.values.all { it.currentRound >= rounds }
        val allPlayersHaveZeroScore = playerScores.values.all { it.score == 0 }

        return when {
            allPlayersFinished && allPlayersHaveZeroScore -> GameState.PLAY_AGAIN
            !allPlayersFinished && localPlayer?.currentRound == rounds -> GameState.WAITING
            allPlayersFinished -> GameState.FINISHED
            else -> GameState.IN_PROGRESS
        }
    }

    override fun subscribeToPlayerScoreUpdates(gameId: String, onPlayerScoreUpdate: (Int, Int, List<Player>) -> Unit) {
         firebaseRepository.subscribeToGame(gameID, onPlayerScoreUpdate, ::getGame)
    }

    override fun stopListeningToGameUpdates() {
        firebaseRepository.unsubscribeFromGame(gameID)
    }

    override fun initateGame(roundsNumber: Int, difficultySetting: String, player: Player) {
        this.rounds = roundsNumber
        this.difficulty = difficultySetting
        addPlayer(player)
        prefs.putString("playerID", player.id).flush()
        putGame()
    }

    //Static method in companion object.
    companion object{
        fun generatePin() : String{
            val letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
            val sb = StringBuilder()
            val random = Random()

            while (sb.length < 4) {
                val index = random.nextInt(letters.length)
                val character = letters[index]

                if (!sb.contains(character)) {
                    sb.append(character)
                }
            }

            return sb.toString()
        }
    }
}
