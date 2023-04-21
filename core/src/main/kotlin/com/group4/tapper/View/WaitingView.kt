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
            tableN.add(Label("${p.nickname}", skin))
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

                row().top().expandX().fillX().pad(100f, 100f, 100f, 100f)
                table{
                    row().expandX()
                    label("Players", "white_bigger"){
                        it.left()
                    }
                    label("A5F4", "pink_bigger"){
                        it.right()
                    }
                }

                // Print player names on screen
                row().expand().fillX().pad(0f, 100f, 0f, 100f).top()
                /*add(tableN).top()*/
                table {
                    row().padBottom(50f).expandX()
                    label("Alf Inge Wang") {
                        it.left()
                        setFontScale(1.25f)
                    }

                    row().padBottom(50f).expandX()
                    label("Barack Obama") {
                        it.left()
                        setFontScale(1.25f)
                    }

                    row().padBottom(50f).expandX()
                    label("Tim Cook") {
                        it.left()
                        setFontScale(1.25f)
                        setColor(0.2f, 1f, 0.1f, 1f)
                    }

                    row().padBottom(50f).expandX()
                    label("Mark Zuckerberg") {
                        it.left()
                        setFontScale(1.25f)
                    }

                    row().padBottom(50f).expandX()
                    label("Elon Musk") {
                        it.left()
                        setFontScale(1.25f)
                    }
                }
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
