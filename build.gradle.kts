import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ContextReceiver

plugins {
    kotlin("jvm") version "1.8.21"
}

group = "me.anton"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

kotlin {
//    explicitApi()
//    explicitApiWarning()

    sourceSets.all {
        languageSettings {
            enableLanguageFeature("ContextReceivers")
        }
    }
}