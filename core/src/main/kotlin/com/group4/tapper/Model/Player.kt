package com.group4.tapper.Model

import java.util.concurrent.atomic.AtomicInteger
class Player {

    var id: Int = generatePlayerId()
        get() = field

    val score: Int = 0

    // Increments the ID by 1 when an instance is made
    companion object {
        private val counter = AtomicInteger(0)
        private fun generatePlayerId() = counter.incrementAndGet()
    }


}
