package com.group4.tapper

import com.group4.tapper.Model.Player


interface FirebaseRepository {
    fun createGame(gameId: String, playerId: String)
    fun addPlayer(gameId: String, playerId: String, callback: () -> Boolean)
    fun subscribeToGame(gameId: String, onUpdate: () -> Player)
    fun updatePlayerScore(gameId: String, playerId: String, newScore: Int, callback: () -> Unit)
    fun unsubscribeFromGame()
}
