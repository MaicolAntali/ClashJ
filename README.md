# ClashJ 

![GitHub top language](https://img.shields.io/github/languages/top/MaicolAntali/clashJ?style=for-the-badge&logo=kotlin&logoColor=%23FFFFFF)
[![Build](https://img.shields.io/github/actions/workflow/status/MaicolAntali/clashJ/gradle-build.yml?style=for-the-badge&logo=githubactions&logoColor=%23FFFFFF&)](https://github.com/MaicolAntali/clashJ/actions/workflows/gradle-build.yml)
[![Validation](https://img.shields.io/github/actions/workflow/status/MaicolAntali/clashJ/gradle-wrapper-validation.yml?style=for-the-badge&logo=githubactions&logoColor=%23FFFFFF&label=Validation)](https://github.com/MaicolAntali/clashJ/actions/workflows/gradle-wrapper-validation.yml)
[![License](https://img.shields.io/github/license/MaicolAntali/ClashJ?style=for-the-badge&color=%23009E60)](https://github.com/MaicolAntali/clashJ/blob/main/LICENSE.txt)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.maicolantali/clashJ?style=for-the-badge)](https://central.sonatype.com/artifact/io.github.maicolantali/clashJ/)


ClashJ is a fully asynchronous Clash of Clans API wrapper, written in Kotlin.

## 🔑 Key points

- Fully asynchronous library
- Support the login with the `Email` and `Password`
- 100% Clash of Clans API
- Events 🚧 (Not implemented yet)

## ℹ️ Getting started

The library required **Java 17** or higher versions.

### Download with Gradle
```gradle
dependencies {
    implementation 'io.github.maicolantali:clashJ:1.0.1'
}
```
### Download with Maven
```xml
<dependency>
    <groupId>io.github.maicolantali</groupId>
    <artifactId>clashJ</artifactId>
    <version>1.0.1</version>
</dependency>
```

### Usage Example
Simple example of the library usage:
```kotlin
fun main() = runBlocking {
    // Build a client with the default option 
    val client = ClientBuilder("email", "password").build()
    
    // Retrive player info
    val player = client.getPlayer("#tag")
    println("Name: ${player.name}, TH lvl: ${player.townHallLevel}")
    
    // Retrive clan info
    val clan = client.getClan("#tag")
    println("Name: ${clan.name}, Tag: ${clan.tag}")
}
```

## 🔗Links

- [clashJ documentation](https://javadoc.io/doc/io.github.maicolantali/clashJ)

## ✅ Contributing
Thank you for considering contributing to clashJ!

Here's how you can contribute:

1. **Check for Issues**: See if there's an existing issue or feature request related to your contribution. If not, create a new one, so we can discuss it.
2. **Fork & Branch**: Fork the repository and create a new branch for your changes.
3. **Code with Love**: Write your code following the Kotlin coding standards.
4. **Tests are Awesome**: If you're adding new features, don't forget to include tests.
5. **Pull Request**: Submit a pull request with a brief description of your changes.

## Disclaimer
This content is not affiliated with, endorsed, sponsored, or specifically approved by Supercell and Supercell is not responsible for it. 
For more information, see [Supercell's Fan Content Policy](https://supercell.com/en/fan-content-policy/).