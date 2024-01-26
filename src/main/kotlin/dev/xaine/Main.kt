package dev.xaine

import dev.xaine.items.CustomItemHandler
import dev.xaine.items.impl.ItemDecrementSpeed
import dev.xaine.items.impl.ItemIncrementSpeed
import dev.xaine.items.impl.ItemSkyblockMenu
import dev.xaine.minigame.GameContainer
import dev.xaine.server.commands.*
import dev.xaine.server.listeners.ChunkUnloadListener
import dev.xaine.server.listeners.PlayerJoinListener
import mu.KotlinLogging
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.MinecraftServer
import net.minestom.server.command.CommandSender
import net.minestom.server.event.server.ServerListPingEvent
import net.minestom.server.extras.MojangAuth
import net.minestom.server.utils.callback.CommandCallback


enum class Main {
    INSTANCE;

    val logger = KotlinLogging.logger {}
    private var itemHandler = CustomItemHandler()
    private var gameContainer = GameContainer()

    fun main() {
        MinecraftServer.setCompressionThreshold(0)
        val minecraftServer = MinecraftServer.init()

        setupServerlist()

        registerCommands()

        registerItems()
        registerListeners()


        Runtime.getRuntime().addShutdownHook(Thread(MinecraftServer::stopCleanly));
        MojangAuth.init()
        PlayerInit.init()

        minecraftServer.start("0.0.0.0", 25565)
    }

    fun getCustomItemHandler(): CustomItemHandler {
        return itemHandler
    }

    private fun registerCommands() {
        val commandManager = MinecraftServer.getCommandManager()
        commandManager.register(CommandGiveItems())
        commandManager.register(CommandGetNBT())
        commandManager.register(CommandGamemode())
        commandManager.register(CommandTest())
        commandManager.unknownCommandCallback =
            CommandCallback { sender: CommandSender, _: String? ->
                sender.sendMessage(
                    Component.text("Invalid Command.", NamedTextColor.RED, TextDecoration.BOLD)
                )
            }
    }
    private fun registerListeners() {
        ChunkUnloadListener().init()
        PlayerJoinListener().init()
    }
    private fun registerItems() {
        itemHandler.put(ItemSkyblockMenu())
        itemHandler.put(ItemIncrementSpeed())
        itemHandler.put(ItemDecrementSpeed())
        itemHandler.init()
    }

    private fun setupServerlist() {
        MinecraftServer.getGlobalEventHandler().addListener(
            ServerListPingEvent::class.java
        ) { event: ServerListPingEvent ->
            val responseData = event.responseData
            responseData.description = Component.text(
                "testnet.xaine.dev",
                TextColor.color(0x66b3ff)
            )
        }
    }
}

fun main() {
    Main.INSTANCE.main()
}