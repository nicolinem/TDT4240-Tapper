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
    private var gameID:String = ""




    fun updatePlayerScoreList(rounds:Int,currentRound:Int,players: List<Player>) {
        stage.clear()
        tableN.clear()

        for (p in players){
            tableN.row().padBottom(20f)
            tableN.add(Label("${p.nickname}", Scene2DSkin.defaultSkin))
        }
        setupUI()
    }

    fun subscribeToPlayerScoreUpdates(updatePlayerScoreList: (Int,Int,List<Player>) -> Unit) {
        controller.subscribeToPlayerScoreUpdates(updatePlayerScoreList)
    }
    override fun show() {
        super.show()
        subscribeToPlayerScoreUpdates(::updatePlayerScoreList)
        controller.getGameID(::updateGameID)
    }





    override fun setupUI() {

        val screenHeight = Gdx.graphics.height.toFloat()
        val screenWidth = Gdx.graphics.width.toFloat()

        stage.actors {
            // Start of table
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                row().padTop(100f)
                if(gameID!=null){
                    label("Pin: $gameID", "white_bigger").setAlignment(1    )
                }

                row().padTop(100f)

                label("Players")

                row().pad(0f)
                image(Texture(Gdx.files.internal("images/Line.png")))


                row().expand().top()
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

    fun updateGameID(gameID:String){
        this.gameID=gameID
    }


    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }




}
