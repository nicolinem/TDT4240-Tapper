package com.group4.tapper.android

import android.util.Log
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import com.group4.tapper.model.Game
import com.group4.tapper.model.Player

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

    private lateinit var listener: ListenerRegistration

    val db = Firebase.firestore

    override fun createGame(game: Game) {
        db.collection("games").document(game.gameID).set(game)
    }


    override fun joinGame(gameId: String, player: Player) {
        val gameRef = db.collection("games").document(gameId)

        var playerScores: MutableMap<String, Player>

        gameRef.get().addOnSuccessListener { documentSnapshot ->
            playerScores = documentSnapshot.get("playerScores") as MutableMap<String, Player>?
                ?: mutableMapOf()

            playerScores[player.id] = player
            gameRef.update("playerScores", playerScores)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
        }
            .addOnFailureListener{e ->
                Log.w(TAG, "Listen failed.", e)
            }

    }






    override fun subscribeToGame(gameId: String, onGameUpdate: (Int,Int,List<Player>) -> Unit, updateGame: (List<Player>,Int,String, Int) -> Unit)  {
        val gameRef = db.collection("games").document(gameId)
        listener = gameRef.addSnapshotListener { snapshot, e ->
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
                    Player((values["nickname"] ?: "") as String).apply {
                        score = values["score"].toString().toInt()
                        currentRound = (values["currentRound"] as Long).toInt()
                        id=playerId
                    }
                }
                val rounds = snapshot["rounds"] as Long
                val currentRound = snapshot["currentRound"] as Long
                val diff = snapshot["difficulty"] as String


                // Pass the list of players to the callback
                onGameUpdate(rounds.toInt(),currentRound.toInt(),players)
                updateGame(players,rounds.toInt(),diff, currentRound.toInt())


            } else {
                Log.d(TAG, "Current data: null")
            }
        }

    }

    override fun removePlayer(gameId: String, players: MutableMap<String, Player> ) {
        val gameRef = db.collection("games").document(gameId)

        gameRef.update("playerScores", players)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    override fun unsubscribeFromGame(gameID: String) {
        this.listener.remove()
    }


/*    override fun updatePlayerScore(gameId: String, playerID: String) {
        val gameRef = db.collection("games").document(gameId)

        gameRef.update(playerID, player.nickname, player.score)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }*/







    override fun checkIfGameExists(pin:String,method: (Int,Boolean) -> Boolean) {
        val gameRef = db.collection("games").document(pin)
        gameRef.get().addOnSuccessListener { documentSnapshot ->
            val playerScores = documentSnapshot.get("playerScores") as MutableMap<String, Player>?
                ?: mutableMapOf()
            method(playerScores.size,documentSnapshot.exists())
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

    override fun updatePlayers(gameID: String, players: Map<String, Player>) {
        val gameRef = db.collection("games").document(gameID)

        gameRef.update("playerScores", players)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    }


