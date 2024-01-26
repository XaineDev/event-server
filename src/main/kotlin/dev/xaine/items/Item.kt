package dev.xaine.items

import dev.xaine.items.attributes.Attribute
import dev.xaine.items.attributes.IAttributeValue
import dev.xaine.items.attributes.impl.IntAttribute
import dev.xaine.items.impl.Rarity
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.color.Color
import net.minestom.server.item.ItemStack
import net.minestom.server.tag.Tag
import java.util.*
import kotlin.collections.HashMap

interface Item {

    val itemID: String
    val itemName: Component
    val itemStackBuilder: ItemStack.Builder
    val itemType: ItemType
    var defaultLore: Array<Component>
    val defaultAttributes: EnumMap<Attribute, Float>
    val specialAttributes: HashMap<String, IAttributeValue<*>>
    val defaultRarity: Rarity

    fun formatLore(lore: String, color: Color = Color(NamedTextColor.GRAY)): Array<Component> {
        return Array(0){Component.empty()}
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
            rarityUpgradedComponent = arrayOf(Component.empty(), Component.text("RARITY UPGRADED")
                .color(NamedTextColor.LIGHT_PURPLE)
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false))
        }
        return itemStackBuilder.displayName(itemName).lore(*defaultLore, *rarityUpgradedComponent, getCurrentRarity().getComponent(isUpgraded(), itemType)).build()
    }
}