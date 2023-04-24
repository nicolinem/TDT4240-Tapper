package com.group4.tapper.assets

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.utils.Pool
import ktx.assets.async.AssetStorage

import java.util.*
import kotlin.math.max

private const val MAX_SOUND_INSTANCES = 16

interface AudioService {
    var enabled: Boolean
    var soundsEnabled: Boolean
    var musicEnabled: Boolean
    fun play(soundAsset: SoundAsset, volume: Float = 1f) = Unit
    fun play(musicAsset: MusicAsset, volume: Float = 1f, loop: Boolean = true) = Unit
    fun pause() = Unit
    fun resume() = Unit
    fun stop(clearSounds: Boolean = true) = Unit
    fun update() = Unit
}

private class SoundRequest : Pool.Poolable {
    lateinit var soundAsset: SoundAsset
    var volume = 1f

    override fun reset() {
        volume = 1f
    }
}

private class SoundRequestPool : Pool<SoundRequest>() {
    override fun newObject() = SoundRequest()
}

class DefaultAudioService(private val assets: AssetStorage) : AudioService {
    override var  musicEnabled = true
        set(value) {
            when (value) {
                true -> currentMusic?.play()
                false -> currentMusic?.pause()
            }
            field = value
        }
    override var soundsEnabled = true


    override var enabled = true
        set(value) {
            when (value) {
                true -> currentMusic?.play()
                false -> currentMusic?.pause()
            }
            field = value
        }
    private val soundCache = EnumMap<SoundAsset, Sound>(SoundAsset::class.java)
    private val soundRequestPool = SoundRequestPool()
    private val soundRequests = EnumMap<SoundAsset, SoundRequest>(SoundAsset::class.java)
    private var currentMusic: Music? = null

    override fun play(soundAsset: SoundAsset, volume: Float) {
        if (!soundsEnabled) return

        when {
            soundAsset in soundRequests -> {
                // same request multiple times in one frame -> set volume to maximum of both requests
                System.out.println("Duplicated sound request for sound $soundAsset" )
                soundRequests[soundAsset]?.let { request ->
                    request.volume = max(request.volume, volume)
                }
            }
            soundRequests.size >= MAX_SOUND_INSTANCES -> {
                // maximum simultaneous sound instances reached -> do nothing
                System.out.println("Maximum sound instances reached" )
            }
            else -> {
                // new request
                if (soundAsset.descriptor !in assets) {
                    // sound not loaded -> error
                    System.out.println("Sound $soundAsset is not loaded" )
                    return
                } else if (soundAsset !in soundCache) {
                    // cache sound for faster access in the future
                    System.out.println( "Adding sound $soundAsset to sound cache")
                    soundCache[soundAsset] = assets[soundAsset.descriptor]
                }

                // get request instance from pool and add it to the queue
                System.out.println("New sound request for sound $soundAsset. Free request objects: ${soundRequestPool.free}")
                soundRequests[soundAsset] = soundRequestPool.obtain().apply {
                    this.soundAsset = soundAsset
                    this.volume = volume
                }
            }
        }
    }

    override fun play(musicAsset: MusicAsset, volume: Float, loop: Boolean) {
        if (currentMusic != null) {
            currentMusic?.stop()
        }

        if (musicAsset.descriptor !in assets) {
            System.out.println( "Music $musicAsset is not loaded" )
            return
        }

        currentMusic = assets[musicAsset.descriptor]
        if (!musicEnabled) return

        currentMusic?.apply {
            this.volume = volume
            this.isLooping = loop
            play()
        }
    }

    override fun update() {
        if (!soundRequests.isEmpty()) {
            // there are sounds to be played
            System.out.println("Playing ${soundRequests.size} sound(s)")
            soundRequests.values.forEach { request ->
                soundCache[request.soundAsset]?.play(request.volume)
                soundRequestPool.free(request)
            }
            soundRequests.clear()
        }
    }

    override fun pause() {
        if (musicEnabled) {
            currentMusic?.pause()
        }
    }

    override fun resume() {
        if (musicEnabled) {
            currentMusic?.play()
        }
    }

    override fun stop(clearSounds: Boolean) {
        if (musicEnabled) {
            currentMusic?.stop()
        }
        if (soundsEnabled && clearSounds) {
            soundRequests.clear()
        }
    }
}
