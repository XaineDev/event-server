package dev.xaine.menu.impl

import dev.xaine.menu.CustomMenu
import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory

abstract class DynamicMenu : CustomMenu {
    /**
     * called when the current gui needs to update something
     */
    abstract fun updateMenu()


}