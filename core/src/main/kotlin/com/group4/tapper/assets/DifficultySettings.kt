package com.group4.tapper.assets

class DifficultySettings (val difficulty: String) {
    val pointsReductionPerError: Int
    val pointsReductionPerTick: Int

    init {
        when (difficulty) {
            "easy" -> {
                pointsReductionPerError = 10
                pointsReductionPerTick = 1
            }
            "medium" -> {
                pointsReductionPerError = 25
                pointsReductionPerTick = 2
            }
            else -> {
                pointsReductionPerError = 50
                pointsReductionPerTick = 4
            }
        }
    }
}
