package com.group4.tapper.Controller

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.group4.tapper.Tapper
import com.group4.tapper.View.ResultView
import com.group4.tapper.assets.AudioService
import com.group4.tapper.assets.MusicAsset
import ktx.assets.async.AssetStorage

class GameController(val tapper: Tapper,
                     val assets: AssetStorage,
                     val audioService: AudioService
) {

    private val prefs : Preferences = Gdx.app.getPreferences("prefs")



    fun handleVictory(points:Int){
/*
        val game = Game(tapper,prefs.getString("rounds").toInt(),prefs.getString("nickname"),prefs.getString("difficulty"),prefs.getString("gameID"))
*/
        println("handleVictory")

        audioService.play(MusicAsset.MENU)
        System.out.println(prefs.getString("playerID"))
        tapper.menuController.game.updatePlayerScore(points, prefs.getString("playerID"))
        tapper.setScreen<ResultView>()
    }

}
