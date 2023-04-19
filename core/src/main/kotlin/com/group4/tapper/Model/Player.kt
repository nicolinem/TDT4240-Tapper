package com.group4.tapper.Model

import java.lang.Integer.min
import java.math.BigInteger
import java.security.SecureRandom
import java.util.concurrent.atomic.AtomicInteger
class Player {

    var nickname: String = ""
        set(value) {
            field = value
        }

    var id: String = generateRandID()
        get() = field

    var score: Double = 0.0
        get() = field
        set(value) {
            if(value >= 0.0)
                field = value
        }

    private fun generateRandID(): String {
        val random = SecureRandom()
        val numBytes = 10 * 5 / 8 + 1
        val id = BigInteger(50, random).toString(32)
        return id.substring(0, min(id.length, 10))
    }

}
