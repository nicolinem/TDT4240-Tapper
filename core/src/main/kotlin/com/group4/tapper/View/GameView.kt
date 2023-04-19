package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.viewport.FitViewport
import com.group4.tapper.Tapper
import com.group4.tapper.View.Objects.Circle
import ktx.app.clearScreen
import kotlin.random.Random
import kotlin.math.sqrt
import kotlin.math.pow

class GameView(game: Tapper) : View(){


    private val pointsFont : BitmapFont
    private val circleFont : BitmapFont = BitmapFont()
    private var points: Int = 1000

    private val background: Texture = Texture(Gdx.files.internal("background_tapper.png"))
    private val shape:ShapeRenderer
    private var height:Int = 0
    private var width:Int = 0

    //Circle object with default values.
    private var circle:Circle = Circle(100f,750f,30f,2,false)

    //tmp wait for puzzle code
    private val circleRadius:Float
    private var circleList: MutableList<Int> = (1..6).toMutableList()
    private var puzzleList: MutableList<Int> = circleList.shuffled() as MutableList<Int>
    private var puzzleListcopy: MutableList<Int> = puzzleList.toMutableList()
    private val coordinates: MutableList<Pair<Float, Float>> = mutableListOf()


    private val texture:Texture = Texture("Circle.png")
    private val textureRegion: TextureRegion = TextureRegion(texture)
    private val textureRegionDrawable: TextureRegionDrawable = TextureRegionDrawable(textureRegion)
    private val textButtonStyle:TextButtonStyle
    private val textButton1:TextButton
    private val textButton2:TextButton
    private val textButton3:TextButton
    private val textButton4:TextButton
    private val textButton5:TextButton
    private val textButton6:TextButton



