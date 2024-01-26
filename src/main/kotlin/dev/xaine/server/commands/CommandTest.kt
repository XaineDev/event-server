package dev.xaine.server.commands

import dev.xaine.menu.impl.menu.SkyblockMenu
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentString
import net.minestom.server.entity.Player

class CommandTest : Command("devtest") {
    init {

        val commandData = ArgumentString("data")

        setDefaultExecutor { sender, _ ->
            sender.sendMessage(Component.text("this command does nothing!").color(NamedTextColor.RED))
        }

        addSyntax(
            { sender, context ->
                if (sender !is Player) {
                    sender.sendMessage(Component.text("player only!"))
                }
                val data = context.get(commandData)

            }, commandData
        )
    }
}