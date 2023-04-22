package com.group4.tapper.Controller

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.group4.tapper.Model.Game
import com.group4.tapper.Tapper

class GameController(val tapper:Tapper) {

    private val prefs : Preferences = Gdx.app.getPreferences("prefs")



    fun handleVictory(points:Int){
/*
        val game = Game(tapper,prefs.getString("rounds").toInt(),prefs.getString("nickname"),prefs.getString("difficulty"),prefs.getString("gameID"))
*/
        System.out.println(prefs.getString("playerID"))
        tapper.menuController.game.updatePlayerScore(points, prefs.getString("playerID"))
    }

}
