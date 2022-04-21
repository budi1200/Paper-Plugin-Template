package org.example.templateplugin.utils

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.example.templateplugin.TemplatePluginMain

object MessageHelper {
    private var plugin = TemplatePluginMain.instance
    private var pluginPrefix: Component = parseString(plugin.mainConfig.pluginPrefix)

    fun reloadPrefix() {
        pluginPrefix = parseString(plugin.mainConfig.pluginPrefix)
    }

    fun sendMessage(
        commandSource: CommandSender,
        rawMessage: String,
        placeholders: MutableMap<String, String> = hashMapOf(),
        prefix: Boolean = true
    ) {
        var output = Component.text("")

        // Add prefix if required
        if (prefix)
            output = output.append(pluginPrefix)

        output = output.append(parseString(rawMessage, placeholders))

        commandSource.sendMessage(output)
    }

    fun sendActionBarMessage(
        player: Player,
        rawMessage: String,
        placeholders: HashMap<String, String> = hashMapOf()
    ) {
        val message = parseString(rawMessage, placeholders)

        player.sendActionBar(message)
    }

    fun parseString(key: String, placeholders: Map<String, String> = hashMapOf()): Component {
        val resolver = TagResolver.resolver(placeholders.map { Placeholder.parsed(it.key, it.value) })

        return Component
            .text("")
            .decoration(TextDecoration.ITALIC, false)
            .append(
                MiniMessage.miniMessage().deserialize(key, resolver)
            )
    }
}