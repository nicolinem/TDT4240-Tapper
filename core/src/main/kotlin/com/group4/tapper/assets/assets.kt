package com.group4.tapper.assets

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.BitmapFontLoader
import com.badlogic.gdx.assets.loaders.FileHandleResolver
import com.badlogic.gdx.assets.loaders.ShaderProgramLoader
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.I18NBundle
import com.badlogic.gdx.utils.Json
import ktx.assets.async.AssetStorage
import ktx.scene2d.Scene2DSkin
import ktx.style.skin
import java.io.File

enum class SoundAsset(
    fileName: String,
    directory: String = "sound",
    val descriptor: AssetDescriptor<Sound> = AssetDescriptor("$directory/$fileName", Sound::class.java)
) {
    BOOST_1("boost1.wav"),
    BOOST_2("boost2.wav"),
    EXPLOSION("explosion.wav"),
    LIFE("life.wav"),
    SHIELD("shield.wav"),
    DAMAGE("damage.wav"),
    BLOCK("block.wav"),
    SPAWN("spawn.wav")
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
    directory: String = "tapper_skin_6",
    val descriptor: AssetDescriptor<Skin> = AssetDescriptor("$directory/$fileName", Skin::class.java)
) {
    SKIN(true, "tapper_skin_7.json", "tapper_skin_6")
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
