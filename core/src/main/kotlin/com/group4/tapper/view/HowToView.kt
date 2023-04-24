package com.group4.tapper.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align.*
import com.group4.tapper.assets.TextureAsset
import com.group4.tapper.controller.MenuController
import ktx.actors.onClick
import ktx.scene2d.*

class HowToView(val controller: MenuController): View() {
    override fun setupUI() {

        stage.actors {
            scrollPane("transparent") {
                setFillParent(true)
                setScrollingDisabled(true, false)
                table{
                    defaults().pad(40f)
                    defaults().fillX().expandX()
                    top()

                    row().width(screenWidth / 10f).height(screenWidth / 10f).left().top()
                    button("return_white"){
                        onClick { controller.handleChangeToMainView()}
                    }

                    row().pad(25f, 0f, 50f, 0f)
                    label("How to create game", "white_bigger"){
                        setFontScale(0.9f)
                        setAlignment(center)
                    }

                    row()
                    label("1. Press \"Create Game\"", "white_bigger"){
                        setFontScale(0.5f)
                        setAlignment(center)
                        wrap = true
                    }

                    row().fill(false, false)
                    image(controller.assets[TextureAsset.CREATE_GAME.descriptor])

                    row()
                    label("2. Choose a nickname, number and difficulty", "white_bigger"){
                        setFontScale(0.5f)
                        setAlignment(center)
                        wrap = true
                    }

                    row().fill(false, false)
                    image(controller.assets[TextureAsset.NUMBER_OF_ROUNDS.descriptor])

                    row().pad(50f, 0f, 50f, 0f)
                    label("How to join game", "white_bigger"){
                        setFontScale(0.9f)
                        setAlignment(center)
                    }

                    row()
                    label("1. Press \"Join Game\"", "white_bigger"){
                        setFontScale(0.5f)
                        setAlignment(center)
                        wrap = true
                    }

                    row().fill(false, false)
                    image(controller.assets[TextureAsset.JOIN_GAME.descriptor])

                    row()
                    label("2. Enter game pin and nickname", "white_bigger"){
                        setFontScale(0.5f)
                        setAlignment(center)
                        wrap = true
                    }

                    row().fill(false, false)
                    image(controller.assets[TextureAsset.PIN_INPUT.descriptor])

                    row().pad(50f, 0f, 50f, 0f)
                    label("How to play", "white_bigger"){
                        setFontScale(0.9f)
                        setAlignment(center)
                    }

                    row()
                    label("Tap the buttons in correct order", "white_bigger"){
                        setFontScale(0.5f)
                        setAlignment(center)
                        wrap = true
                    }

                    row().fill(false, false).pad(0f)
                    image(controller.assets[TextureAsset.GAME_TUTORIAL.descriptor])

                    row().pad(100f, 0f, 100f, 0f)
                    label("Happy tapping!", "white_bigger"){
                        setFontScale(0.7f)
                        setAlignment(center)
                        wrap = true
                    }
                }
            }
        }
   }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }
}
