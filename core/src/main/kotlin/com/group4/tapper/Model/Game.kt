package com.group4.tapper.Model

class Game( rounds: Int) {

    var players: Array<Player> = emptyArray<Player>()
        get() = field

    var puzzle: Puzzle = Puzzle()
        get() = field

    var rounds: Int = rounds;


}
