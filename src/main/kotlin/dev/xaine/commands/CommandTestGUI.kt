package dev.xaine.commands

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentEnum
import net.minestom.server.command.builder.arguments.ArgumentString
import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType

class CommandTestGUI : Command("testgui") {
    init {
        addSyntax({sender, context ->
            if (sender !is Player) {
                return@addSyntax
            }
            var prompt = context.get("prompt") as String
            val invType = context.get("inventory") as InventoryType
            prompt = prompt.replace("%nl%", "\n")
            val inventory = Inventory(invType, prompt)
            sender.openInventory(inventory)
        }, ArgumentString("prompt"), ArgumentEnum("inventory", InventoryType::class.java))
    }

}