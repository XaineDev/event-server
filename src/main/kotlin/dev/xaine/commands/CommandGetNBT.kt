package dev.xaine.commands

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.condition.Conditions
import net.minestom.server.entity.Player

class CommandGetNBT : Command("nbt") {

    init {
        setCondition(Conditions::playerOnly)
        setDefaultExecutor { commandSender, commandContext ->
            run {
                val player = commandSender as Player
                val heldItem = player.itemInMainHand
                val heldItemNBT = heldItem.meta().toSNBT()
                player.sendMessage(heldItemNBT)
            }

        }
    }
}