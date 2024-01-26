package dev.xaine.items

import dev.xaine.items.attributes.Attribute
import dev.xaine.items.attributes.IAttributeValue
import dev.xaine.items.attributes.impl.IntAttribute
import dev.xaine.items.impl.Rarity
import dev.xaine.util.ComponentUtil
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.color.Color
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag
import java.util.*
import kotlin.collections.HashMap

abstract class Item(val itemID: String) {
    abstract val itemName: Component
    abstract val itemStackBuilder: ItemStack.Builder
    protected open val itemType: ItemType = ItemType.ITEM
    protected open var defaultLore: Array<Component> = arrayOf()
    private val defaultAttributes: EnumMap<Attribute, IAttributeValue<*>> = EnumMap(Attribute::class.java)
    private val specialAttributes: HashMap<String, IAttributeValue<*>> = HashMap()
    protected open val defaultRarity: Rarity = Rarity.COMMON

    private val loreRegex: Regex = Regex("(.{1,40}(?:(?<=\\s)|(?<=[.!?])))\\s*|\\S+|\\n")
    fun formatLore(lore: String, color: Color = Color(NamedTextColor.GRAY)): Array<Component> {
        val lines = loreRegex.findAll(lore).map { it.value }
        val textColor = TextColor { color.asRGB() }
        return Array(lines.count()){ ComponentUtil.text(lines.elementAt(it)).color(textColor) }
    }

    fun getAttributeLore(): Array<Component> {
        val components = MutableList<Component>(defaultAttributes.size+1) { Component.empty() }
        if (defaultAttributes.isEmpty()) return components.toTypedArray()
        var index = 0;
        for (entry in defaultAttributes) {
            val attrib = entry.key
            var attribModification = "+${entry.value.get().toString()}"
            if (attrib.percentage) {
                attribModification += "%"
            }

            components[index] = ComponentUtil.text("$attrib: ").color(NamedTextColor.GRAY)
                .append(ComponentUtil.text(attribModification).color(attrib.attributeColor))
            index++
        }
        return components.toTypedArray()
    }

    fun registerDefaultAttribute(key: Attribute, value: IAttributeValue<*>) {
        defaultAttributes[key] = value
    }
    fun registerAttribute(key: String, value: IAttributeValue<*>) {
        specialAttributes[key] = value
    }

    fun isUpgraded(): Boolean {
        return specialAttributes["rarity_upgrades"] != null
    }

    fun getCurrentRarity(): Rarity {
        val rarityUpgrades = specialAttributes["rarity_upgrades"] ?: return defaultRarity // if not upgraded return default
        var upgraded = (rarityUpgrades as IntAttribute).get() ?: return defaultRarity // error handling
        var currentRarityIndex = defaultRarity.ordinal // rarity index
        if (currentRarityIndex == Rarity.VALUES.size) return defaultRarity // no point in calculating new rarity if the current rarity is max
        while (upgraded > 0) {
            upgraded--
            val newRarity = Rarity[currentRarityIndex++]
            if (!newRarity.canUpgrade()) return newRarity // return current rarity if you cant upgrade it again
        }
        return Rarity[currentRarityIndex]
    }

    fun getItemStack(): ItemStack {
        var rarityUpgradedComponent = arrayOf(Component.empty())
        if (isUpgraded()) {
            rarityUpgradedComponent = arrayOf(Component.empty(), ComponentUtil.text("RARITY UPGRADED")
                .color(NamedTextColor.LIGHT_PURPLE)
                .decoration(TextDecoration.BOLD, true))
        }
        return itemStackBuilder.displayName(itemName).lore(*getAttributeLore(), *defaultLore, *rarityUpgradedComponent, getCurrentRarity().getComponent(isUpgraded(), itemType)).build()
    }
}