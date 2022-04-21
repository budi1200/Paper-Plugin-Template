package org.example.templateplugin.config.data

import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class LangConfigData(
    val noPermission: String = "<red>Missing permission <perm>!"
)