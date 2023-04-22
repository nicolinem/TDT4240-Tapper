package com.group4.tapper.Model

import java.lang.Integer.min
import java.math.BigInteger
import java.security.SecureRandom
import java.util.concurrent.atomic.AtomicInteger
class Player ( nickname:String, id: String = ""){

    var nickname: String = nickname
        set(value) {
            field = value
        }
        get(){
            return field
        }
    var id: String = generateRandID()
        get() = field


   var score: Int = 0


    init {
        if (id.equals("")){
            this.id = generateRandID()

        }
        else{
            this.id = id

        }
    }

   /* var pair:Pair<String,Int> = Pair(nickname,score)
        get(){
            return field
        }*/




    private fun generateRandID(): String {
        val random = SecureRandom()
        val numBytes = 10 * 5 / 8 + 1
        val id = BigInteger(50, random).toString(32)
        return id.substring(0, min(id.length, 10))
    }

    fun updateScore(score: Int) {
        this.score += score
    }

}
