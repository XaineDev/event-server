package dev.xaine.items.impl

import dev.xaine.items.ItemType
import dev.xaine.items.attributes.Attribute
import dev.xaine.util.ComponentUtil
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.color.Color

enum class Rarity(private val colour: Color, private val upgradable: Boolean = true) {
    COMMON(Color(NamedTextColor.WHITE)),
    UNCOMMON(Color(NamedTextColor.GREEN)),
    RARE(Color(NamedTextColor.BLUE)),
    EPIC(Color(NamedTextColor.DARK_PURPLE)),
    LEGENDARY(Color(NamedTextColor.GOLD)),
    MYTHIC(Color(NamedTextColor.LIGHT_PURPLE), false), // dont allow them to upgrade past mythical
    SPECIAL(Color(NamedTextColor.RED), false),
    DIVINE(Color(NamedTextColor.AQUA), false);

    companion object {
        operator fun get(currentRarityIndex: Int): Rarity {
            return VALUES[currentRarityIndex]
        }

        val VALUES = entries.toList()
    }

    private fun getRarityString(): String {
        return this.name
    }

    fun canUpgrade(): Boolean {
        val isMax = ordinal == VALUES.size
        return !isMax && upgradable
    }

    fun getComponent(upgraded: Boolean, itemType: ItemType? = null): Component {
        if (itemType == null || itemType.toString().isEmpty()) {
            val withoutSuffix = ComponentUtil.text(getRarityString())
            return if (!upgraded) withoutSuffix.color { colour.asRGB() } else surround(withoutSuffix).color { colour.asRGB() }
        } else {
            val withSuffix = ComponentUtil.text("${getRarityString()} $itemType")
            return if (!upgraded) withSuffix.color { colour.asRGB() } else surround(withSuffix).color { colour.asRGB() }
        }
    }

    private fun surround(component: Component): Component {
        val spaceComponent = ComponentUtil.text(" ")
        val obfuscatedComponent = ComponentUtil.text("a").decorate(TextDecoration.BOLD).decorate(TextDecoration.OBFUSCATED)
        return obfuscatedComponent.append(spaceComponent).append(component.decoration(TextDecoration.OBFUSCATED, false)).append(spaceComponent).append(obfuscatedComponent)
    }
}