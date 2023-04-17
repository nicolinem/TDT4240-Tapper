@file:JvmName("Lwjgl3Launcher")

package com.group4.tapper.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.group4.tapper.Tapper

/** Launches the desktop (LWJGL3) application. */
fun main() {
    Lwjgl3Application(Tapper(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("Tapper")
        setWindowedMode(1080/2, 1920/2)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
