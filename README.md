April-Sentry
---

Kotlin Multiplatform Sentry Wrapper

...WIP

# How to use

## Gradle Repository

```kotlin
repositories {
    maven("https://maven.pkg.github.com/creatorclub/april-sentry") {
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }
}
```

## Kotlin Multiplatform

```kotlin
dependencies {
    implementation("io.april:april-sentry:x.y.z")
}
```

## KotlinJs Project

```kotlin
dependencies {
    implementation("io.april:april-sentry-js:x.y.z")
}
```

## Spring Boot Project

```kotlin
dependencies {
    implementation("io.april:april-sentry-spring:x.y.z")
}
```

...WIP

# Support

- [x] Kotlin/JS
    - [ ] <del>React</del>
- [x] Spring Boot
- [ ] Android
- [ ] iOS

# TODO

- [ ] English document
- [ ] Public package manager repository (ex: mavenCentral)
- [ ] Sentry api interface wrapping for pure kotlin
- [ ] iOS version
- [ ] Tests

# Contributing

...WIP
