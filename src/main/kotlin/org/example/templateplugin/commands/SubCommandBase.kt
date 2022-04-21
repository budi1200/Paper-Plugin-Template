package org.example.templateplugin.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender

interface SubCommandBase {

    fun execute(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean

    fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<String>): List<String> {
        return listOf("")
    }

    fun getPermission(): String

    fun getDesc(): String

    fun canConsoleUse(): Boolean
}