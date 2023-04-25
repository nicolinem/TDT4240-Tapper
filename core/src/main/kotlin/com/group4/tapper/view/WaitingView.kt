package com.group4.tapper.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.group4.tapper.assets.TextureAsset
import com.group4.tapper.controller.MenuController
import com.group4.tapper.model.IMenuController
import com.group4.tapper.model.Player
import ktx.actors.onClick
import ktx.scene2d.*

class WaitingView(val controller: IMenuController) : View() {

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
        stage.actors {
            // Start of table
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)


                // Pin-code label
                row().padTop(100f)
                table {
                    row().expandX()
                    if(gameID!=null){
                        button("return_white"){
                            it.left()
                            it.width(screenWidth / 10f)
                            it.height(screenWidth / 10f)
                            it.padRight(0f)
                            onClick { controller.handleFinishGame() }}
                        label("Pin", "pink_bigger"){
                            it.padLeft(20f)
                        }
                        label(gameID, "white_bigger"){
                            it.right()
                        }
                    }
                }

                // "Players" label
                row().padTop(100f)
                label("Players", "white_bigger"){
                    setFontScale(0.7f)
                }

                // Line
                row().pad(0f)
                image(controller.getTextureAsset(TextureAsset.LINE))

                // Player list
                row().expand()
                add(tableN)

                // Join Game button
                row().bottom()
                textButton("Start Game", "new_game") {
                    onClick { controller.handleChangeToGameView() }
                }

                setFillParent(true)
                top()
                pack()
            }
        }

    }

    fun updateGameID(gameID:String){
        this.gameID=gameID
    }




}
