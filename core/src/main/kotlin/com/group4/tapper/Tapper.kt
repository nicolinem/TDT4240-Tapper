package com.group4.tapper

import com.group4.tapper.controller.GameController
import com.group4.tapper.controller.MenuController
import com.group4.tapper.view.*
import com.group4.tapper.assets.DefaultAudioService
import com.group4.tapper.assets.MusicAsset
import com.group4.tapper.assets.TextureAtlasAsset
import com.group4.tapper.assets.createSkin
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.assets.async.AssetStorage
import ktx.async.KtxAsync
import ktx.collections.gdxArrayOf


class Tapper(IF: FirebaseRepository) : KtxGame<KtxScreen>() {

    lateinit var menuController: MenuController
    lateinit var gameController: GameController

    val assets: AssetStorage by lazy {
        KtxAsync.initiate()
        AssetStorage()
    }

    val audioService by lazy { DefaultAudioService(assets) }

    val FBIF = IF


    fun getInterface(): FirebaseRepository {
            return FBIF
        }

    override fun create() {
        menuController = MenuController(this, assets, audioService)
        gameController = GameController(this, assets, audioService)


        var old = System.currentTimeMillis()
        val assetRefs = gdxArrayOf(
            TextureAtlasAsset.values().filter { it.isSkinAtlas }.map { assets.loadAsync(it.descriptor) },
            MusicAsset.values().map { assets.loadAsync(it.descriptor) }

        ).flatten()
        KtxAsync.launch {
            assetRefs.joinAll()

            old = System.currentTimeMillis()
            createSkin(assets)

            addScreen(MainView(menuController))
            addScreen(HowToView(menuController))
            addScreen(NewGameView(menuController))
            addScreen(ResultView(menuController))
            addScreen(WaitingView(menuController))
            addScreen(JoinGameView(menuController))
            addScreen(GameView(gameController))
            addScreen(SettingsView(menuController))

            audioService.play(MusicAsset.MENU)

            setScreen<MainView>()


        }
    }




    }





