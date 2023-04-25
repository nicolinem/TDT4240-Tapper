package com.group4.tapper

import com.group4.tapper.assets.*
import com.group4.tapper.controller.GameController
import com.group4.tapper.controller.MenuController
import com.group4.tapper.model.Game
import com.group4.tapper.model.IGameController
import com.group4.tapper.model.IMenuController
import com.group4.tapper.view.*
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.assets.async.AssetStorage
import ktx.async.KtxAsync
import ktx.collections.gdxArrayOf

class Tapper(val IF: FirebaseRepository) : KtxGame<KtxScreen>() {

    lateinit var menuController: IMenuController
    lateinit var gameController: IGameController

    val assets: AssetStorage by lazy {
        KtxAsync.initiate()
        AssetStorage()
    }

    val audioService by lazy { DefaultAudioService(assets) }
    val game by lazy { Game(IF) }


    fun getInterface(): FirebaseRepository {
        return IF
    }

    override fun create() {
        createControllers()
        loadAssets()
    }

    private fun createControllers() {
        menuController = MenuController(this, game, assets, audioService)
        gameController = GameController(this, game, assets, audioService)
    }

    private fun loadAssets() {
        val assetRefs = gdxArrayOf(
            TextureAtlasAsset.values().filter { it.isSkinAtlas }.map { assets.loadAsync(it.descriptor) },
            MusicAsset.values().map { assets.loadAsync(it.descriptor) },
            SoundAsset.values().map { assets.loadAsync(it.descriptor) },
            TextureAsset.values().map { assets.loadAsync(it.descriptor) }
        ).flatten()

        KtxAsync.launch {
            assetRefs.joinAll()

            createSkin(assets)
            createScreens()

            menuController.playMusic(MusicAsset.GAME)
            setScreen<MainView>()
        }
    }

    private fun createScreens() {
        addScreen(MainView(menuController))
        addScreen(HowToView(menuController))
        addScreen(NewGameView(menuController))
        addScreen(ResultView(menuController))
        addScreen(WaitingView(menuController))
        addScreen(JoinGameView(menuController))
        addScreen(GameView(gameController))
        addScreen(SettingsView(menuController))
    }
}
