plugins {
    alias(libs.plugins.portfolio.kotlinMultiplatform)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core.common)
            implementation(projects.core.model)
            api(projects.core.data)
        }
    }
}