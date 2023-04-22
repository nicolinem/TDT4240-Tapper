package com.group4.tapper.Controller

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.group4.tapper.Model.Game
import com.group4.tapper.Tapper
import com.group4.tapper.View.ResultView

class GameController(val tapper:Tapper) {

    private val prefs : Preferences = Gdx.app.getPreferences("prefs")



    fun handleVictory(points:Int){
/*
        val game = Game(tapper,prefs.getString("rounds").toInt(),prefs.getString("nickname"),prefs.getString("difficulty"),prefs.getString("gameID"))
*/
        println("handleVictory")

        tapper.menuController.game.updatePlayerScore(points, prefs.getString("playerID"))
        tapper.setScreen<ResultView>()
    }

}
