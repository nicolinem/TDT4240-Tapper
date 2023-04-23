package com.group4.tapper

import com.group4.tapper.model.Game
import com.group4.tapper.model.Player


interface FirebaseRepository {
    fun createGame(game: Game)
    fun joinGame(gameId: String, player: Player)
    fun subscribeToGame(gameId: String, onGameUpdate: (Int,Int,List<Player>) -> Unit, updateGame: (List<Player>,Int,String) -> Unit)
/*
    fun updatePlayerScore(gameId: String, playerID: String)
*/

    fun unsubscribeFromGame()
    fun checkIfGameExists(pin:String,method:(Boolean) -> Boolean)
    fun checkIfLastRound(gameID:String, method:(Boolean) -> Unit)
}
