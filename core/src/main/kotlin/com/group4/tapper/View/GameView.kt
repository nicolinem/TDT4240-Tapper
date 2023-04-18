package com.group4.tapper.View

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.group4.tapper.Tapper
import com.group4.tapper.View.Objects.Circle
import ktx.app.clearScreen
import kotlin.random.Random
import kotlin.math.sqrt
import kotlin.math.pow

class GameView(game: Tapper) : View(game){


    private val pointsFont : BitmapFont = BitmapFont()
    private var points: Int = 1000

    private val background: Texture = Texture(Gdx.files.internal("background_tapper.png"))
    private val shape:ShapeRenderer = ShapeRenderer()
    private var height:Int = 0
    private var width:Int = 0

    //Circle object with default values.
    private var circle:Circle = Circle(100f,750f,30f,2,false)

    //tmp wait for puzzle code
    private var count:Boolean = true
    private var circleList: MutableList<Int> = (1..6).toMutableList()
    private var puzzleList: MutableList<Int> = circleList.shuffled() as MutableList<Int>
    private val coordinates: MutableList<Pair<Float, Float>> = mutableListOf()

    override fun setupUI() {
        TODO("Not yet implemented")
    }


    override fun render(dt:Float) {
        clearScreen(red = 0.7f, green = 0.7f, blue = 0.7f)
        pointsFont.setColor(50f, 50f, 50f, 1f)
        pointsFont.getData().setScale(7f)
        shape.setColor(245/255f,209/255f,239/255f,1f)

        batch.begin()
        height = Gdx.graphics.getHeight()
        width = Gdx.graphics.getWidth()
        batch.draw(background,0f,0f,2000f,2000f)
        //Tegner sirkeler

        drawTopCircles(6)
        drawClickCircles(6)


        pointsFont.draw(batch,"Points: " + points.toString() ,width*0.05f , height*0.95f)

        batch.end()

        shape.begin(ShapeRenderer.ShapeType.Filled)
        //Tegner strekene
        shape.rectLine(0f,height*0.85f,width*1f,height*0.85f,10f)
        shape.rectLine(0f,height*0.75f,width*1f,height*0.75f,10f)

        shape.end()
        points-=1

    }

    override fun update(dt: Float) {
        TODO("Not yet implemented")
    }



    fun drawClickCircles(amount: Int) {
        val maxX: Float = width * 0.95.toFloat()
        val minX: Float = width * 0.05.toFloat()
        val maxY: Float = height * 0.6.toFloat()
        val minY: Float = height * 0.05.toFloat()

        val circleRadius = 70f

        if(coordinates.isNotEmpty()){
            var index = 0
            for (pair in coordinates){
                circle = Circle(pair.first,pair.second,circleRadius,puzzleList.get(index),true)
                index +=1
                circle.draw(batch)
            }
            return
        }

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
            val circle: Circle = Circle(randomX, randomY, circleRadius, i,true)

            circle.draw(batch)
        }
    }

    fun distance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return sqrt((x1 - x2).pow(2) + (y1 - y2).pow(2))
    }



    fun drawTopCircles(amount:Int){
        //populate a list of random numbers
        for (i in 1..amount){
            circle = Circle(-width*0.06f+width*0.16f*i,0.8f*height,70f,circleList.get(i-1),false)
            circle.draw(batch)
        }

    }


}
