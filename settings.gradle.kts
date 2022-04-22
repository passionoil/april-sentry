rootProject.name = "april-sentry"

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
