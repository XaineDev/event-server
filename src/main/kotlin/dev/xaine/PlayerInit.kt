package dev.xaine

import net.kyori.adventure.resource.ResourcePackInfo
import net.kyori.adventure.resource.ResourcePackRequest
import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent
import net.minestom.server.event.player.PlayerDisconnectEvent
import net.minestom.server.event.player.PlayerSpawnEvent
import net.minestom.server.instance.AnvilLoader
import net.minestom.server.instance.Instance
import net.minestom.server.instance.InstanceContainer
import net.minestom.server.instance.LightingChunk
import net.minestom.server.instance.block.Block
import net.minestom.server.world.DimensionType
import java.net.URI
import java.util.*
import kotlin.collections.HashMap


class PlayerInit {
    companion object {

        private var resourcePackRequest : ResourcePackRequest? = null
        private val instanceMap : HashMap<UUID, Instance> = HashMap()
        init {
            ResourcePackInfo.resourcePackInfo()
                .uri(URI.create("https://xaine.dev/invisiblebossbar.zip"))
                .computeHashAndBuild()
                .thenApplyAsync { info ->
                    val resourcePackRequest = ResourcePackRequest.resourcePackRequest()
                        .packs(info)
                        .required(true)
                        .build()
                    this.resourcePackRequest = resourcePackRequest
                    println("loaded resource pack")
                }
        }
        fun init() {
            val spawn = Pos(0.0, 40.0, 0.0)

            val eventHandler = MinecraftServer.getGlobalEventHandler()
            eventHandler.addListener(AsyncPlayerConfigurationEvent::class.java) {
                val player: Player = it.player
                if (resourcePackRequest == null) {
                    player.kick("Resource pack not loaded.")
                    return@addListener
                }
                val playerInstanceContainer = MinecraftServer.getInstanceManager().createInstanceContainer()
                playerInstanceContainer.setGenerator { unit -> unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK) }
                playerInstanceContainer.enableAutoChunkLoad(true)
                playerInstanceContainer.setChunkSupplier { chunk, x, y -> LightingChunk(chunk, x, y) }
                playerInstanceContainer.chunkLoader = AnvilLoader("worlds/player/${player.uuid}")
                playerInstanceContainer.enableAutoChunkLoad(true)
                val instance: Instance = MinecraftServer.getInstanceManager().instances.last()
                instanceMap[player.uuid] = instance
                it.spawningInstance = instance
                player.respawnPoint = spawn
                player.sendResourcePacks(resourcePackRequest!!)
            }
            eventHandler.addListener(PlayerSpawnEvent::class.java) { event ->
                event.player.teleport(spawn)
            }
            eventHandler.addListener(PlayerDisconnectEvent::class.java) {
                val instance = instanceMap[it.player.uuid] ?: return@addListener
                instance.saveChunksToStorage().thenApplyAsync {
                    MinecraftServer.getInstanceManager().unregisterInstance(instance)
                    println("saving world.")
                }
            }
        }
    }
}