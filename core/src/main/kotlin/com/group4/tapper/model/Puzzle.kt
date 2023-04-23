package com.group4.tapper.model
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random
class Puzzle {

    private var _randomNumbers : MutableList<Int> = createRandomNumbers()
    val randomNumbers: List<Int> get() = _randomNumbers

    fun createRandomNumbers(): MutableList<Int> {
        return (1..9).shuffled().take(6).toMutableList()
    }


    private fun generateRandomCoordinate(minX: Float, maxX: Float, minY: Float, maxY: Float, circleRadius: Float, coordinates: MutableList<Pair<Float, Float>>): Pair<Float, Float> {
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

        return Pair(randomX, randomY)
    }

    fun createCoordinates(width: Int, height: Int, circleRadius: Float): MutableList<Pair<Float, Float>> {
        val maxX: Float = width * 0.85.toFloat()
        val minX: Float = width * 0.05.toFloat()
        val maxY: Float = height * 0.6.toFloat()
        val minY: Float = height * 0.05.toFloat()
        val coordinates: MutableList<Pair<Float, Float>> = mutableListOf()

        repeat(createRandomNumbers().size) {
            coordinates.add(generateRandomCoordinate(minX, maxX, minY, maxY, circleRadius, coordinates))
        }

        return coordinates
    }

    private fun distance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return sqrt((x1 - x2).pow(2) + (y1 - y2).pow(2))
    }

}
