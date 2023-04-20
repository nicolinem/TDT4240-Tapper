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


    val gameId = "ZS6E" // Replace this with the actual game id

    fun updatePlayerList(players: List<Player>) {
        for (p in players){
            tableN.add(Label("Current data: ${p.nickname}", skin))
        }




    /*    stage.addActor(tableN)*/

        setupUI()
       /* for (player in players) {
            System.out.println("Current data: ${player.id}")
        }*/
    }

    override fun show() {
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

                row().width(screenWidth/2.5f).right()
                textButton("How to play?")

              /*  row().width(screenWidth/1.5f).height(screenWidth/1.5f).padTop(screenHeight/8f)*/

                row().padTop(screenHeight/6f)



                // New Game button
                row().padTop(screenHeight/3f)
                textButton("New Game", "new_game"){
                }

                // Join Game button
                row()
                textButton("Join Game", "join_game") {
                }.addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        game.setScreen<WaitingView>()
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
