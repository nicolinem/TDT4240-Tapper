package com.group4.tapper.View.Objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import jdk.jfr.Event
import java.util.EventListener


class Circle(x:Float, y: Float, size:Float, number: Int, clickable:Boolean) {


    private val x:Float = x
    private val y:Float = y
    private val size:Float = size
    private val number:Int = number
    private val font :BitmapFont = BitmapFont()
    private val texture:Texture = Texture("Circle.png")

    fun draw(batch: SpriteBatch){

        val image:Image = Image(texture)
        image.addListener(object :InputListener(){
            fun onCLick(event:InputEvent,x:Float,y:Float,pointer:Int,buttin:Int):Boolean{
                //Handle click event.
                println("click")
                return true
            }
        })

        //Draw circle
        batch.draw(texture,x-size,y-size,size*2,size*2)

        //Draw font
        font.setColor(Color.BLACK)
        font.getData().setScale(6f)
        font.draw(batch, number.toString(), x - font.xHeight / 2f, y + font.capHeight / 2f)
    }




}
