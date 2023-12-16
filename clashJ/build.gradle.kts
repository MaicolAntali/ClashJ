import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.21"
    id("org.jetbrains.dokka") version "1.9.10"
    id("org.jmailen.kotlinter") version "4.1.0"
    `java-library`
    `maven-publish`
}

version = "1.0.3"
group = "io.github.maicolantali"

repositories {
    mavenCentral()
}

dependencies {
    api(libs.kotlin.coroutines)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.gson)

    implementation(libs.oshai.logging.jvm)
    api(libs.logback.classic)

    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.kotlin.test.junit5)
    testImplementation(libs.junit.juniper)
    testImplementation(libs.assertj.core)

}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
    withJavadocJar()
}

tasks.named<Jar>("jar") {
    manifest {
        attributes(mapOf("Implementation-Title" to project.name, "Implementation-Version" to project.version))
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.named<Jar>("javadocJar") {
    from(tasks.named("dokkaJavadoc"))
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

publishing {
    publications {
        create<MavenPublication>("clashJ") {
            from(components["java"])

            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            pom {
                name.set(artifactId)
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
