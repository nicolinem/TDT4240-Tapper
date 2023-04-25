package com.group4.tapper

import com.group4.tapper.model.Game
import com.group4.tapper.model.Player


interface FirebaseRepository {
    fun createGame(game: Game)
    fun joinGame(gameId: String, player: Player)
    fun subscribeToGame(gameId: String, onGameUpdate: (Int,Int,List<Player>) -> Unit, updateGame: (List<Player>,Int,String, Int) -> Unit )

    fun checkIfGameExists(pin:String,method:(Int,Boolean) -> Boolean)
    fun removePlayer(gameId: String, players: MutableMap<String, Player> )
    fun unsubscribeFromGame(gameID: String)

    fun checkIfLastRound(gameID:String, method:(Boolean) -> Unit)

}
