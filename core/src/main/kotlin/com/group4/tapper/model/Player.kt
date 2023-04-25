package com.group4.tapper.model

import java.lang.Integer.min
import java.math.BigInteger
import java.security.SecureRandom

class Player ( nickname:String){

    var nickname: String = nickname
        set(value) {
            field = value
        }
        get(){
            return field
        }
    var id: String = generateRandID()
        set(value){
            field = value
        }


   var score: Int = 0
   var currentRound:Int = 0





    private fun generateRandID(): String {
        val random = SecureRandom()
        val numBytes = 10 * 5 / 8 + 1
        val id = BigInteger(50, random).toString(32)
        return id.substring(0, min(id.length, 10))
    }

    fun incrementRound(){
        currentRound+=1
    }

    fun updateScore(score: Int) {
        this.score += score
    }

    fun resetStats(){
        this.score = 0
        this.currentRound = 0
    }

}
