package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.group4.tapper.Controller.MenuController
import com.group4.tapper.Tapper
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.scene2d.*


class NewGameView(val controller:MenuController): View() {

    private var rounds: Int = 2
    private var nickname: String = ""
    private var difficulty: String = "easy"

     override fun setupUI() {
        val screenWidth = Gdx.graphics.width.toFloat()

        stage.actors {
            // Start of table
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                row().width(screenWidth/10f).height(screenWidth/10f).expand().left().top()
                button("return_white").addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        controller.handleChangeToMainView()
                    }
                })


                // Pin
                row().expand()
               // label("Pin: 1234"){
              //      setFontScale(2f)
               // }


                // Nickname-field
                label("Nickname")
                row().width(screenWidth/2f).left()
                textField {
                    style.background.leftWidth += 40


                }.addListener(object : ChangeListener() {
                    override fun changed(event: ChangeEvent?, actor: Actor?) {
                        nickname = actor?.let { (it as TextField).text }.toString()

                    }
                })

                // Rounds-label
                row()
                label("Rounds")

                // Rounds-buttons
                row().left()
                buttonGroup(1,1){
                    it.fillX().expandX()
                    textButton("2", "toggle") {
                        pad(25f, 50f, 25f, 50f)
                    }
                    textButton("3", "toggle") {
                        pad(25f, 50f, 25f, 50f)
                        it.padLeft(50f)
                        it.padRight(50f)
                    }
                    textButton("4", "toggle") {
                        pad(25f, 50f, 25f, 50f)
                    }
                }.addListener(object : ChangeListener() {
                    override fun changed(event: ChangeEvent?, actor: Actor?) {
                        rounds =  actor.toString().split(" ").get(1).toInt()


                    }
                })

                // Difficulty-label
                row().left()
                label("Difficulty")

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

                // Create game-button
                row()
                textButton("Create Game", "selection") {
                    it.padTop(400f)
                    it.height(150f)
                }.addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        //System.out.println(actor.toString())
                        controller.createNewGame(nickname,rounds,difficulty)
                        //TODO ADD CHANGE VIEW
                       // TODO("Change view")
                    }
                })

                // Table-options
                setFillParent(true)
                top()
                pack()
            }
        }
    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        stage.disposeSafely()
        uiDispose()
    }
}
