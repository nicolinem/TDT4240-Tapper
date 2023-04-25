package com.group4.tapper.controller

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.graphics.Texture
import com.group4.tapper.model.Player
import com.group4.tapper.Tapper
import com.group4.tapper.view.*
import com.group4.tapper.assets.AudioService
import com.group4.tapper.assets.GameState
import com.group4.tapper.assets.MusicAsset
import com.group4.tapper.assets.TextureAsset
import com.group4.tapper.model.GameModel
import com.group4.tapper.model.IMenuController
import ktx.assets.async.AssetStorage

class MenuController(private val tapper: Tapper,
                     private val game: GameModel,
                     private val assets: AssetStorage,
                     private val audioService: AudioService) : IMenuController {


    private val prefs: Preferences = Gdx.app.getPreferences("prefs")


    override fun createNewGame(nickname: String, roundsNumber: Int, difficultySetting: String) {
        game.initateGame(roundsNumber, difficultySetting, Player(nickname))
    }

    override fun joinGame(player: Player, gamepin: String) {
        game.apply {
            this.gameID = gamepin
        }
        game.joinGame(player)
        prefs.putString("playerID", player.id).flush()
        tapper.setScreen<WaitingView>()
    }


    override fun playAgain(){
        game.playAgain()
        tapper.setScreen<WaitingView>()
    }



    override fun handleChangeToWaitRoom(){
        game.resetPlayerStats(prefs.getString("playerID"))
        tapper.setScreen<WaitingView>()
    }

    override fun handleChangeToMainView(){
        prefs.remove("gameID")
        prefs.flush()
        tapper.setScreen<MainView>()
    }

     override fun handleChangeToJoinGameView(){
        tapper.setScreen<JoinGameView>()
    }


     override fun handleNewGame(nickname: String, rounds: Int, difficulty: String){
         createNewGame(nickname, rounds, difficulty)
        tapper.setScreen<WaitingView>()
    }



    override fun handleChangeToHowToView() {
        tapper.setScreen<HowToView>()
    }

    override fun handleChangeToGameView(){
        playMusic(MusicAsset.GAME)
        tapper.setScreen<GameView>()
    }

    override fun subscribeToPlayerScoreUpdates(onPlayerScoreUpdate: (Int, Int, List<Player>) -> Unit) {
        game.subscribeToPlayerScoreUpdates(this.game.gameID, onPlayerScoreUpdate)
    }

    override fun doesGameExist(pin:String, callback:(Int, Boolean) -> Boolean){
        game.doesGameExist(pin, callback)
    }

    override fun checkIfLastRound(method: (Boolean) -> Unit){
        game.checkIfLastRound(method)
    }


    override fun handleChangeToNewGameView() {
        tapper.setScreen<NewGameView>()
    }


    override fun getGameID(method: (String)-> Unit) {
        method(game.gameID)
    }


    override fun handleChangeToSettingsView() {
        tapper.setScreen<SettingsView>()
    }

    override fun getPlayerID():String{
        return prefs.getString("playerID")
    }



    override fun handleFinishGame() {
        game.stopListeningToGameUpdates()
        game.removePlayer(prefs.getString("playerID"))
        tapper.setScreen<MainView>()
    }



    override fun playMusic(asset: MusicAsset) {
        audioService.play(asset)
    }

    override fun toggleMusic(){
        audioService.musicEnabled = !audioService.musicEnabled
    }
    override fun toggleSound(){
        audioService.soundsEnabled = !audioService.soundsEnabled
    }

    override fun checkGameState(): GameState {
        return game.checkGameState(prefs.getString("playerID"))
    }

    override fun getTextureAsset(asset: TextureAsset): Texture {
        return assets[asset.descriptor]
    }


}
