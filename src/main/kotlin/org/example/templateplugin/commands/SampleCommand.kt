package org.example.templateplugin.commands

import org.bukkit.command.*
import org.example.templateplugin.TemplatePluginMain
import org.example.templateplugin.commands.subcommands.ReloadSubCommand
import org.example.templateplugin.utils.MessageHelper

class SampleCommand(private val plugin: TemplatePluginMain) : CommandExecutor, TabExecutor {
    private val subCommands: MutableMap<String, SubCommandBase> = HashMap()
    private var subCommandsList: List<String> = emptyList()

    init {
        subCommands["reload"] = ReloadSubCommand(plugin)

        subCommandsList = subCommands.keys.toList()
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {

        if (args.isNotEmpty()) run {
            val sc: SubCommandBase = subCommands[args[0]] ?: return false

            val reqPerm: String = sc.getPermission()
            if (reqPerm != "" && !sender.hasPermission(reqPerm)) {
                MessageHelper.sendMessage(sender, plugin.langConfig.noPermission, hashMapOf("perm" to reqPerm))
                return true
            }

            // Check if console can use the sub command
            if (sender is ConsoleCommandSender && !sc.canConsoleUse()) {
                MessageHelper.sendMessage(sender, "This command cannot be used from console.")
                return true
            }

            sc.execute(sender, command, label, args)
        } else {
            // Base command without arguments here
        }

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<String>): List<String> {
        return when {
            args[0] == "" -> {
                subCommandsList
            }
            args.size == 1 -> {
                subCommandsList.filter { it.contains(args[0], ignoreCase = true) }
            }
            else -> {
                val sc: SubCommandBase = subCommands[args[0]] ?: return emptyList()
                return sc.onTabComplete(sender, command, alias, args)
            }
        }
    }

    fun getSubCommandList(): List<String> {
        return subCommandsList
    }

    fun getSubCommands(): MutableMap<String, SubCommandBase> {
        return subCommands
    }
}