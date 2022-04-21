package org.example.templateplugin.config

import org.example.templateplugin.TemplatePluginMain
import org.example.templateplugin.config.data.LangConfigData

class LangConfig(plugin: TemplatePluginMain) : ConfigBase<LangConfigData>(plugin, "messages.conf", LangConfigData::class.java)