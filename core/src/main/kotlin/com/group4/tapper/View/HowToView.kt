package com.group4.tapper.View

import com.badlogic.gdx.Gdx
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




        stage.actors {
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
                label("How to play Tapper") {
                    setFontScale(2f)
                    setAlignment(center)
                }


                row().padTop(200f).fill().expand()
                scrollPane {
                    table {


                        defaults().fillY().expandY()
                        pad(50f)

                        row()
                        label("Welcome to the exciting world of Circle Tapping! " +
                            "This game is designed to test your responsiveness and quick thinking skills across multiple levels of difficulty.") {
                            wrap = true
                            it.width(screenWidth -100f)
                            setFontScale(1.5f)


                        }

                        row()
                        label("In each level, you'll be presented with a line of circles at the top of the screen. " +
                            "Your task is to tap the circles in the correct order as quickly as possible. The faster you are, the more points you'll earn.") {
                            wrap = true
                            it.width(screenWidth -60f)
                            setFontScale(1.5f)

                        }

                        row()
                        label("But be careful! If you tap the wrong circle, you'll lose 10 points." +
                            " So make sure you stay focused and accurate!") {
                            wrap = true
                            it.width(screenWidth -100f)
                            setFontScale(1.5f)

                        }



                        row()
                        label("There are several levels of difficulty to choose from, each with its own unique challenges. " +
                            "Can you make it to the highest level and become the ultimate Circle Tapping champion?") {
                            wrap = true
                            it.width(screenWidth -100f)
                            setFontScale(1.5f)

                        }

                        row()
                        label("Good luck and happy tapping!") {
                            wrap = true
                            it.width(screenWidth -100f)
                            setFontScale(1.5f)


                        }

                    }
                }


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
