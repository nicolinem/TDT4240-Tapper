package com.group4.tapper.android

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore

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



    override fun subscribeToGame(gameId: String) {
        val gameRef = db.collection("games").document(gameId)

        gameRef.addSnapshotListener { snapshot: DocumentSnapshot?, error: FirebaseFirestoreException? ->
            if (error != null) {
                Log.w("GameSubscription", "Failed to subscribe to game", error)
            }

/*            if (snapshot != null && snapshot.exists()) {
*//*
                onUpdate(snapshot)
*//*
            }*/
        }
    }


    override fun unsubscribeFromGame() {
    }
}
