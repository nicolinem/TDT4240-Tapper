package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.group4.tapper.Controller.MenuController
import ktx.scene2d.*
import javax.swing.Spring.width

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
                    setAlignment(Align.center)
                }

                row().padTop(150f)
                label("Hey there, welcome to the exciting world of circle tapping! " +
                    "This game is going to test your responsiveness and quick thinking skills.") {
                    wrap = true
                    it.width(screenWidth -100f)
                    setAlignment(Align.center)
                    setFontScale(1.5f)
                }

                row()
                label("You need to press the circles in the order matching the line of circles presented at the top of the screen. " +
                    "The faster you are, the more points you will score.") {
                    wrap = true
                    it.width(screenWidth -100f)
                    setAlignment(Align.center)
                    setFontScale(1.5f)
                }

                row()
                label("If you make a mistake, don't worry, the circle you pressed incorrectly" +
                    " will turn red, and you'll get another chance to press the correct one.") {
                    wrap = true
                    it.width(screenWidth -100f)
                    setAlignment(Align.center)
                    setFontScale(1.5f)
                }

                row()
                label("Good luck and happy tapping!") {
                    wrap = true
                    it.width(screenWidth -100f)
                    setAlignment(Align.center)
                    setFontScale(1.5f)
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
