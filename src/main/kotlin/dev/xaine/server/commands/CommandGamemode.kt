package dev.xaine.server.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentEnum
import net.minestom.server.command.builder.condition.Conditions
import net.minestom.server.entity.GameMode
import net.minestom.server.entity.Player

class CommandGamemode : Command("gamemode") {
    init {
        setCondition(Conditions::playerOnly)

        val gamemodeArg = ArgumentEnum("gamemode", GameMode::class.java)

        addSyntax({ sender, context ->
            if (sender is Player) {
                val gameMode : GameMode = context.get(gamemodeArg)
                sender.setGameMode(gameMode)
                sender.sendMessage(Component.text("gamemode set to $gameMode", NamedTextColor.GREEN))

            }
        }, gamemodeArg)

    }
}