    init {

        // Initialize pointsFont
        pointsFont = BitmapFont() // Assuming pointsFont is a BitmapFont object
        pointsFont.color = Color(255f / 255f, 255f / 255f, 255f / 255f, 1f)
        pointsFont.data.setScale(7f)

        circleRadius = 70f

        // Initialize shape
        shape = ShapeRenderer() // Assuming shape is a ShapeRenderer object
        shape.color = Color(245f / 255f, 209f / 255f, 239f / 255f, 1f)

        // Initialize textButtonStyle
        textButtonStyle = TextButtonStyle()
        textButtonStyle.up = TextureRegionDrawable(textureRegionDrawable)
        textButtonStyle.down = TextureRegionDrawable(textureRegionDrawable)
        textButtonStyle.checked = TextureRegionDrawable(textureRegionDrawable)
        textButtonStyle.font = circleFont // Assuming circleFont is a BitmapFont object
        textButtonStyle.fontColor = Color.BLACK

        // Initialize skin
        skin = Skin()
        skin.add("circle", texture) // Assuming texture is a Texture object
        skin.add("default", textButtonStyle, TextButtonStyle::class.java)

        // Initialize textButton
        textButton1 = TextButton("TEMP", skin)
        textButton2 = TextButton("TEMP", skin)
        textButton3 = TextButton("TEMP", skin)
        textButton4 = TextButton("TEMP", skin)
        textButton5 = TextButton("TEMP", skin)
        textButton6 = TextButton("TEMP", skin)
        textButton1.label.setFontScale(5f)
        textButton2.label.setFontScale(5f)
        textButton3.label.setFontScale(5f)
        textButton4.label.setFontScale(5f)
        textButton5.label.setFontScale(5f)
        textButton6.label.setFontScale(5f)

        textButton1.setSize(circleRadius*2,circleRadius*2)
        textButton2.setSize(circleRadius*2,circleRadius*2)
        textButton3.setSize(circleRadius*2,circleRadius*2)
        textButton4.setSize(circleRadius*2,circleRadius*2)
        textButton5.setSize(circleRadius*2,circleRadius*2)
        textButton6.setSize(circleRadius*2,circleRadius*2)

        //Create coordinates for buttons
        height = 1920
        width = 1080
        createCoordinates()

        //Add listeners to buttons. Make them clickable.
        textButton1.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {

                if(textButton1.getText().toString().equals(puzzleList[0].toString())){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(textButton1,true)

                }
                else{
                    triggerError()
                }
            }
        })

        textButton2.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(textButton2.getText().toString().equals(puzzleList[0].toString())){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(textButton2,true)

                }
                else{
                    triggerError()
                }
            }
        })

        textButton3.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(textButton3.getText().toString().equals(puzzleList[0].toString())){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(textButton3,true)

                }
                else{
                    triggerError()
                }
            }
        })

        textButton4.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(textButton4.getText().toString().equals(puzzleList[0].toString())){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(textButton4,true)

                }
                else{
                    triggerError()
                }
            }
        })

        textButton5.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(textButton5.getText().toString().equals(puzzleList[0].toString())){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(textButton5,true)

                }
                else{
                    triggerError()
                }
            }
        })

        textButton6.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if(textButton6.getText().toString().equals(puzzleList[0].toString())){
                    puzzleList.removeAt(0)
                    stage.actors.removeValue(textButton6,true)

                }
                else{
                    triggerError()
                }
            }
        })
    }

    override fun setupUI() {
        TODO("Not yet implemented")
    }

    override fun show() {
        Gdx.input.inputProcessor = stage
        stage.addActor(textButton1)
        stage.addActor(textButton2)
        stage.addActor(textButton3)
        stage.addActor(textButton4)
        stage.addActor(textButton5)
        stage.addActor(textButton6)


    }

    override fun render(dt:Float) {
        checkVictory()
        clearScreen(red = 42f/255, green = 12f/255, blue = 39f/255)


        batch.begin()

        //Tegner sirkeler

        drawTopCircles(6)
        drawClickCircles(6)
        stage.draw()
        stage.act()


        pointsFont.draw(batch,"Points: " + points.toString() ,width*0.05f , height*0.95f)

        batch.end()

        shape.begin(ShapeRenderer.ShapeType.Filled)
        //Tegner strekene
        shape.rectLine(0f,height*0.85f,width*1f,height*0.85f,10f)
        shape.rectLine(0f,height*0.75f,width*1f,height*0.75f,10f)

        shape.end()


    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }



    fun drawClickCircles(amount: Int) {


        if(coordinates.isNotEmpty()){

            textButton1.setPosition(coordinates.get(5).first,coordinates.get(5).second)
            textButton1.setText(puzzleListcopy[5].toString())

            textButton2.setPosition(coordinates.get(4).first, coordinates.get(4).second)
            textButton2.setText(puzzleListcopy[4].toString())

            textButton3.setPosition(coordinates.get(3).first, coordinates.get(3).second)
            textButton3.setText(puzzleListcopy[3].toString())

            textButton4.setPosition(coordinates.get(2).first, coordinates.get(2).second)
            textButton4.setText(puzzleListcopy[2].toString())

            textButton5.setPosition(coordinates.get(1).first, coordinates.get(1).second)
            textButton5.setText(puzzleListcopy[1].toString())

            textButton6.setPosition(coordinates.get(0).first, coordinates.get(0).second)
            textButton6.setText(puzzleListcopy[0].toString())


        }


    }
    fun drawTopCircles(amount:Int){

        for (i in puzzleListcopy){
            circle = Circle(-width*0.06f+width*0.16f*i,0.8f*height,70f,puzzleListcopy.get(i-1),false)
            circle.draw(batch)
        }

    }

    fun createCoordinates(){
        println(width.toString())
        val maxX: Float = width * 0.85.toFloat()
        val minX: Float = width * 0.05.toFloat()
        val maxY: Float = height * 0.6.toFloat()
        val minY: Float = height * 0.05.toFloat()
        for (i in puzzleList) {
            var randomX: Float
            var randomY: Float
            var overlapping: Boolean

            do {
                overlapping = false
                randomX = minX + (maxX - minX) * Random.nextFloat()
                randomY = minY + (maxY - minY) * Random.nextFloat()

                // Check if the generated coordinates overlap with any already drawn circles
                for (coordinate in coordinates) {
                    if (distance(randomX, randomY, coordinate.first, coordinate.second) <= 2 * circleRadius) {
                        overlapping = true
                        break
                    }
                }
            } while (overlapping)

            val pair: Pair<Float, Float> = Pair(randomX, randomY)
            coordinates.add(pair)
        }
    }

    fun distance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return sqrt((x1 - x2).pow(2) + (y1 - y2).pow(2))
    }

    fun triggerError(){
        Gdx.input.vibrate(500)
    }

    fun checkVictory(){
        if(puzzleList.isNotEmpty()){
            points -=1
        }
    }
}
