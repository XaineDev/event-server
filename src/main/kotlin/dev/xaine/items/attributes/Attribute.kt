package dev.xaine.items.attributes

import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor

enum class Attribute(
    val attributeName: String,
    val attributeType: AttributeType,
    val attributeColor: TextColor,
    val baseValue: Float,
    val percentage: Boolean = false
) {
    HEALTH("Health", AttributeType.COMBAT, NamedTextColor.GREEN, 100f),
    DEFENSE("Defense", AttributeType.COMBAT, NamedTextColor.GREEN, 0f),
    DAMAGE("Damage", AttributeType.COMBAT, NamedTextColor.RED, 0f),
    STRENGTH("Strength", AttributeType.COMBAT, NamedTextColor.RED, 0f),
    INTELLIGENCE("Intelligence", AttributeType.COMBAT, NamedTextColor.GREEN, 0f),
    CRIT_CHANCE("Crit Chance", AttributeType.COMBAT, NamedTextColor.RED, 30f, true),
    CRIT_DAMAGE("Crit Damage", AttributeType.COMBAT, NamedTextColor.RED, 50f, true),
    ATTACK_SPEED("Bonus Attack Speed", AttributeType.COMBAT, NamedTextColor.RED, 0f, true),
    ABILITY_DAMAGE("Ability Damage", AttributeType.COMBAT, NamedTextColor.GREEN, 0f, true),
    TRUE_DEFENCE("True Defense", AttributeType.COMBAT, NamedTextColor.GREEN, 0f),
    FEROCITY("Ferocity", AttributeType.COMBAT, NamedTextColor.RED, 0f),
    HEALTH_REGEN("Health Regen", AttributeType.COMBAT, NamedTextColor.GREEN, 100f),
    VITALITY("Vitality", AttributeType.COMBAT, NamedTextColor.GREEN, 100f),
    MENDING("Mending", AttributeType.COMBAT, NamedTextColor.GREEN, 100f),
    SWING_RANGE("Swing Range", AttributeType.COMBAT, NamedTextColor.GREEN, 3f),
    SPEED("Speed", AttributeType.MISC, NamedTextColor.GREEN, 100f),
    MAGIC_FIND("Magic Find", AttributeType.MISC, NamedTextColor.GREEN, 1f),
    SEA_CREATURE_CHANCE("Sea Creature Chance", AttributeType.MISC, NamedTextColor.GREEN,20f);


    companion object {
        val VALUES = entries.toList()
    }

    override fun toString(): String {
        return attributeName
    }
}