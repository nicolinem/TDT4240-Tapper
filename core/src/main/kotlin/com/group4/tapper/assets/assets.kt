package com.group4.tapper.assets

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import ktx.assets.async.AssetStorage
import ktx.scene2d.Scene2DSkin
import ktx.style.skin
import java.io.File

enum class SoundAsset(
    fileName: String,
    directory: String = "sounds",
    val descriptor: AssetDescriptor<Sound> = AssetDescriptor("$directory/$fileName", Sound::class.java)
) {
    CORRECT("incorrect.wav"),
    INCORRECT("correct.wav"),

}

enum class MusicAsset(
    fileName: String,
    directory: String = "music",
    val descriptor: AssetDescriptor<Music> = AssetDescriptor("$directory/$fileName", Music::class.java)
) {
    MENU("meny.mp3"),
    GAME("gameplay.mp3")
}

enum class TextureAtlasAsset(
    val isSkinAtlas: Boolean,
    fileName: String,
    directory: String = "tapper_skin",
    val descriptor: AssetDescriptor<Skin> = AssetDescriptor("$directory/$fileName", Skin::class.java)
) {
    SKIN(true, "tapper_skin_13.json", "tapper_skin")
}

fun createSkin(assets: AssetStorage) {
    val atlas = assets[TextureAtlasAsset.SKIN.descriptor]
    Scene2DSkin.defaultSkin = atlas
    }


enum class TextureAsset(
    fileName: String,
    directory: String = "images",
    val descriptor: AssetDescriptor<Texture> = AssetDescriptor("$directory/$fileName", Texture::class.java)
) {
    CREATE_GAME("create_game.png"),
    GAME_TUTORIAL("game_tutorial.png"),
    JOIN_GAME("join_game.png"),
    LINE("line.png"),
    NUMBER_OF_ROUNDS("number_of_rounds.png"),
    PIN_INPUT("pin-input.png"),
    RETURN("return.png"),
    RETURN_WHITE("return_white.png"),
    TAPPER_LOGO("tapper_logo.png")
}
