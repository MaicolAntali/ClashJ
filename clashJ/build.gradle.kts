import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.20"
    id("org.jetbrains.dokka") version "1.9.10"
    id("org.jmailen.kotlinter") version "4.0.0"
}

repositories {
    mavenCentral()
}

val ktorVersion= "2.3.5"

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    api("ch.qos.logback:logback-classic:1.4.11")

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-apache5:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-gson:$ktorVersion")
    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.9.10")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.0")

    testImplementation("org.assertj:assertj-core:3.24.2")
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