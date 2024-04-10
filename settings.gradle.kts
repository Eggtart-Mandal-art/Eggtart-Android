pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = java.net.URI("https://devrepo.kakao.com/nexus/content/groups/public/")}
    }
}

rootProject.name = "Eggtart"
include(":app")
include(":core:room")
include(":core:network")
include(":domain:mandalart")
include(":features")
include(":features:home")
include(":features:login")
include(":features:write-goal")
include(":common:feature")
include(":common:util")
include(":di")
