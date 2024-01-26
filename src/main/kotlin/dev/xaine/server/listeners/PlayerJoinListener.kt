package dev.xaine.server.listeners

import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import net.minestom.server.event.player.PlayerSpawnEvent
import net.minestom.server.scoreboard.Sidebar


class PlayerJoinListener : DefaultListener {

    override fun init() {
        val eventHandler = MinecraftServer.getGlobalEventHandler()
        eventHandler.addListener(PlayerSpawnEvent::class.java) {
            if (it.isFirstSpawn) {
                addBossBar(it.player)

                Thread{
                    run {

                        val sidebar = Sidebar(Component.text("ꜱᴛᴀᴛꜱ", NamedTextColor.YELLOW, TextDecoration.BOLD))
                        sidebar.createLine(Sidebar.ScoreboardLine("ticks",
                            Component.text("ᴛɪᴄᴋꜱ: ", NamedTextColor.WHITE)
                                .append(Component.text(it.player.aliveTicks, NamedTextColor.DARK_AQUA)
                                ), 2))
                        sidebar.createLine(Sidebar.ScoreboardLine("blank2", Component.text(""), 1))
                        sidebar.createLine(Sidebar.ScoreboardLine("footer", Component.text("ᴍᴄ.xᴀɪɴᴇ.ᴅᴇᴠ", NamedTextColor.YELLOW, TextDecoration.BOLD), 0))

                        sidebar.addViewer(it.player)

                        while (it.player.isOnline) {
                            sidebar.removeLine("ticks")
                            sidebar.createLine(Sidebar.ScoreboardLine("ticks",
                                Component.text("ᴛɪᴄᴋꜱ: ", NamedTextColor.WHITE)
                                    .append(Component.text(it.player.aliveTicks, NamedTextColor.DARK_AQUA)
                                    ), 2))
                            Thread.sleep(1000)
                        }
                    }
                }.start()
            }
        }
    }

    private fun addBossBar(player: Player) {
        val bossBar = BossBar.bossBar(
            Component.text("ᴛᴇꜱᴛɴᴇᴛ.xᴀɪɴᴇ.ᴅᴇᴠ • ᴍɪɴᴇꜱᴛᴏᴍ ᴛᴇꜱᴛ ꜱᴇʀᴠᴇʀ", NamedTextColor.YELLOW, TextDecoration.BOLD),
            1f,
            BossBar.Color.WHITE,
            BossBar.Overlay.PROGRESS
        )
    }
}