package com.group4.tapper.Model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.group4.tapper.FirebaseRepository
import java.util.*

class Game(private val firebaseRepository:
           FirebaseRepository,
           ) {

    private val prefs : Preferences = Gdx.app.getPreferences("prefs")

    val playerScores:MutableMap<String,Player> = mutableMapOf()

    var playerScoresList:List<Player> = listOf()


    var gameID:String = ""
        set(value) {
            field = value
            prefs.putString("gameID", value)
            prefs.flush()
        }


    var rounds: Int = 3
    var currentRound:Int = 1

    var difficulty:String = "medium"



    init {

        if (gameID.isNotEmpty()) {
            this.gameID = gameID
        } else {
            this.gameID = generatePin()
        }

        with(prefs) {
            clear()
            putString("gameID", this@Game.gameID)
            flush()
        }


    }

    fun putGame() {
        firebaseRepository.createGame(this)

    }

    fun addPlayer(player: Player){
        playerScores[player.id] = player
    }

    fun joinGame(player: Player) {
        playerScores[player.id] = player
        firebaseRepository.joinGame(this.gameID, player)
        prefs.putString("playerID",player.id)
        prefs.flush()
    }

    fun updatePlayerScore(points:Int, playerID: String){
        playerScores[playerID]?.incrementRound()
        playerScores[playerID]?.updateScore(points)
        playerScores[playerID]?.let { firebaseRepository.joinGame(this.gameID, it) }
    }
    fun resetPlayerStats(playerID: String){
        playerScores[playerID]?.resetStats()
        playerScores[playerID]?.let { firebaseRepository.joinGame(this.gameID, it) }
    }

    fun sendRefresh(pin:String,refreshMethod:(Boolean) -> Boolean){
        firebaseRepository.checkIfGameExists(pin,refreshMethod)
    }

    fun checkIfLastRound(method:(Boolean) -> Unit){
        firebaseRepository.checkIfLastRound(gameID,method)
    }
    fun playAgain(){
        println(playerScores)
        for ((key,value) in playerScores){
            println(key)
            println(value.nickname)
            playerScores[key]?.resetStats()
        }
        putGame()
    }


/**
    fun generateScore(time: Int, player: Player, amountWrong: Int, game: Game) {
        val maxPoint = 100
        val maxSeconds = 30

        val timePoints = maxPoint/maxSeconds
        val score = 100 - (time * timePoints).coerceAtMost(100.0) - 10 * amountWrong

        player.score = score.coerceAtLeast(0.0)
        FirebaseRepository.updatePlayerScore(game.gameID, player.id, player.score)
    }



    // Sorterer spillere fra høyest til lavest.
    fun generateWinner(players: Array<Player>): Array<Player> {
        val sortedPlayers = players.sortedByDescending { it.score }
        return sortedPlayers.toTypedArray()
    }
*/

private fun getPlayers(players: List<Player>) {
    this.playerScoresList = players
    for (p in players){
        for (p in players)
            playerScores[p.id] = p
    }
}

    fun subscribeToPlayerScoreUpdates(gameId: String, onPlayerScoreUpdate: (Int,Int, List<Player>) -> Unit) {

        firebaseRepository.subscribeToGame(gameId, onPlayerScoreUpdate, ::getPlayers)
    }



    //Static method in companion object.
    companion object{
        fun generatePin() : String{
            val letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
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
