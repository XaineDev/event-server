package dev.xaine.items.attributes

enum class Attribute(val attributeType: AttributeType, val baseValue: Float) {
    HEALTH(AttributeType.COMBAT, 100f),
    DEFENSE(AttributeType.COMBAT, 0f),
    STRENGTH(AttributeType.COMBAT, 0f),
    INTELLIGENCE(AttributeType.COMBAT, 0f),
    CRIT_CHANCE(AttributeType.COMBAT, 30f),
    CRIT_DAMAGE(AttributeType.COMBAT, 50f),
    ATTACK_SPEED(AttributeType.COMBAT, 0f),
    ABILITY_DAMAGE(AttributeType.COMBAT, 0f),
    TRUE_DEFENCE(AttributeType.COMBAT, 0f),
    FEROCITY(AttributeType.COMBAT, 0f),
    HEALTH_REGEN(AttributeType.COMBAT, 100f),
    VITALITY(AttributeType.COMBAT, 100f),
    MENDING(AttributeType.COMBAT, 100f),
    SWING_RANGE(AttributeType.COMBAT, 3f),
    SPEED(AttributeType.MISC, 100f),
    MAGIC_FIND(AttributeType.MISC, 1f),
    SEA_CREATURE_CHANCE(AttributeType.MISC,20f);


    companion object {
        val VALUES = entries.toList()
    }
}