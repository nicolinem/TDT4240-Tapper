package com.group4.tapper


interface FirebaseRepository {
    fun createGame(gameId: String, map: MutableMap<String,Pair<String,Double>>, rounds: Int,difficulty:String)
    fun addPlayer(gameId: String, playerId: String,pair:Pair<String,Double>)
    fun subscribeToGame(gameId: String)
    fun updatePlayerScore(gameId: String, playerId: String, newScore: Double)
    fun unsubscribeFromGame()
}
