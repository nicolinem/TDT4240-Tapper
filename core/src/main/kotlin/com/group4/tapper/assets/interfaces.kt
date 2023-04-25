package com.group4.tapper.model

import com.badlogic.gdx.graphics.Texture
import com.group4.tapper.assets.GameState
import com.group4.tapper.assets.MusicAsset
import com.group4.tapper.assets.TextureAsset
import com.group4.tapper.view.GameView

interface GameModel {
    var gameID: String
    var difficulty: String
    fun removePlayer(playerID: String)
    fun putGame()
    fun addPlayer(player: Player)
    fun joinGame(player: Player)
    fun updatePlayerScore(points:Int, playerID: String)
    fun resetPlayerStats(playerID: String)
    fun doesGameExist(pin:String, refreshMethod:(Int, Boolean) -> Boolean)
    fun checkIfLastRound(method:(Boolean) -> Unit)
    fun playAgain()
    fun checkGameState(playerID: String): GameState
    fun subscribeToPlayerScoreUpdates(gameId: String, onPlayerScoreUpdate: (Int,Int, List<Player>) -> Unit)
    fun stopListeningToGameUpdates()
    fun initateGame(roundsNumber: Int, difficultySetting: String, player: Player)
}
interface PuzzleModel {
    val randomNumbers: List<Int>
    fun createCoordinates(width: Int, height: Int, circleRadius: Float): MutableList<Pair<Float, Float>>
}

interface IGameController {
    var gameView: GameView
    fun start()
    fun getTextureAsset(asset: TextureAsset): Texture
    fun handleVictory()
    fun setDifficulty()
    fun updateAudioService()
    fun getRemainingPoints(): String
    fun getPuzzleList(): MutableList<Int>
    fun getCoordinates(): MutableList<Pair<Float, Float>>
    fun handleButtonClick(i: Int, buttonText: String)
}

interface IMenuController {
    fun createNewGame(nickname: String, roundsNumber: Int, difficultySetting: String)
    fun joinGame(player: Player, gamepin: String)
    fun playAgain()
    fun handleChangeToWaitRoom()
    fun handleChangeToMainView()
    fun handleChangeToJoinGameView()
    fun handleNewGame(nickname: String, rounds: Int, difficulty: String)
    fun handleChangeToHowToView()
    fun handleChangeToGameView()
    fun subscribeToPlayerScoreUpdates(onPlayerScoreUpdate: (Int, Int, List<Player>) -> Unit)
    fun checkIfLastRound(method: (Boolean) -> Unit)
    fun handleChangeToNewGameView()
    fun getGameID(method: (String) -> Unit)
    fun handleChangeToSettingsView()
    fun getPlayerID(): String
    fun handleFinishGame()
    fun playMusic(asset: MusicAsset)
    fun toggleMusic()
    fun toggleSound()
    fun checkGameState(): GameState
     fun getTextureAsset(asset: TextureAsset): Texture
     fun doesGameExist(pin:String, callback:(Int, Boolean) -> Boolean)
}
