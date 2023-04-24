package com.group4.tapper.controller

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.audio.Sound
import com.group4.tapper.model.Game
import com.group4.tapper.model.Player
import com.group4.tapper.Tapper
import com.group4.tapper.view.*
import com.group4.tapper.assets.AudioService
import com.group4.tapper.assets.GameState
import com.group4.tapper.assets.MusicAsset
import com.group4.tapper.assets.SoundAsset
import com.group4.tapper.model.GameModel
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
    internal lateinit var game:GameModel
    private val FB = tapper.getInterface()
    private val prefs : Preferences = Gdx.app.getPreferences("prefs")


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
        val player = Player(nickname)
        game.addPlayer(player)
        prefs.putString("playerID",player.id)

        prefs.flush()

        game.putGame()
    }

    fun joinGame(player: Player, gamepin: String) {
        game.apply {
            this.gameID = gamepin
        }
        game.joinGame(player)
        prefs.putString("playerID", player.id)
        prefs.flush()
        tapper.setScreen<WaitingView>()
    }

    fun playAgain(){
        game.playAgain()
        tapper.setScreen<WaitingView>()
    }

    fun addPlayerToGame(player: Player){
        game.joinGame(player)
        tapper.setScreen<WaitingView>()
    }

    fun handleChangeToWaitRoom(){
        game.resetPlayerStats(prefs.getString("playerID"))
        tapper.setScreen<WaitingView>()
    }

    fun handleChangeToMainView(){
        prefs.remove("gameID")
        prefs.flush()
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
        playMusic(MusicAsset.GAME)
        tapper.setScreen<GameView>()
    }

    fun subscribeToPlayerScoreUpdates(onPlayerScoreUpdate: (Int,Int,List<Player>) -> Unit) {
        game.subscribeToPlayerScoreUpdates(this.game.gameID, onPlayerScoreUpdate)
    }

    fun sendRefresh(pin:String,refreshMethod:(Int,Boolean) -> Boolean){
        game.sendRefresh(pin, refreshMethod)
    }

    fun checkIfLastRound( method: (Boolean) -> Unit){
        game.checkIfLastRound(method)
    }



    fun handleChangeToNewGameView() {
        tapper.setScreen<NewGameView>()
    }


    fun getGameID(method: (String)-> Unit) {
        method(game.gameID)
    }


    fun handleChangeToSettingsView() {
        tapper.setScreen<SettingsView>()
    }

    fun getPlayerID():String{
        return prefs.getString("playerID")
    }

    fun removePlayer(string: String) {
        game.removePlayer(string)
    }





    fun handleFinishGame() {
        game.stopListeningToGameUpdates()
        game.removePlayer(prefs.getString("playerID"))
        tapper.setScreen<MainView>()
    }



    fun playMusic(asset: MusicAsset) {
        audioService.play(asset)
    }

    fun toggleMusic(){
        audioService.musicEnabled = !audioService.musicEnabled
    }
    fun toggleSound(){
        audioService.soundsEnabled = !audioService.soundsEnabled
    }


}
