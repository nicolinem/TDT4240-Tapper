package com.group4.tapper.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.group4.tapper.assets.MusicAsset
import com.group4.tapper.assets.TextureAsset
import com.group4.tapper.controller.MenuController
import com.group4.tapper.model.IMenuController
import ktx.actors.onClick
import ktx.scene2d.*

class MainView(val controller: IMenuController) : View() {

    override fun setupUI() {


        val screenHeight = Gdx.graphics.height.toFloat()
        val screenWidth = Gdx.graphics.width.toFloat()

        stage.actors {
            // Start of table
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                row()
                table {

                    // Settings button
                    row().expandX()
                    button("settings") {
                        it.size(screenWidth/10f, screenWidth/10f)
                        it.left()
                        onClick { controller.handleChangeToSettingsView() }
                    }

                    // How to play button
                    textButton("How to play?") {
                        it.right()
                        pad(25f, 50f, 25f, 50f)
                        onClick { controller.handleChangeToHowToView() }
                    }
                }

                // Tapper logo
                row().width(screenWidth/1.5f).height(screenWidth/1.5f).padTop(screenHeight/8f)
                image(controller.getTextureAsset(TextureAsset.TAPPER_LOGO))

                // New Game button
                row()
                textButton("New Game", "new_game"){
                    onClick { controller.handleChangeToNewGameView() }
                }

                // Join Game button
                row()
                textButton("Join Game", "join_game") {
                    onClick { controller.handleChangeToJoinGameView() }
                }

                setFillParent(true)
                top()
                pack()
            }
        }

    }




}
