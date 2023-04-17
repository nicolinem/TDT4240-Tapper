package com.group4.tapper.View

import ktx.app.clearScreen
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.assets.disposeSafely
import ktx.scene2d.*


class NewGameView: View() {
    lateinit var skin: Skin
    private val scale = 6f
    private var stage = Stage(FitViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()))
    private val createGameButton = ImageButton(TextureRegionDrawable(
        TextureRegion(Texture(Gdx.files.internal("create_game_button.png")))))

    init {
        skin = Skin(Gdx.files.internal("skin.json"))
        skin.getAll(Texture::class.java).values().forEach { texture ->
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        }
        Scene2DSkin.defaultSkin = skin
        Gdx.input.inputProcessor = stage
        setupUI()
    }


    private fun setupUI() {
        val screenHeight = Gdx.graphics.height.toFloat()
        val screenWidth = Gdx.graphics.width.toFloat()
        val buttonpadding = 50f
        val buttonWidth = screenWidth/3 - buttonpadding

        /*stage.actors {
            table{
                setFillParent(true)
                defaults().fillX().expandX()
                defaults().pad(50f)
                row().expand().bottom().left()
                button("easy", skin) {

                }
            }
        }*/
        stage.actors {
            table {
                defaults().fillX().expandX()
                defaults().pad(50f)

                row().expand().bottom().left()
                label("Pin: "){
                    style.font.data.setScale(scale/2)
                    setFontScale(scale)
                    setColor(255f,255f,255f,1f)
                }

                row().size(screenWidth/2, 150f).left()
                textField("Nickname"){
                }

                row()
                label("Rounds").setFontScale(scale/1.5f)

                row().left()
                buttonGroup(0,1){
                    it.fillX().expandX()
                    textButton("2") {
                        pad(25f, 50f, 25f, 50f)
                    }
                    textButton("3") {
                        pad(25f, 50f, 25f, 50f)
                        it.padLeft(50f)
                        it.padRight(50f)
                    }
                    textButton("4") {
                        pad(25f, 50f, 25f, 50f)
                    }
                }

                row()
                label("Difficulty").setFontScale(scale/1.5f)

                row().left()
                buttonGroup(1,1){
                    textButton("easy") {
                        pad(25f, 50f, 25f, 50f)
                    }
                    textButton("medium") {
                        pad(25f, 50f, 25f, 50f)
                        it.padLeft(50f)
                        it.padRight(50f)
                    }
                    textButton("hard") {
                        pad(25f, 50f, 25f, 50f)
                    }
                }

                row()
                textButton("Create Game") {
                    it.padTop(400f)
                    it.height(150f)
                }
                setFillParent(true)
                top()
                pack()
            }
        }
    }

    override fun render(delta: Float) {
        clearScreen(0.42f, 0.12f, 0.39f, 1f)
        stage.act()
        stage.draw()
    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        stage.disposeSafely()
    }
}
