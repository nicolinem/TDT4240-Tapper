package com.group4.tapper.View

import com.badlogic.gdx.assets.AssetManager
import com.group4.tapper.Tapper
import com.group4.tapper.assets.MusicAssets
import com.group4.tapper.assets.TextureAtlasAssets
import com.group4.tapper.assets.load
import ktx.app.KtxScreen
import ktx.scene2d.actors
import ktx.scene2d.label

class LoadingView(  game: Tapper,
                private val assets: AssetManager): View(game) {

    override fun setupUI() {
        stage.actors {
            label("Loading")
        }
    }

    override fun update(dt: Float) {
        assets.update()
        if (assets.isFinished) {
            stage.clear()
            stage.actors {
                label("Finished loading")
            }

        }
    }

    override fun show() {
        MusicAssets.values().forEach { assets.load(it) }
/*        SoundAssets.values().forEach { assets.load(it) }*/
        TextureAtlasAssets.values().forEach { assets.load(it) }
    }



}
