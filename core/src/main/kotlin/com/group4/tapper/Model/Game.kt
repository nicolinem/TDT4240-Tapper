package com.group4.tapper.Model

import com.group4.tapper.FirebaseRepository
import java.math.BigInteger
import java.security.SecureRandom
import java.util.*

class Game(private val rounds: Int, private val FirebaseRepository: FirebaseRepository) {


    private val gameID: String = generateRandID()
        get() = field

   // private val pin: Int
    private var difficultyLevel: String = "Low"
        set(value) {
            field = value
        }
    private var players: Array<Player> = emptyArray<Player>()
        get() = field

    var puzzle: Puzzle = Puzzle()
        get() = field

/**
    //Hva skal jeg ha med her egnt? Skjønner ikke hvordan denne er koblet opp
    fun createGame(diffLevel: String, player: Player, game: Game) {
        //trenger jeg denne
        difficultyLevel = diffLevel
        FirebaseRepository.createGame(game.gameID, player.id)
    }

    fun addPlayers(player: Player, game: Game) {
        //Add to list, trenger vi denne hvis vi kan hente den fra database
        players[players.size - 1] = player
        //Add to database
        FirebaseRepository.addPlayer( game.gameID, player.id, )


    }

    */

    // Vet ikke om vi trenger denne
    fun getRounds(): Int {
        return rounds
    }


    fun generateScore(time: Double, player: Player, amountWrong: Int, game: Game) {
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

    private fun generateRandID(): String {
        val random = SecureRandom()
        val numBytes = 10 * 5 / 8 + 1
        val id = BigInteger(50, random).toString(32)
        return id.substring(0, 10)
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
