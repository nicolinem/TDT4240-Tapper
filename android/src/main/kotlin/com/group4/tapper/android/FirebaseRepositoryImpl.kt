package com.group4.tapper.android

import android.util.Log
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore

data class Game(
    val gameId: String,
    val playerScores: MutableMap<String, Pair<String,Double>>,
    val rounds: Int,
    val difficulty: String
)

class FirebaseRepositoryImpl : com.group4.tapper.FirebaseRepository {
    companion object {
        private const val TAG = "FirebaseRepositoryImpl"
    }

    val db = Firebase.firestore

    override fun createGame(gameId: String, map: MutableMap<String,Pair<String,Double>>, rounds: Int, difficulty :String) {
        val game = Game(gameId, map,rounds,difficulty)
        db.collection("games").document(gameId).set(game)
    }

    override fun addPlayer(gameId: String, playerId: String,pair: Pair<String,Double>) {
        val gameRef = db.collection("games").document(gameId)

        gameRef.get()
            .addOnSuccessListener { documentSnapshot ->
                val playerScores = documentSnapshot.get("playerScores") as MutableMap<String, Pair<String, Double>>?
                    ?: mutableMapOf<String, Pair<String, Double>>()

                playerScores[playerId] = pair

                gameRef.set(hashMapOf("playerScores" to playerScores), SetOptions.merge())
                    .addOnSuccessListener {
                        Log.d(TAG, "Player added successfully")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding player", e)
                    }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting document", e)
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

        }
    }


    override fun unsubscribeFromGame() {
    }
}
