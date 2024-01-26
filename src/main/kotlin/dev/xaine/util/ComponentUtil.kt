package dev.xaine.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.TextDecoration

class ComponentUtil {
    companion object {
        fun emptyText(): TextComponent {
            return Component.text("").decoration(TextDecoration.ITALIC, false)
        }
        fun text(text: String): TextComponent {
            Component.text()
            return Component.text(text).decoration(TextDecoration.ITALIC, false)
        }
    }

}