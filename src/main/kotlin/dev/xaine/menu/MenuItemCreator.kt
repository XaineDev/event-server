package dev.xaine.menu

import net.kyori.adventure.text.Component
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

class MenuItemCreator {
    companion object {
        fun createItemStack(material: Material, title: Component): ItemStack.Builder {
            return ItemStack.builder(material).displayName(title)
        }
    }
}