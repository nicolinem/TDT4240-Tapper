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
import ktx.scene2d.*

class WaitingView(val controller: MenuController) : View() {

    private val tableN = Table(skin)

    val gameId = "IcKG" // Replace this with the actual game id

    fun updatePlayerScoreList(players: List<Player>) {
        stage.clear()
        System.out.println("CALLED")
        tableN.clear()
        for (p in players){
            tableN.row().padBottom(20f)
            tableN.add(Label("${p.nickname} ${p.score}", skin))
        }
        setupUI()
    }

    fun subscribeToPlayerScoreUpdates(gameId: String, updatePlayerScoreList: (List<Player>) -> Unit) {
        controller.subscribeToPlayerScoreUpdates(gameId, updatePlayerScoreList)
    }
    override fun show() {
        subscribeToPlayerScoreUpdates(gameId, ::updatePlayerScoreList)
    }

    override fun setupUI() {

        val screenHeight = Gdx.graphics.height.toFloat()
        val screenWidth = Gdx.graphics.width.toFloat()

        stage.actors {
            // Start of table
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                // Print player names on screen
                row().padTop(screenHeight/6f).expand()
                add(tableN)

                // Join Game button
                row().bottom()
                textButton("Start Game", "new_game") {
                }.addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        controller.handleChangeToMainView()
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

}
