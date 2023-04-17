package com.group4.tapper.Model
import kotlin.random.Random
class Puzzle {

    var randomNumbers : List<Int> = List(6) { Random.nextInt(0, 9) }
        get() = field

}
