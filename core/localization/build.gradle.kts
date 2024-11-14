plugins {
    alias(libs.plugins.portfolio.kotlinMultiplatform)
    alias(libs.plugins.portfolio.composeMultiplatform)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(libs.coil.compose)
        }
    }
}