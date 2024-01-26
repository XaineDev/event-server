package dev.xaine.commands

import dev.xaine.Main
import dev.xaine.items.impl.ItemIncrementSpeed
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.condition.Conditions
import net.minestom.server.command.builder.condition.Conditions.playerOnly
import net.minestom.server.entity.Player

class CommandGiveItems() : Command("giveitems") {

    init {
        setCondition(Conditions::playerOnly);
        setDefaultExecutor { sender, _ ->
            sender.sendMessage("Usage: /giveitems <item>")
        }

        addSyntax({ sender, _ ->
            if (sender is Player) {
                sender.sendMessage("Giving Items")
                Main.INSTANCE.getCustomItemHandler().get("INCREMENT_SPEED")?.let { sender.inventory.addItemStack(it.getItemStack()) } ?: run {
                    sender.sendMessage("Invalid Item.")
                }
                Main.INSTANCE.getCustomItemHandler().get("DECREMENT_SPEED")?.let { sender.inventory.addItemStack(it.getItemStack()) } ?: run {
                    sender.sendMessage("Invalid Item.")
                }

            }
        })
    }
}