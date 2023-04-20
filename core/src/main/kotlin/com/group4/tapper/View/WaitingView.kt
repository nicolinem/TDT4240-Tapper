package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.group4.tapper.Model.Player
import com.group4.tapper.Tapper
import ktx.scene2d.*

class WaitingView(game: Tapper) : View(game) {

    private val firebaseRepository = game.getInterface()
    private val tableN = Table(skin)


    val gameId = "yzgJ" // Replace this with the actual game id

    fun updatePlayerList(players: List<Player>) {
        stage.clear()
        tableN.clear() // Clear the table to remove previous content
        System.out.println(players[0].nickname)

        for (p in players) {
            tableN.row().padTop(60f)
            tableN.add(Label(p.nickname, skin)).row() // Add the new content and create a new row for each player
        }

    /*    stage.addActor(tableN)*/
        setupUI()

        /*    stage.addActor(tableN)*/

       /* for (player in players) {
            System.out.println("Current data: ${player.id}")
        }*/
    }

    override fun show() {
        Gdx.input.inputProcessor = stage
        firebaseRepository.subscribeToGame(gameId, ::updatePlayerList)
    }

    override fun setupUI() {

        val screenHeight = Gdx.graphics.height.toFloat()
        val screenWidth = Gdx.graphics.width.toFloat()

        stage.actors {
            // Start of table
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                row().width(screenWidth / 2.5f).right()
                textButton("How to play?")

                row().width(screenWidth / 1.2f).padTop(70f)
                label("Waiting for players")
                row()

                add(tableN).colspan(2) // Add the tableN to the stage

                row().padTop(screenHeight / 2f)

                textButton("Start Game", "join_game") {
                }.addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        game.setScreen<MainView>()
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
