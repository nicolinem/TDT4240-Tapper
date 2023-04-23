package com.group4.tapper.assets

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
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
/*    GAME_OVER("gameOver.mp3"),
    MENU("menu.mp3")*/
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


/*
enum class TextureAsset(
    fileName: String,
    directory: String = "graphics",
    val descriptor: AssetDescriptor<Texture> = AssetDescriptor("$directory/$fileName", Texture::class.java)
) {
    BACKGROUND("background.png")
}
*/

/*enum class ShaderProgramAsset(
    vertexFileName: String,
    fragmentFileName: String,
    directory: String = "shader",
    val descriptor: AssetDescriptor<ShaderProgram> = AssetDescriptor(
        "$directory/$vertexFileName/$fragmentFileName",
        ShaderProgram::class.java,
        ShaderProgramLoader.ShaderProgramParameter().apply {
            vertexFile = "$directory/$vertexFileName"
            fragmentFile = "$directory/$fragmentFileName"
        })
) {
    OUTLINE("default.vert", "outline.frag")
}*/

/*enum class BitmapFontAsset(
    fileName: String,
    directory: String = "ui",
    val descriptor: AssetDescriptor<BitmapFont> = AssetDescriptor(
        "$directory/$fileName",
        BitmapFont::class.java,
        BitmapFontLoader.BitmapFontParameter().apply {
            atlasName = TextureAtlasAsset.UI.descriptor.fileName
        })
) {
    FONT_LARGE_GRADIENT("font11_gradient.fnt"),
    FONT_DEFAULT("font8.fnt")
}*/

/*enum class I18NBundleAsset(
    fileName: String,
    directory: String = "i18n",
    val descriptor: AssetDescriptor<I18NBundle> = AssetDescriptor("$directory/$fileName", I18NBundle::class.java)
) {
    DEFAULT("i18n")
}*/
