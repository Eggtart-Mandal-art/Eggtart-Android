import dev.iurysouza.modulegraph.Orientation
import dev.iurysouza.modulegraph.Theme

// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hiltAndroid) apply false
    alias(libs.plugins.moduleGraph) apply true
    alias(libs.plugins.secretsGradle) apply false
} // Needed to make the Suppress annotation work for the plugins block

//buildscript {
//    dependencies {
//        classpath(libs.secrets.gradle)
//    }
//}

moduleGraphConfig {
    heading.set("EggTart Module Dependency Graph")
    readmePath.set("./README.md")
    orientation.set(Orientation.TOP_TO_BOTTOM)

    theme.set(Theme.NEUTRAL)
}