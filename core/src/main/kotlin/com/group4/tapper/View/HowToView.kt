package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Value
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Align.*
import com.group4.tapper.Controller.MenuController
import ktx.scene2d.*
import javax.swing.Spring.width
import javax.swing.text.StyleConstants.setAlignment

class HowToView(val controller: MenuController): View() {
    override fun setupUI() {
        val screenHeight = Gdx.graphics.height.toFloat()
        val screenWidth = Gdx.graphics.width.toFloat()

        /*stage.actors {
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                row().width(screenWidth / 10f).height(screenWidth / 10f).left().top()
                button("return_white").addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        controller.handleChangeToMainView()
                    }
                })

                row()
                label("How to create game", "white_bigger")

                row().pad(0f, 0f, 0f, 0f).fill().expand()
                scrollPane {
                    table {

                        defaults().fillX().expandX()
                        pad(50f)
                    }
                }


                setFillParent(true)
                top()
                pack()
            }
        }*/

        stage.actors {
            scrollPane("transparent") {
                setFillParent(true)
                setScrollingDisabled(true, false)
                table{
                    defaults().pad(40f)
                    defaults().fillX().expandX()
                    top()

                    row().width(screenWidth / 10f).height(screenWidth / 10f).left().top()
                    button("return_white").addListener(object : ClickListener() {
                        override fun clicked(event: InputEvent?, x: Float, y: Float) {
                            controller.handleChangeToMainView()
                        }
                    })

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
                    image(Texture(Gdx.files.internal("images/create_game.png")))

                    row()
                    label("2. Choose a nickname, number and difficulty", "white_bigger"){
                        setFontScale(0.5f)
                        setAlignment(center)
                        wrap = true
                    }

                    row().fill(false, false)
                    image(Texture(Gdx.files.internal("images/number_of_rounds.png")))

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
                    image(Texture(Gdx.files.internal("images/join_game.png")))

                    row()
                    label("2. Enter game pin and nickname", "white_bigger"){
                        setFontScale(0.5f)
                        setAlignment(center)
                        wrap = true
                    }

                    row().fill(false, false)
                    image(Texture(Gdx.files.internal("images/pin-input.png")))

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
                    image(Texture(Gdx.files.internal("images/game_tutorial.png")))

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
