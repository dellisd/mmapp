plugins {
    kotlin("js")
}

// https://youtrack.jetbrains.com/issue/KT-34389
org.jetbrains.kotlin.gradle.targets.js.npm.NpmResolverPlugin.apply(project)

repositories {
    maven(url = "https://kotlin.bintray.com/kotlin-js-wrappers")
    jcenter()
    mavenCentral()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-dev")
    maven(url = "https://kotlin.bintray.com/kotlin-eap")
}

kotlin {
    target {
        browser()
    }

    val reactVersion = "16.13.1-pre.105-kotlin-1.3.72"
    dependencies {
        implementation(kotlin("stdlib-js"))

        implementation("org.jetbrains:kotlin-react:$reactVersion")
        implementation("org.jetbrains:kotlin-react-dom:$reactVersion")
        implementation(npm("react", "16.13.1"))
        implementation(npm("react-dom", "16.13.1"))

        implementation("org.jetbrains:kotlin-styled:1.0.0-pre.105-kotlin-1.3.72")
        implementation(npm("styled-components", "5.1.0"))
        implementation(npm("inline-style-prefixer", "6.0.0"))
    }
}