package com.group4.tapper.Model

class Game(private val rounds: Int) {

    var difficultyLevel: String = "Low"
        set(value) {
            field = value
        }

    private var players: Array<Player> = emptyArray<Player>()
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


    /*Alternativ 1 jeg endrer poengsummen her
    Alternativ 2 jeg returnerer en poengsum
    */
    fun generateScore(time: Double, player: Player, amountWrong: Int) {
        val maxPoint = 100
        val maxSeconds = 30

        val timePoints = maxPoint/maxSeconds
        val score = 100 - (time * timePoints).coerceAtMost(100.0) - 10 * amountWrong

        player.score = score.coerceAtLeast(0.0)
    }


    // Sorterer spillere fra høyest til lavest.
    fun generateWinner(players: Array<Player>): Array<Player> {
        val sortedPlayers = players.sortedByDescending { it.score }
        return sortedPlayers.toTypedArray()
    }

}
