import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `maven-publish`
    kotlin("multiplatform")
}

repositories {
    mavenCentral()
}

kotlin {
    js {
        browser()
        binaries.executable()

        tasks.withType<Kotlin2JsCompile> {
            kotlinOptions {
                sourceMap = false
                sourceMapEmbedSources = null
                sourceMapPrefix = null
                freeCompilerArgs += listOf(
                    "-Xir-per-module",
                    "-Xir-property-lazy-initialization",
                )
            }
        }
    }

    jvm("spring") {
        tasks.withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "11"
            }
        }

        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jsMain by getting {
            dependencies {
                val sentryJsVersion: String by extra

                implementation(kotlin("stdlib-js"))
                api(npm("@sentry/browser", sentryJsVersion))
                api(npm("@sentry/tracing", sentryJsVersion))
            }
        }

        val jvmMain by creating {
            dependencies {
                implementation(kotlin("reflect"))
                implementation(kotlin("stdlib-jdk8"))
            }
        }

        val springMain by getting {
            dependsOn(commonMain)
            dependsOn(jvmMain)
            dependencies {
                val sentrySpringBootVersion: String by extra

                api("io.sentry:sentry-spring-boot-starter:$sentrySpringBootVersion")
            }
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/passionoil/april-sentry")

            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }

    publications {
        register<MavenPublication>("gpr") {
            from(components["kotlin"])
        }
    }
}
