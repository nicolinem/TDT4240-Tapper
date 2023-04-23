package com.group4.tapper.view

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.group4.tapper.assets.AudioService
import com.group4.tapper.controller.MenuController
import ktx.scene2d.*

class SettingsView(val controller: MenuController): View() {
    override fun setupUI() {


        stage.actors {
            table {
                setFillParent(true)
                top()
                defaults().fillX().expandX()
                defaults().pad(50f)

                row().width(screenWidth / 10f).height(screenWidth / 10f).left().top()
                button("return_white").addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        controller.handleChangeToMainView()
                    }
                })

                row()
                label("Settings", "white_bigger") {
                }

                row().padTop(screenHeight / 20f)
                table {
                    row().expandX()
                    label("Music", "white_bigger") {
                        setFontScale(0.8f)
                        it.left()
                    }
                    button("toggle_setting") {
                        it.right()
                        it.size(screenWidth/5f, screenWidth/10f)
                        toggle()
                    }.addListener(object : ChangeListener() {
                        override fun changed(event: ChangeEvent?, actor: Actor?) {
                            controller.audioService.enabled = !controller.audioService.enabled
                        }
                    })
                }

                row()
                table {
                    row().expandX()
                    label("Sound FX", "white_bigger") {
                        setFontScale(0.8f)
                        it.left()
                    }
                    button("toggle_setting") {
                        it.right()
                        it.size(screenWidth/5f, screenWidth/10f)
                        toggle()
                    }
                }

                pack()
            }
        }
    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }

}
