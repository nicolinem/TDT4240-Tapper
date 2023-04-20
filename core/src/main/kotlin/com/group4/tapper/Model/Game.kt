package com.group4.tapper.Model

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.group4.tapper.FirebaseRepository
import com.group4.tapper.Tapper
import java.math.BigInteger
import java.security.SecureRandom
import java.util.*

class Game(private val tapper: Tapper, private val rounds: Int, private val nickname:String,private val difficulty:String, gameID:String ="") {



    private val gameID:String
    init{

        if (gameID.equals("")){
            this.gameID = generatePin()
        }
        else{
            this.gameID = gameID
        }
    }

    private val playerScores:MutableMap<String,Pair<String,Int>> = mutableMapOf()

    private val firebaseRepository: FirebaseRepository = tapper.getInterface()
    private val prefs : Preferences = Gdx.app.getPreferences("prefs")

    fun getGameID():String{
        return gameID
    }

    fun createGame() {
        val player = Player(nickname)
        playerScores[player.id] = player.pair
        firebaseRepository.createGame(gameID,playerScores,rounds,difficulty)
        //Update context
        prefs.putString("gameID",getGameID())
        prefs.putString("rounds",rounds.toString())
        prefs.putString("difficulty",difficulty)
        prefs.flush()
    }

    fun addPlayer(gameID:String, nickname: String) {
        val player = Player(nickname)
        firebaseRepository.addPlayer(gameID, player.id,player.pair)
        //Update context
        prefs.putString("nickname",nickname)
        prefs.putString("playerID",player.id)
        prefs.flush()
    }

    fun updatePlayerScore(points:Int,playerID:String,nickname: String){
        val player = Player(nickname)
        firebaseRepository.updatePlayerScore(gameID,playerID,player.pair)
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
    private fun generateRandID(): String {
        val random = SecureRandom()
        val numBytes = 10 * 5 / 8 + 1
        val id = BigInteger(50, random).toString(32)
        return id.substring(0, 10)
    }


    fun subscribeToPlayerScoreUpdates(gameId: String, onPlayerScoreUpdate: (players: List<Player>) -> Unit) {
        firebaseRepository.subscribeToGame(gameId, onPlayerScoreUpdate)

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
