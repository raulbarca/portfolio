plugins {
    alias(libs.plugins.portfolio.kotlinMultiplatform)
    alias(libs.plugins.portfolio.composeMultiplatform)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)
            implementation(projects.core.localization)
            implementation(libs.coil.compose)
            implementation(libs.zoomimage.compose.coil)
        }
    }
}