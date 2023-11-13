import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.20"
    id("org.jetbrains.dokka") version "1.9.10"
    id("org.jmailen.kotlinter") version "4.0.0"
}

repositories {
    mavenCentral()
}

dependencies {
    api(libs.kotlin.coroutines)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.apache5)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.gson)

    api(libs.logback.classic)

    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.kotlin.test.junit5)
    testImplementation(libs.junit.juniper)
    testImplementation(libs.assertj.core)

}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }

    withSourcesJar()
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<DokkaTask>().configureEach {
    moduleName.set(project.name)
    moduleVersion.set(project.version.toString())
    failOnWarning.set(true)

    dokkaSourceSets {
        configureEach {
            reportUndocumented.set(true)
        }
    }
}

tasks.register<Jar>("dokkaHtmlJar") {
    dependsOn(tasks.dokkaHtml)
    from(tasks.dokkaHtml.flatMap { it.outputDirectory })
    archiveClassifier.set("javadoc")
}

publishing {
    publications {
        create<MavenPublication>("clashJ") {
            from(components["java"])
            artifact(tasks.named<Jar>("dokkaHtmlJar"))

            pom {
                name.set("clashJ")
                description.set("Kotlin library designed as an asynchronous API wrapper for Clash of Clans.")
                url.set("https://github.com/MaicolAntali/clashJ")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/MaicolAntali/clashJ/blob/main/LICENSE.txt")
                    }
                }
            }
        }
    }
}
