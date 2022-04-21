package org.example.templateplugin.config.data

import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Comment

@ConfigSerializable
data class MainConfigData(
    @Comment("Optional comment")
    val pluginPrefix: String = "default value for this variable",
)