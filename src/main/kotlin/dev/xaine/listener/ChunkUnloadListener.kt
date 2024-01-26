package dev.xaine.listener

import net.minestom.server.MinecraftServer
import net.minestom.server.event.player.PlayerChunkUnloadEvent

class ChunkUnloadListener : DefaultListener {

    override fun init() {
        val eventHandler = MinecraftServer.getGlobalEventHandler()
        eventHandler.addListener(PlayerChunkUnloadEvent::class.java) {
            val instance = it.player.instance ?: return@addListener
            val chunk = instance.getChunk(it.chunkX, it.chunkZ) ?: return@addListener
            if (chunk.isLoaded && chunk.viewers.isEmpty()) {
                //instance.saveChunkToStorage(chunk)
                //instance.unloadChunk(chunk)
                //println("unloading chunk {x = ${chunk.chunkX}, z = ${chunk.chunkZ}}")
            }
        }
    }

}