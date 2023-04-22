package com.group4.tapper

import com.group4.tapper.Model.Game
import com.group4.tapper.Model.Player


interface FirebaseRepository {
    fun createGame(game: Game)
    fun joinGame(gameId: String, player: Player)
    fun subscribeToGame(gameId: String, onGameUpdate: (List<Player>) -> Unit, updateGame: (List<Player>) -> Unit)
/*
    fun updatePlayerScore(gameId: String, playerID: String)
*/

    fun unsubscribeFromGame()
    fun checkIfGameExists(pin:String,method:(Boolean) -> Boolean)
    fun checkIfLastRound(gameID:String, method:(Boolean) -> Unit)
}
