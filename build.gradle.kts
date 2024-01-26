plugins {
    kotlin("jvm") version "1.9.21"

}

group = "dev.xaine"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("dev.hollowcube:minestom-ce:5347c0b11f")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.20")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}