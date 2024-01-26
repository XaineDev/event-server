package dev.xaine.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.item.*
import net.minestom.server.tag.Tag
import java.util.regex.Pattern


class ItemCreator {
    companion object {

        private val LORE_REGEX = Regex("(.{1,40}(?:(?<=\\s)|(?<=[,-.!?])))\\s*|\\n")

        fun createItemStack(material: Material?, name: Component?, itemID: String? = null): ItemStack.Builder {
            return ItemStack.builder(material!!)
                .meta { meta: ItemMeta.Builder ->
                    meta.hideFlag(ItemHideFlag.HIDE_UNBREAKABLE)
                    meta.hideFlag(ItemHideFlag.HIDE_ATTRIBUTES)
                    meta.hideFlag(ItemHideFlag.HIDE_ENCHANTS)
                    meta.hideFlag(ItemHideFlag.HIDE_POTION_EFFECTS)
                    if (itemID != null) {
                        meta.setTag(Tag.String("item"), itemID)
                    }
                }.displayName(name)
        }

        fun loreToComponents(lore: String, color: NamedTextColor = NamedTextColor.GRAY): Array<Component> {
            val loreLines = LORE_REGEX.findAll(lore).map { it.value }.toList()
            val lineCount = loreLines.size
            val loreComponents = Array<Component>(loreLines.size) { Component.empty() }
            for (index in 0..<lineCount) {
                val component = Component.text(loreLines[index]).color(color).decoration(TextDecoration.ITALIC, false)
                loreComponents[index] = component
            }
            return loreComponents
        }
    }
}