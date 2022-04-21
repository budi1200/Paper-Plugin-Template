package org.example.templateplugin.config

import org.example.templateplugin.TemplatePluginMain
import org.example.templateplugin.config.data.MainConfigData

class MainConfig(plugin: TemplatePluginMain) : ConfigBase<MainConfigData>(plugin, "config.conf", MainConfigData::class.java)