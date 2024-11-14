plugins {
    alias(libs.plugins.portfolio.kotlinMultiplatform)
    alias(libs.plugins.portfolio.composeMultiplatform)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)
            implementation(projects.core.model)
            api(projects.core.domain)
            implementation(projects.core.designsystem)
            implementation(projects.core.localization)

            implementation(libs.koin.compose)
            implementation(libs.koin.composeVM)

            implementation(libs.navigation.compose)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.filekit.core)

            implementation(libs.coil.compose)
        }
    }
}