package dev.xaine.menu

import dev.xaine.util.ComponentUtil
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.item.Material

object MenuItems {
    val GRAY_PANE = MenuItemCreator.createItemStack(Material.GRAY_STAINED_GLASS_PANE, ComponentUtil.text("")).build()
    var CLOSE_MENU = MenuItemCreator.createItemStack(Material.BARRIER, ComponentUtil.text("Close").color(NamedTextColor.RED)).build()
}