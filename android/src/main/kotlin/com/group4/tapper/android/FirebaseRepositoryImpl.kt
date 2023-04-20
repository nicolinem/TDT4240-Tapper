package com.group4.tapper.android

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.group4.tapper.Model.Player

data class Game(
    val gameId: String,
    val playerId: List<String>? = null,
    val playerScores: MutableMap<String, Int> = mutableMapOf()
)

class FirebaseRepositoryImpl : com.group4.tapper.FirebaseRepository {
    companion object {
        private const val TAG = "FirebaseRepositoryImpl"
    }

    val db = Firebase.firestore

    override fun createGame(gameId: String, playerId: String) {
        val game = Game(gameId, listOf(playerId), mutableMapOf<String, Int>(playerId to 0))
        db.collection("games").document(gameId).set(game)
    }

    override fun addPlayer(gameId: String, playerId: String) {
        val gameRef = db.collection("games").document(gameId)

        gameRef.update("playerId", FieldValue.arrayUnion(playerId))
            .addOnSuccessListener {
                gameRef.update("playerScores.$playerId", 0)
                    .addOnSuccessListener {

                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding player score", e)
                    }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding player", e)
            }
    }


    override fun updatePlayerScore(gameId: String, playerId: String, newScore: Double) {
        val gameRef = db.collection("games").document(gameId)

        gameRef.update("playerScores.$playerId", newScore)
            .addOnSuccessListener {

            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error updating player score", e)
            }
    }



    override fun subscribeToGame(gameId: String, onGameUpdate: (List<Player>) -> Unit) {
        val gameRef = db.collection("games").document(gameId)
        gameRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                Log.d(TAG, "Current data: ${snapshot.data}")
                // Parse playerScores from the snapshot
                val playerScores = snapshot["playerScores"] as? Map<String, Map<String, Any>> ?: emptyMap()

                // Create a list of Player objects
                val players = playerScores.map { (playerId, values) ->
                    Player().apply {
                        id = playerId
                        nickname = values["nickname"] as? String ?: ""
                        score = values["score"] as? Double ?: 0.0
                    }
                }

                // Pass the list of players to the callback
                onGameUpdate(players)

            } else {
                Log.d(TAG, "Current data: null")
            }
        }

    }


    override fun unsubscribeFromGame() {
    }
}
