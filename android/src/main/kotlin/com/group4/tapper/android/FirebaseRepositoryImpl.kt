package com.group4.tapper.android

import android.util.Log
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import com.group4.tapper.Model.Player

data class Game(
    val gameId: String,
    val playerScores: MutableMap<String, Pair<String,Int>>,
    val rounds: Int,
    val difficulty: String
)

class FirebaseRepositoryImpl : com.group4.tapper.FirebaseRepository {
    companion object {
        private const val TAG = "FirebaseRepositoryImpl"
    }

    val db = Firebase.firestore

    override fun createGame(gameId: String, map: MutableMap<String,Pair<String,Int>>, rounds: Int, difficulty :String) {
        val game = Game(gameId, map,rounds,difficulty)
        db.collection("games").document(gameId).set(game)
    }

    override fun addPlayer(gameId: String, playerId: String,pair: Pair<String,Int>) {
        val gameRef = db.collection("games").document(gameId)

        gameRef.get()
            .addOnSuccessListener { documentSnapshot ->
                val playerScores = documentSnapshot.get("playerScores") as MutableMap<String, Pair<String, Int>>?
                    ?: mutableMapOf()

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
                    Player(values["first"] as? String ?: "").apply {
                        id = playerId
                        score = values["second"] as? Int ?: 0
                    }
                }

                // Pass the list of players to the callback
                onGameUpdate(players)

            } else {
                Log.d(TAG, "Current data: null")
            }
        }

    }

    override fun updatePlayerScore(gameId: String, playerId: String, pair: Pair<String, Int>) {
       // TODO("Not yet implemented")
    }





    override fun unsubscribeFromGame() {
    }

    override fun checkIfGameExists(pin:String,method: (Boolean) -> Boolean) {
        val gameRef = db.collection("games").document(pin)
        gameRef.get().addOnSuccessListener { documentSnapshot ->
            method(documentSnapshot.exists())
        }
            .addOnFailureListener{e ->
                Log.w(TAG, "Listen failed.", e)
            }
    }

    override fun checkIfLastRound(gameID:String, method: (Boolean) -> Unit) {
        val gameRef = db.collection("games").document(gameID)
        gameRef.get().addOnSuccessListener { documentSnapshot ->
            val rounds = documentSnapshot.get("rounds")
            val currentRound = documentSnapshot.get("currentRound")
            println(rounds)
            println(currentRound)
            if (rounds == currentRound) {
                    method(true)
                } else {
                    method(false)
                }
            }
            .addOnFailureListener{e ->
                Log.w(TAG, "Listen failed.", e)
            }
    }

}
