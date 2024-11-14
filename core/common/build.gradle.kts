plugins {
    alias(libs.plugins.portfolio.kotlinMultiplatform)
    alias(libs.plugins.portfolio.composeMultiplatform)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.raulbarca.portfolio.resources"
    generateResClass = always
}
