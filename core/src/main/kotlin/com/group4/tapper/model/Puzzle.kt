package com.group4.tapper.model
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random
class Puzzle {

    private var randomNumbers : MutableList<Int> = createRandomNumbers()
    private val coordinates: MutableList<Pair<Float, Float>> = mutableListOf()

    fun createRandomNumbers(): MutableList<Int> {
        val list = mutableListOf<Int>()
        while (list.size < 6) {
            val number = (1..9).random()
            if (!list.contains(number)) {
                list.add(number)
            }
        }

        return list
    }
    fun createCoordinates(width:Int, height: Int, circleRadius:Float):  MutableList<Pair<Float, Float>>{
        val maxX: Float = width * 0.85.toFloat()
        val minX: Float = width * 0.05.toFloat()
        val maxY: Float = height * 0.6.toFloat()
        val minY: Float = height * 0.05.toFloat()
        for (i in randomNumbers) {
            var randomX: Float
            var randomY: Float
            var overlapping: Boolean

            do {
                overlapping = false
                randomX = minX + (maxX - minX) * Random.nextFloat()
                randomY = minY + (maxY - minY) * Random.nextFloat()

                // Check if the generated coordinates overlap with any already drawn circles
                for (coordinate in coordinates) {
                    if (distance(randomX, randomY, coordinate.first, coordinate.second) <= 2 * circleRadius) {
                        overlapping = true
                        break
                    }
                }
            } while (overlapping)

            val pair: Pair<Float, Float> = Pair(randomX, randomY)
            coordinates.add(pair)
        }
        return coordinates
    }
    private fun distance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return sqrt((x1 - x2).pow(2) + (y1 - y2).pow(2))
    }

}