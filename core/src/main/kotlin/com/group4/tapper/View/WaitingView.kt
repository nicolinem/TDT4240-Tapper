package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.group4.tapper.Controller.MenuController
import com.group4.tapper.Model.Player
import com.group4.tapper.Tapper
import com.group4.tapper.assets.AudioService
import com.group4.tapper.assets.DefaultAudioService
import com.group4.tapper.assets.MusicAsset
import com.group4.tapper.assets.TextureAtlasAsset
import kotlinx.coroutines.launch
import ktx.assets.async.AssetStorage
import ktx.async.KtxAsync
import ktx.scene2d.*
import java.lang.System.currentTimeMillis

class WaitingView(val controller: MenuController) : View() {

    private val tableN = Table(Scene2DSkin.defaultSkin)



    fun updatePlayerScoreList(players: List<Player>) {
        stage.clear()
        System.out.println("CALLED")
        tableN.clear()

        for (p in players){
            tableN.row().padBottom(20f)
            tableN.add(Label("${p.nickname}", Scene2DSkin.defaultSkin))
        }
        setupUI()
    }

    fun subscribeToPlayerScoreUpdates(updatePlayerScoreList: (List<Player>) -> Unit) {
        controller.subscribeToPlayerScoreUpdates(updatePlayerScoreList)
    }



    override fun setupUI() {

        val screenHeight = Gdx.graphics.height.toFloat()
        val screenWidth = Gdx.graphics.width.toFloat()

        stage.actors {
            // Start of table
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                row().width(screenWidth/2.5f).right()
                textButton("How to play?")

                /*  row().width(screenWidth/1.5f).height(screenWidth/1.5f).padTop(screenHeight/8f)*/

                row().padTop(screenHeight/6f)



                add(tableN)

                // Join Game button
                row().bottom()
                textButton("Start Game", "new_game") {
                }.addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        controller.handleChangeToGameView()
                    }
                })
                setFillParent(true)
                top()
                pack()
            }
        }

    }


    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }

    override fun show() {
        super.show()
        subscribeToPlayerScoreUpdates(::updatePlayerScoreList)

    }



}
