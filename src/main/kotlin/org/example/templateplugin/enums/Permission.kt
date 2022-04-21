package org.example.templateplugin.enums

enum class Permission(private val key: String) {
    RELOAD("commands.reload");


    fun getPerm(): String {
        return "templateplugin.$key"
    }
}