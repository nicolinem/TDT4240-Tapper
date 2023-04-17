package com.group4.tapper.Model

import java.util.concurrent.atomic.AtomicInteger
class Player {

    var id: Int = generatePlayerId()
        get() = field

    var score: Double = 0.0
        get() = field
        set(value) {
            if(value >= 0.0)
                field = value
        }

    // Increments the ID by 1 when an instance is made
    companion object {
        private val counter = AtomicInteger(0)
        private fun generatePlayerId() = counter.incrementAndGet()
    }


}
