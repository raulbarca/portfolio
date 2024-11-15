import java.util.Properties

plugins {
    alias(libs.plugins.portfolio.kotlinMultiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.buildConfig)
}

val secretKeyProperties by lazy {
    val secretKeyPropertiesFile = rootProject.file("secrets.properties")
    Properties().apply { secretKeyPropertiesFile.inputStream().use { secret -> load(secret) } }
}

kotlin {
    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }
        commonMain.dependencies {
            implementation(projects.core.model)
            implementation(projects.core.localization)

            implementation(libs.kotlinx.serialization.json)
            api(libs.bundles.ktor.common)
        }
        desktopMain.dependencies {
            implementation(libs.ktor.client.java)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

buildConfig {
    packageName = "com.raulbarca.portfolio"
    useKotlinOutput { internalVisibility = true }
    buildConfigField(
        "String",
        "API_HOST",
        "\"${secretKeyProperties["portfolio.api.host"]}\""
    )
    buildConfigField(
        "String",
        "APP_NAME",
        "\"${rootProject.name}\""
    )
}