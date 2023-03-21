package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.group4.tapper.View.Objects.Circle
import kotlin.random.Random

class GameView : View(){

    private val test:String = "test"
    private val font : BitmapFont = BitmapFont()

    private val background: Texture = Texture(Gdx.files.internal("background_tapper.png"))
    private val shape:ShapeRenderer = ShapeRenderer()
    private var height:Float = 0.0f
    private var width:Float = 0.0f
    private var circle:Circle = Circle(100f,750f,30f,2)
    private var count:Boolean = true
    private var circleList: MutableList<Int> = arrayListOf()


    override fun render(dt:Float) {
        font.setColor(50f, 50f, 50f, 1f)
        shape.setColor(245/255f,209/255f,239/255f,1f)

        batch.begin()
        batch.draw(background,0f,0f,2000f,2000f)
        //Tenger sirkel
        //circle.draw(batch)

        drawCircles(6)


        font.draw(batch, "Test", 500*dt, 50f)

        batch.end()

        shape.begin(ShapeRenderer.ShapeType.Filled)
        //Tegner strekene
        shape.rectLine(-100f,800f,650f,801f,5f)
        shape.rectLine(-100f,700f,650f,701f,5f)

        shape.end()


    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }

    override fun render() {

    }

    fun drawCircles(amount:Int){
        //populate a list of random numbers
        var numberList = (1..amount).toMutableList()

        //draw the circles
        //boolean to not make them random every tick, but stay the same after the first iteration.
        if(count){
            for (i in 1..amount){
                var randomInteger = numberList.random()
                numberList.remove(randomInteger)
                circleList.add(randomInteger)
                circle = Circle(0f+75f*i,750f,30f,randomInteger)
                circle.draw(batch)
            }
            count=false
            println(circleList)
        }
        else{
            for (i in 1..amount){
                circle = Circle(0f+75f*i,750f,30f,circleList.get(i-1))
                circle.draw(batch)
            }
        }


    }


}
