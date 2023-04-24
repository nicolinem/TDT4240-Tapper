package com.group4.tapper.model

import com.group4.tapper.assets.GameState

interface GameModel {
    var gameID: String
    var difficulty: String
    fun removePlayer(playerID: String)
    fun putGame()
    fun addPlayer(player: Player)
    fun joinGame(player: Player)
    fun updatePlayerScore(points:Int, playerID: String)
    fun resetPlayerStats(playerID: String)
    fun sendRefresh(pin:String,refreshMethod:(Int,Boolean) -> Boolean)
    fun checkIfLastRound(method:(Boolean) -> Unit)
    fun playAgain()
    fun checkGameState(playerID: String): GameState
    fun subscribeToPlayerScoreUpdates(gameId: String, onPlayerScoreUpdate: (Int,Int, List<Player>) -> Unit)
    fun stopListeningToGameUpdates()
}
interface PuzzleModel {
    val randomNumbers: List<Int>
    fun createCoordinates(width: Int, height: Int, circleRadius: Float): MutableList<Pair<Float, Float>>
}
