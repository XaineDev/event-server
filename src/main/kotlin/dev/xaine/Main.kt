package dev.xaine

import dev.xaine.commands.CommandGamemode
import dev.xaine.commands.CommandGetNBT
import dev.xaine.commands.CommandGiveItems
import dev.xaine.commands.CommandTestGUI
import dev.xaine.items.CustomItemHandler
import dev.xaine.items.impl.ItemDecrementSpeed
import dev.xaine.items.impl.ItemIncrementSpeed
import dev.xaine.listener.ChunkUnloadListener
import dev.xaine.listener.PlayerJoinListener
import mu.KotlinLogging
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.MinecraftServer
import net.minestom.server.command.CommandSender
import net.minestom.server.event.server.ServerListPingEvent
import net.minestom.server.extras.MojangAuth
import net.minestom.server.extras.lan.OpenToLAN
import net.minestom.server.extras.lan.OpenToLANConfig
import net.minestom.server.instance.LightingChunk
import net.minestom.server.instance.block.Block
import net.minestom.server.utils.callback.CommandCallback
import net.minestom.server.utils.identity.NamedAndIdentified
import net.minestom.server.utils.time.TimeUnit
import java.time.Duration


enum class Main {
    INSTANCE;

    val logger = KotlinLogging.logger {}
    private var itemHandler = CustomItemHandler()

    fun main() {
        MinecraftServer.setCompressionThreshold(0)
        val minecraftServer = MinecraftServer.init()
        MinecraftServer.getGlobalEventHandler().addListener(
            ServerListPingEvent::class.java
        ) { event: ServerListPingEvent ->
            val responseData = event.responseData

            responseData.description = Component.text(
                "testnet.xaine.dev",
                TextColor.color(0x66b3ff)
            )
        }
        val commandManager = MinecraftServer.getCommandManager()
        commandManager.register(CommandGiveItems())
        commandManager.register(CommandGetNBT())
        commandManager.register(CommandGamemode())
        commandManager.register(CommandTestGUI())
        commandManager.unknownCommandCallback =
            CommandCallback { sender: CommandSender, _: String? ->
                sender.sendMessage(
                    Component.text("Invalid Command.", NamedTextColor.RED, TextDecoration.BOLD)
                )
            }

        MojangAuth.init()
        itemHandler.put(ItemIncrementSpeed())
        itemHandler.put(ItemDecrementSpeed())
        itemHandler.init()
        ChunkUnloadListener().init()
        PlayerJoinListener().init()
        Runtime.getRuntime().addShutdownHook(Thread(MinecraftServer::stopCleanly));

        PlayerInit.init()

        minecraftServer.start("0.0.0.0", 25565)
    }

    fun getCustomItemHandler(): CustomItemHandler {
        return itemHandler
    }
}

fun main() {
    Main.INSTANCE.main()
}