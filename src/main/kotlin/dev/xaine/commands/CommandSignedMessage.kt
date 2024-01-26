package dev.xaine.commands

import net.kyori.adventure.chat.SignedMessage
import net.minestom.server.adventure.audience.Audiences
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentString

class CommandSignedMessage : Command("signed_message") {
    init {
        addSyntax({sender, context ->
            val message: String = context.get("message")
            val signedMessage: SignedMessage
        }, ArgumentString("message"))
    }
}