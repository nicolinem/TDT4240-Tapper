package com.group4.tapper.View;

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.group4.tapper.Controller.MenuController
import com.group4.tapper.Model.Player
import ktx.scene2d.actors
import ktx.scene2d.label
import ktx.scene2d.table
import ktx.scene2d.textButton
import ktx.style.skin

class ResultView(val controller: MenuController): View() {

    private val tableN = Table(skin)

    val gameId = "1exQ" // Replace this with the actual game id
    private var lastRound:Boolean = false



    fun updatePlayerScoreList(players: List<Player>) {
        stage.clear()
        System.out.println(players)
        tableN.clear()
        var num = 1
        for (p in players){
            tableN.row().padBottom(50f).expandX()
            tableN.add(Label("${num}. ${p.nickname}", skin)).left()
            tableN.add(Label("${p.score} pt", skin)).right()
            num++
        }
        setupUI()
    }

    fun subscribeToPlayerScoreUpdates(gameId: String, updatePlayerScoreList: (List<Player>) -> Unit) {
        controller.subscribeToPlayerScoreUpdates(gameId, updatePlayerScoreList)
    }
    override fun show() {
        controller.checkIfLastRound(::checkIfLastRound)
        subscribeToPlayerScoreUpdates(gameId, ::updatePlayerScoreList)

    }
    override fun setupUI(){
        val screenWidth = Gdx.graphics.width.toFloat()
        val screenHeight = Gdx.graphics.height.toFloat()

        stage.actors {
            // Start of table
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                row().top().left().pad(100f, 100f, 100f, 0f)
                label("Results", "white_bigger")

                // Print player names on screen
                row().expand().fillX().pad(0f, 100f, 0f, 100f).top()
                /*add(tableN).top()*/
                table {
                    row().padBottom(50f).expandX()
                    label("1. Alf Inge Wang") {
                        it.left()
                        setFontScale(1.25f)
                    }
                    label("845"){
                        it.right()
                        setFontScale(1.25f)
                    }

                    row().padBottom(50f).expandX()
                    label("2. Barack Obama") {
                        it.left()
                        setFontScale(1.25f)
                    }
                    label("735"){
                        it.right()
                        setFontScale(1.25f)
                    }

                    row().padBottom(50f).expandX()
                    label("3. Tim Cook") {
                        it.left()
                        setFontScale(1.25f)
                        setColor(0.2f, 1f, 0.1f, 1f)
                    }
                    label("627"){
                        it.right()
                        setFontScale(1.25f)
                        setColor(0.2f, 1f, 0.1f, 1f)
                    }

                    row().padBottom(50f).expandX()
                    label("4. Mark Zuckerberg") {
                        it.left()
                        setFontScale(1.25f)
                    }
                    label("524"){
                        it.right()
                        setFontScale(1.25f)
                    }

                    row().padBottom(50f).expandX()
                    label("5. Elon Musk") {
                        it.left()
                        setFontScale(1.25f)
                    }
                    label("326"){
                        it.right()
                        setFontScale(1.25f)
                    }
                }

                //CHECK IF ITS THE LAST ROUND
                if(lastRound){
                    // Finish Game button
                    row().bottom()
                    textButton("Finish", "new_game") {
                    }.addListener(object : ClickListener() {
                        override fun clicked(event: InputEvent?, x: Float, y: Float) {
                            controller.handleChangeToMainView()
                        }
                    })

                    row()
                    textButton("Play Again", "selection") {
                    }.addListener(object : ClickListener() {
                        override fun clicked(event: InputEvent?, x: Float, y: Float) {
                            //HANDLE NEW GAME
                        }
                    })

                }
                else{
                        // Resume Game button
                        row().bottom()
                        textButton("Next Round", "new_game") {
                        }.addListener(object : ClickListener() {
                            override fun clicked(event: InputEvent?, x: Float, y: Float) {

                                //TODO HANDLE NEW ROUND
                            }
                        })
                }


                setFillParent(true)
                top()
                pack()
            }
        }
    }

    private fun checkIfLastRound(lastRound:Boolean){
            println(this.lastRound)
            this.lastRound = lastRound
            println(this.lastRound)
            setupUI()
    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }
}
