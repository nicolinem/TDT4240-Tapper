package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
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



    private val prefs : Preferences = Gdx.app.getPreferences("prefs") // Replace this with the actual game id

    private fun updatePlayerScoreList(players: List<Player>) {
        stage.clear()
        tableN.clear()

        for (p in players){
            System.out.println(p.nickname)
            tableN.row()
            tableN.add(Label(p.nickname, skin))
        }
        setupUI()
    }

    private fun subscribeToPlayerScoreUpdates(gameId: String, updatePlayerScoreList: (List<Player>) -> Unit) {
        controller.subscribeToPlayerScoreUpdates(gameId, updatePlayerScoreList)
    }

    override fun show() {
        System.out.println(prefs.get())
        super.show()
        System.out.println("GAMEID:  ${prefs.getString("gameID")}")
        subscribeToPlayerScoreUpdates(prefs.getString("gameID"), ::updatePlayerScoreList)
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
                row()
                textButton("Start Game", "join_game") {
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

}
