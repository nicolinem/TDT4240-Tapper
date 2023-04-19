package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.group4.tapper.Tapper
import ktx.scene2d.*

class JoinGameView(game: Tapper): View(game) {

    override fun setupUI() {
        val screenHeight = Gdx.graphics.height.toFloat()
        val screenWidth = Gdx.graphics.width.toFloat()

        stage.actors {
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                row().width(screenWidth/10f).height(screenWidth/10f).expand().left().top()
                button("return_white").addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        game.setScreen<MainView>()
                    }
                })

                row().width(screenWidth/2f)
                textField("Pin"){
                    style.background.leftWidth += 40
                }

                row().width(screenWidth/2f)
                textField("Nickname")

                row().padTop(screenHeight/3f)
                textButton("Join Game", "selection")

                setFillParent(true)
                bottom()
                pack()
            }
        }
    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }
}
