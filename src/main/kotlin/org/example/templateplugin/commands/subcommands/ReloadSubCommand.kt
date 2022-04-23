package org.example.templateplugin.commands.subcommands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.example.templateplugin.TemplatePluginMain
import org.example.templateplugin.commands.SubCommandBase
import org.example.templateplugin.enums.Permission
import org.example.templateplugin.utils.MessageHelper

class ReloadSubCommand(private val plugin: TemplatePluginMain): SubCommandBase {
    override fun execute(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val reloadResults = hashMapOf<String, Boolean>()

        reloadResults["config.conf"] = plugin.mainConfigObj.reloadConfig()
        reloadResults["messages.conf"] = plugin.langConfigObj.reloadConfig()

        val failedReloads = reloadResults.filterValues { !it }
        if (failedReloads.isNotEmpty()) {
            MessageHelper.sendMessage(
                sender,
                "<red>Failed to reload configs! ${failedReloads.keys.joinToString(", ")}"
            )
            return true
        }

        plugin.mainConfig = plugin.mainConfigObj.getConfig()
        plugin.langConfig = plugin.langConfigObj.getConfig()

        MessageHelper.reloadPrefix()
        MessageHelper.sendMessage(sender, "<green>Plugin Reloaded!")
        return true
    }

    override fun getPermission(): String {
        return Permission.RELOAD.getPerm()
    }

    override fun getDesc(): String {
        return "Reloads the plugin"
    }

    override fun canConsoleUse(): Boolean {
        return true
    }
}
