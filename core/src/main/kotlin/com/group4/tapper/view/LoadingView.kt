package com.group4.tapper.view

import com.badlogic.gdx.assets.AssetManager
import com.group4.tapper.controller.MenuController
import ktx.scene2d.actors
import ktx.scene2d.label

class LoadingView( val  controller: MenuController,
                private val assets: AssetManager): View() {

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





}
