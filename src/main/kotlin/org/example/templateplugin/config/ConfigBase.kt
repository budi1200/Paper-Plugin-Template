package org.example.templateplugin.config

import org.bukkit.plugin.java.JavaPlugin
import org.spongepowered.configurate.CommentedConfigurationNode
import org.spongepowered.configurate.ConfigurateException
import org.spongepowered.configurate.hocon.HoconConfigurationLoader
import org.spongepowered.configurate.kotlin.objectMapperFactory
import java.io.IOException
import java.nio.file.Path

open class ConfigBase<T>(plugin: JavaPlugin, fileName: String, private val clazz: Class<T>) {
    private val logger = plugin.logger
    private val dataDirectory = plugin.dataFolder
    private val configPath = Path.of("$dataDirectory/$fileName")

    private val loader = HoconConfigurationLoader.builder()
        .path(configPath)
        .prettyPrinting(true)
        .defaultOptions { options ->
            options.shouldCopyDefaults(true)
            options.serializers { builder ->
                builder.registerAnnotatedObjects(objectMapperFactory())
            }
        }
        .build()

    private lateinit var root: CommentedConfigurationNode
    private var config: T? = null

    fun reloadConfig(): Boolean {
        root = try {
            loader.load()
        } catch (e: IOException) {
            logger.severe("An error occurred while loading configuration: ${e.message}")
            if (e.cause != null) {
                e.cause!!.printStackTrace()
            }
            return false
        }

        val tmp = root.get(clazz)

        if (tmp == null) {
            logger.severe("An error occurred while parsing configuration")
            return false
        }

        config = tmp

        return true
    }

    fun getConfig(): T {
        if (config != null) reloadConfig()
        return config!!
    }

    fun saveConfig(data: T) {
        try {
            val node: CommentedConfigurationNode = loader.load()

            node[clazz] = data
            loader.save(node)
        } catch (exception: ConfigurateException) {
            logger.severe("Could not save configuration: ${exception.message}")
        }
    }

    private fun saveDefaultConfig() {
        if (!dataDirectory.exists())
            dataDirectory.mkdir()

        try {
            val node: CommentedConfigurationNode = loader.load()
            config = node[clazz]
            node[clazz] = config
            loader.save(node)
        } catch (exception: ConfigurateException) {
            logger.severe("Could not load configuration: ${exception.message}")
        }
    }

    init {
        saveDefaultConfig()
    }
}