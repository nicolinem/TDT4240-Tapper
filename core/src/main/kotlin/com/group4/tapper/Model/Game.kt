package com.group4.tapper.Model

class Game(private val rounds: Int) {

    var players: Array<Player> = emptyArray<Player>()
        get() = field

    var puzzle: Puzzle = Puzzle()
        get() = field

    fun addPlayers(player: Player) {
        players[players.size - 1] = player

    }

    // Vet ikke om vi trenger denne
    fun getRounds(): Int {
        return rounds
    }

    fun gamePlay() {

    }



}
