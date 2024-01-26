package dev.xaine.server.commands

import dev.xaine.Main
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentString
import net.minestom.server.command.builder.condition.Conditions
import net.minestom.server.entity.Player

class CommandGiveItems : Command("giveitems") {

    init {
        setCondition(Conditions::playerOnly)
        setDefaultExecutor { sender, _ ->
            sender.sendMessage("Usage: /giveitems <item>")
        }

        val itemType = ArgumentString("item")

        addSyntax({ sender, ctx ->
            if (sender is Player) {
                sender.sendMessage("Giving Items")
                val item = ctx.get(itemType)
                Main.INSTANCE.getItemRepository().getFromID(item)?.let { sender.inventory.addItemStack(it.getItemStack()) } ?: run {
                    sender.sendMessage("Invalid Item.")
                }
            }
        }, itemType)
    }
}