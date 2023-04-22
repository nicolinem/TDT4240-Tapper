package com.group4.tapper.Controller

import com.group4.tapper.Model.Game
import com.group4.tapper.Model.Player
import com.group4.tapper.Tapper
import com.group4.tapper.View.*
import com.group4.tapper.assets.AudioService
import com.group4.tapper.assets.MusicAsset
import kotlinx.coroutines.CoroutineScope
import ktx.assets.async.AssetStorage

class MenuController(tapper: Tapper,
                     val assets: AssetStorage,
                     val audioService: AudioService  ) {

    val tapper:Tapper
        get() {
            return field
        }


    private var numberOfRounds: Int
    private var difficulty:String
    internal lateinit var game:Game
    private val FB = tapper.getInterface()


    init{
        this.tapper = tapper
        numberOfRounds = 2
        difficulty = "easy"
        game = Game(FB)
    }

    fun createNewGame(nickname:String, roundsNumber:Int, difficultySetting:String){
        game = Game(FB).apply {
            rounds = roundsNumber
            difficulty = difficultySetting
        }
        game.addPlayer(Player(nickname))
        game.putGame()
    }

    fun joinGame(player: Player, gamepin: String) {
        game.apply {
            gameID = gamepin

        }
        game.joinGame(player)
        tapper.setScreen<WaitingView>()

    }

    fun addPlayerToGame(player: Player){
        game.joinGame(player)
        tapper.setScreen<WaitingView>()
    }

    fun handleChangeToMainView(){
        tapper.setScreen<MainView>()
    }

     fun handleChangeToJoinGameView(){
        tapper.setScreen<JoinGameView>()
    }


     fun handleNewGame(nickname: String, rounds: Int, difficulty: String){
         createNewGame(nickname, rounds, difficulty)
        tapper.setScreen<WaitingView>()
    }



    fun handleChangeToHowToView() {
        tapper.setScreen<HowToView>()
    }

    fun handleChangeToGameView(){
        audioService.play(MusicAsset.GAME)
        tapper.setScreen<GameView>()
    }

    fun subscribeToPlayerScoreUpdates(onPlayerScoreUpdate: (List<Player>) -> Unit) {
        game.subscribeToPlayerScoreUpdates(this.game.gameID, onPlayerScoreUpdate)
    }

    fun sendRefresh(pin:String,refreshMethod:(Boolean) -> Boolean){
        game.sendRefresh(pin, refreshMethod)
    }

    fun checkIfLastRound( method: (Boolean) -> Unit){
        game.checkIfLastRound(method)
    }



    fun handleChangeToNewGameView() {
        tapper.setScreen<NewGameView>()
    }

    fun playMusic() {

    }


}
