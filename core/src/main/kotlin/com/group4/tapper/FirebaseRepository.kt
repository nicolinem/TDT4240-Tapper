package com.group4.tapper

import com.group4.tapper.Model.Player


interface FirebaseRepository {
    fun createGame(gameId: String, playerId: String)
    fun addPlayer(gameId: String, playerId: String)
    fun subscribeToGame(gameId: String, onGameUpdate: (List<Player>) -> Unit)
    fun updatePlayerScore(gameId: String, playerId: String, newScore: Double)
    fun unsubscribeFromGame()
}
