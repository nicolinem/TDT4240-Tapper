package com.group4.tapper

import com.group4.tapper.Model.Player


interface FirebaseRepository {
    fun createGame(gameId: String, map: MutableMap<String,Pair<String,Int>>, rounds: Int,difficulty:String)
    fun addPlayer(gameId: String, playerId: String,pair:Pair<String,Int>)
    fun subscribeToGame(gameId: String)
    fun updatePlayerScore(gameId: String, playerId: String, pair:Pair<String,Int>)
    fun unsubscribeFromGame()
}
