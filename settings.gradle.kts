pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
    }
}

include(":app", ":shared"/*, ":web"*/)
rootProject.name = "mmapp"
