package dev.xaine.server.commands

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentString
import net.minestom.server.entity.Player
import net.minestom.server.network.packet.client.ClientPacketsHandler.Play

class CommandStartGame : Command("startgame") {

    init {

        val gameType = ArgumentString("game")

        addSyntax(
            { sender, context ->
                if (sender !is Player) return@addSyntax
                val chosenGame = context.get(gameType)
            }, gameType
        )
    }

}