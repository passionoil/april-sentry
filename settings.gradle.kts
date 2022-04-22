val name: String by extra

rootProject.name = name

pluginManagement {
    resolutionStrategy {
        plugins {
            val kotlinVersion: String by extra

            kotlin("multiplatform") version kotlinVersion
        }
    }

    repositories {
        gradlePluginPortal()
    }
}
