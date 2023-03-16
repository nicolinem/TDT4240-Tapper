package com.group4.tapper.View

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.Stack


object ViewManager {
    private val states: Stack<View> = Stack()

    fun setView(view: View){

    }

    fun push(state: View?) {
        states.push(state)
    }

    fun pop() {
        states.pop()
    }

    fun set(state: View?) {
        states.pop()
        states.push(state)
    }

    fun update(dt: Float) {
        states.peek().update(dt)
    }

    fun render(sb: SpriteBatch, font: BitmapFont) {

    }
}
