package com.group4.tapper.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.group4.tapper.controller.MenuController
import com.group4.tapper.model.IMenuController
import ktx.actors.onChange
import ktx.actors.onChangeEvent
import ktx.actors.onClick
import ktx.assets.disposeSafely
import ktx.scene2d.*


class NewGameView(val controller: IMenuController): View() {

    private var rounds: Int = 2
    private var nickname: String = ""
    private var difficulty: String = "easy"
    private lateinit var feedback: Label

    override fun setupUI() {
        val screenWidth = Gdx.graphics.width.toFloat()

        stage.actors {
            // Start of table
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                // Return button
                row().expand().width(screenWidth/10f).height(screenWidth/10f).left().top()
                button("return_white"){
                    onClick { controller.handleChangeToMainView() }
                }

                // Nickname-field
                row()
                label("Nickname").setAlignment(1)

                // Nickname-textfield
                row().width(screenWidth/2f)
                textField {
                    style.background.leftWidth += 40
                    onChange {
                        nickname = text
                        prompt("")
                    }
                }

                // Rounds-label
                row()
                label("Rounds").setAlignment(1)

                // Rounds-buttons
                row()
                buttonGroup(1,1) {
                    textButton("2", "toggle") {
                        pad(25f, 50f, 25f, 50f)
                    }
                    textButton("3", "toggle") {
                        pad(25f, 50f, 25f, 50f)
                        it.padLeft(50f)
                        it.padRight(50f)
                    }
                    textButton("4", "toggle") {
                        pad(
                            25f, 50f, 25f,
                            50f
                        )
                    }
                }.addListener(object : ChangeListener() {
                    override fun changed(event: ChangeEvent?, actor: Actor?) {
                        rounds = actor.toString().split(" ")[1].toInt()
                    }
                })

                // Difficulty-label
                row().left()
                label("Difficulty").setAlignment(1)

                // Difficulty-buttons
                row().left()
                buttonGroup(1,1){
                    textButton("easy", "toggle") {
                        pad(25f, 50f, 25f, 50f)
                    }
                    textButton("medium", "toggle") {
                        pad(25f, 50f, 25f, 50f)
                        it.padLeft(50f)
                        it.padRight(50f)
                    }
                    textButton("hard", "toggle") {
                        pad(25f, 50f, 25f, 50f)
                    }
                }.addListener(object : ChangeListener() {
                    override fun changed(event: ChangeEvent?, actor: Actor?) {
                        val tmpString = actor.toString().split(" ")
                        difficulty = tmpString.get(1)
                    }
                })

                row().padTop(100f)
                feedback = label("")

                // Create game-button
                row().expand()
                textButton("Create Game", "selection") {
                    it.bottom()
                    it.height(150f)
                    onClick {
                        if(nickname.equals("")){
                            prompt("You need to choose a nickname!")
                        }

                        else{
                            controller.handleNewGame(nickname, rounds, difficulty)
                        }
                    }
                }

                // Table-options
                setFillParent(true)
                bottom()
                pack()
            }
        }
    }



    private fun prompt(input:String){
        feedback.setAlignment(1)
        feedback.setText(input)
    }



    override fun dispose() {
        stage.disposeSafely()
        uiDispose()
    }
}
