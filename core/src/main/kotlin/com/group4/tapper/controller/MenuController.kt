package com.group4.tapper.controller

import com.group4.tapper.model.Game
import com.group4.tapper.model.Player
import com.group4.tapper.Tapper
import com.group4.tapper.view.*

class MenuController(tapper: Tapper) {

    private val tapper:Tapper
    private var numberOfRounds: Int
    private var difficulty:String
    private val firebaseRepository = tapper.getInterface()


    init{
        this.tapper = tapper
        numberOfRounds = 0
        difficulty = ""

    }

    fun createNewGame(nickname:String, rounds:Int, difficulty:String){
        val player = Player()
        player.nickname = nickname
        val map = mutableMapOf<String,Pair<String,Double>>()
        map[player.id] = Pair(nickname,player.score)
        firebaseRepository.createGame(Game.generatePin(),map,rounds,difficulty)

    }

    fun addPlayerToGame(pin:String,nickname: String){
        val player = Player()
        player.nickname = nickname
        val pair = Pair(nickname,player.score)
        firebaseRepository.addPlayer(pin,player.id,pair)
    }

    fun handleChangeToMainView(){
        tapper.setScreen<MainView>()
    }

     fun handleChangeToJoinGameView(){
        tapper.setScreen<JoinGameView>()
    }

     fun handleChangeToNewGameView(){
        tapper.setScreen<NewGameView>()
    }

    fun handleChangeToHowToView() {
        tapper.setScreen<HowToView>()
    }






    fun getTapper(): Tapper {
        return this.tapper
    }

}
