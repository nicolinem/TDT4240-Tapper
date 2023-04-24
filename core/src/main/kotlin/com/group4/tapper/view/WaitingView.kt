package com.group4.tapper.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.group4.tapper.controller.MenuController
import com.group4.tapper.model.Player
import ktx.scene2d.*

class WaitingView(val controller: MenuController) : View() {

    private val tableN = Table(Scene2DSkin.defaultSkin)
    private var gameID:String = ""




    fun updatePlayerScoreList(rounds:Int,currentRound:Int,players: List<Player>) {
        Gdx.app.postRunnable {
            stage.clear()
            tableN.clear()
            for (p in players) {
              tableN.row().padBottom(20f).expandX()
              var label = Label(p.nickname, Scene2DSkin.defaultSkin)
              label.setFontScale(1.5f)
              if (p.id == controller.getPlayerID()) label.setColor(124/255f, 252/255f, 0f, 1f)
              tableN.add(label)
            }
            setupUI()
        }
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
                table {
                    row().expandX()
                    if(gameID!=null){
                        label("Pin", "pink_bigger"){
                            it.left()
                        }
                    }
                    label(gameID, "white_bigger"){
                        it.right()
                    }
                }

                row().padTop(100f)

                label("Players", "white_bigger"){
                    setFontScale(0.7f)
                }

                row().pad(0f)
                image(Texture(Gdx.files.internal("images/line.png")))


                row().expand()
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
