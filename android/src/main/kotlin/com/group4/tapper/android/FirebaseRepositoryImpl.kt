package com.group4.tapper.android

import android.util.Log
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import com.group4.tapper.Model.Game
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

    override fun createGame(game: Game) {
        db.collection("games").document(game.gameID).set(game)
    }


    override fun joinGame(gameId: String, player: Player, ) {
        val gameRef = db.collection("games").document(gameId)


        gameRef.get()
            .addOnSuccessListener { documentSnapshot ->
                val playerScores = documentSnapshot.get("playerScores") as MutableMap<String, Player>?
                    ?: mutableMapOf()

                playerScores[player.id] = player

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


    override fun subscribeToGame(gameId: String, onGameUpdate: (List<Player>) -> Unit, updateGame: (List<Player>) -> Unit): Unit {
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

                Log.d(TAG, "Current data: $playerScores")
                // Create a list of Player objects
                val players = playerScores.map { (playerId, values) ->
                    Player((values["nickname"] ?: "") as String, playerId).apply {
                        score = values["score"].toString().toInt()
                    }
                }

                // Pass the list of players to the callback
                onGameUpdate(players)


            } else {
                Log.d(TAG, "Current data: null")
            }
        }

    }




/*    override fun updatePlayerScore(gameId: String, playerID: String) {
        val gameRef = db.collection("games").document(gameId)

        gameRef.update(playerID, player.nickname, player.score)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }*/





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
