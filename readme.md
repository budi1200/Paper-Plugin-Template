## Getting Started

### General
- Refactor package name (should be in the following format: `group.projectName`)
- Refactor main plugin class (`TemplatePluginMain`)
- Refactor main command class (`SampleCommand`)
- Update command name in main plugin class

### build.gradle.kts

- Update variable `group`
- Update output path for task `buildAndPush`
- Update `bukkit` section with your plugin info

### settings.gradle.kts

- Update variable `rootProject.name`

## Useful links

[Paper Javadocs](https://papermc.io/javadocs/paper/1.18/)

[Configurate](https://github.com/SpongePowered/Configurate/wiki)

[Plugin yml](https://github.com/Minecrell/plugin-yml#bukkit=)

[Exposed Wiki](https://github.com/JetBrains/Exposed/wiki)

