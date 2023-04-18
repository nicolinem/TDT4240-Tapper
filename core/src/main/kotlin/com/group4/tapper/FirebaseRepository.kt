package com.group4.tapper



interface FirebaseRepository {
    fun createGame(gameId: String, playerId: String)
    fun addPlayer(gameId: String, playerId: String, callback: () -> Boolean)
    fun subscribeToGame(gameId: String)
    fun updatePlayerScore(gameId: String, playerId: String, newScore: Int, callback: () -> Unit)
    fun unsubscribeFromGame()
}
