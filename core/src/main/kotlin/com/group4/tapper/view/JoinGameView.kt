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
import com.group4.tapper.model.Player
import ktx.actors.onChange
import ktx.actors.onClick
import ktx.scene2d.*

class JoinGameView(val controller: IMenuController): View() {

    private var pin: String = ""
    private var nickname : String =""
    private lateinit var feedback: Label

    //CHANGE THIS TO SET MAX PLAYERS PER GAME
    private val maxPlayersPerGame:Int = 8


    override fun setupUI() {
        val screenHeight = Gdx.graphics.height.toFloat()
        val screenWidth = Gdx.graphics.width.toFloat()

        stage.actors {
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                // Return button
                row().width(screenWidth/10f).height(screenWidth/10f).expand().left().top()
                /*button("return_white"){
                    onClick { controller.handleChangeToMainView() }
                }*/

                // Pin-label
                row()
                label("Pin").setAlignment(1)

                // Pin-textfield
                row().width(screenWidth/2f)
                textField(){
                    style.background.leftWidth += 40
                    onChange {
                        pin = text
                        prompt("")
                    }
                }

                // Nickname-label
                row().padTop(screenHeight/20f)
                label("Nickname").setAlignment(1)

                // Nickname-textfield
                row().width(screenWidth/2f)
                textField(){
                    onChange {
                        nickname = text
                        prompt("")
                    }
                }

                // Feedback if error
                row()
                feedback = label("")

                // Join game button with logic
                row().expand()
                textButton("Join Game", "selection"){
                    it.bottom()
                    onClick {
                        println(pin)
                        println(nickname)
                        if(pin.equals("")){
                            prompt("You need to insert a game pin!")
                        }
                        else if(nickname.equals("")){
                            prompt("You need to choose a nickname!")
                        }

                        else{
                            controller.doesGameExist(pin, ::refreshGoToGame)
                        }
                    }
                }

                setFillParent(true)
                bottom()
                pack()
            }
        }
    }



    fun refreshGoToGame(numOfPlayers:Int,exists:Boolean):Boolean{
        println(exists)
        if(exists){
            if(numOfPlayers>=maxPlayersPerGame){
                prompt("Max 8 players per game. Game is full.")
            }
            else{
                println("JOININGGAME")
                println(nickname)
                println(pin)
                controller.joinGame(Player(nickname), pin)
            }
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


}
