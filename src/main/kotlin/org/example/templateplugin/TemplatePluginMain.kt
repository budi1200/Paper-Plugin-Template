package org.example.templateplugin

import org.bukkit.plugin.java.JavaPlugin
import org.example.templateplugin.commands.SampleCommand
import org.example.templateplugin.config.LangConfig
import org.example.templateplugin.config.MainConfig
import org.example.templateplugin.config.data.LangConfigData
import org.example.templateplugin.config.data.MainConfigData

class TemplatePluginMain: JavaPlugin() {
    // Config variables
    lateinit var mainConfigObj: MainConfig
    lateinit var mainConfig: MainConfigData
    lateinit var langConfigObj: LangConfig
    lateinit var langConfig: LangConfigData

    // Commands
    lateinit var mainCommand: SampleCommand

    companion object {
        lateinit var instance: TemplatePluginMain
    }

    override fun onEnable() {
        instance = this

        // Load configs
        mainConfigObj = MainConfig(this)
        mainConfig = mainConfigObj.getConfig()

        langConfigObj = LangConfig(this)
        langConfig = langConfigObj.getConfig()

        // Load commands
        mainCommand = SampleCommand(instance)
        getCommand("samplecommand")?.setExecutor(mainCommand)
    }
}