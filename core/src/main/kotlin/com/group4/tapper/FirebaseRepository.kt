package com.group4.tapper

import com.group4.tapper.Model.Player


interface FirebaseRepository {
    fun createGame(gameId: String, map: MutableMap<String,Pair<String,Double>>, rounds: Int,difficulty:String)
    fun addPlayer(gameId: String, playerId: String,pair:Pair<String,Double>)
    fun subscribeToGame(gameId: String, onGameUpdate: (List<Player>) -> Unit)
    fun updatePlayerScore(gameId: String, playerId: String, newScore: Double)
    fun unsubscribeFromGame()
}
