package com.group4.tapper.View;

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.group4.tapper.Controller.MenuController
import com.group4.tapper.Model.Player
import ktx.scene2d.actors
import ktx.scene2d.label
import ktx.scene2d.table
import ktx.scene2d.textButton

class ResultView(val controller: MenuController): View() {

    private val tableN = Table(skin)

    val gameId = "dummy" // Replace this with the actual game id
    fun updatePlayerScoreList(players: List<Player>) {
        stage.clear()
        System.out.println("CALLED")
        tableN.clear()
        var num = 1
        for (p in players){
            tableN.row().padBottom(20f).expandX().fillX()
            var newTable = Table(skin)
            newTable.add(Label("${num}. ${p.nickname}", skin)).left()
            newTable.add(Label("${p.score}", skin)).right()
            tableN.add(newTable)
            num++
        }
        setupUI()
    }

    fun subscribeToPlayerScoreUpdates(gameId: String, updatePlayerScoreList: (List<Player>) -> Unit) {
        controller.subscribeToPlayerScoreUpdates(gameId, updatePlayerScoreList)
    }
    override fun show() {
        subscribeToPlayerScoreUpdates(gameId, ::updatePlayerScoreList)
    }
    override fun setupUI(){
        val screenWidth = Gdx.graphics.width.toFloat()
        val screenHeight = Gdx.graphics.height.toFloat()

        stage.actors {
            // Start of table
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                row().top().left().pad(100f, 100f, 0f, 0f)
                label("Winner!", "white_bigger")

                // Print player names on screen
                row().expand().fillX()
                add(tableN).top()

                // Join Game button
                row().bottom()
                textButton("Finish", "new_game") {
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
