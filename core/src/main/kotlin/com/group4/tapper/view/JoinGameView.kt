package com.group4.tapper.view

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.group4.tapper.controller.MenuController
import com.group4.tapper.model.Player
import ktx.scene2d.*

class JoinGameView(val controller: MenuController): View() {

    private var pin: String = ""
    private var nickname : String =""
    private lateinit var feedback: Label


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
                       controller.handleChangeToMainView()
                    }
                })

                row()
                label("Pin").setAlignment(1)
                row().width(screenWidth/2f)
                textField(){
                    style.background.leftWidth += 40
                }.addListener(object : ChangeListener() {
                    override fun changed(event: ChangeEvent?, actor: Actor?) {
                        pin = actor?.let { (it as TextField).text }.toString()
                        prompt("")
                    }
                })
                row().padTop(screenHeight/20f)
                label("Nickname").setAlignment(1)
                row().width(screenWidth/2f)
                textField()
                    .addListener(object : ChangeListener() {
                    override fun changed(event: ChangeEvent?, actor: Actor?) {
                        nickname = actor?.let { (it as TextField).text }.toString()
                        prompt("")
                    }
                })
                row()
                feedback = label("")

                row().padTop(screenHeight/4f)
                textButton("Join Game", "selection")
                    .addListener(object : ClickListener() {
                        override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        if(pin.equals("")){
                            prompt("You need to insert a game pin!")
                        }
                        else if(nickname.equals("")){
                            prompt("You need to choose a nickname!")
                        }

                        else{
                            controller.sendRefresh(pin, ::refreshGoToGame)
                        }
                        }
                    })

                setFillParent(true)
                bottom()
                pack()
            }
        }
    }



    fun refreshGoToGame(exists:Boolean):Boolean{
        if(exists){
            controller.joinGame(Player(nickname), pin)
        }
        else{
            prompt("This game does not exists, try again.")
        }
        return exists
    }

    private fun prompt(input:String){
        feedback.setAlignment(1)
        feedback.setText(input)
    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }
}