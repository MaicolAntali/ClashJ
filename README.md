# ClashJ

[![Release](https://jitpack.io/v/MaicolAntali/clashJ.svg?style=flat-square)](https://jitpack.io/#MaicolAntali/clashJ)
[![KDocs](https://img.shields.io/static/v1?label=Dokka&message=KDocs&color=blue&logo=kotlin)](https://maicolantali.github.io/clashJ/)

[![License](https://img.shields.io/github/license/MaicolAntali/ClashJ?style=flat-square&color=%23009E60)](https://github.com/MaicolAntali/clashJ/blob/main/LICENSE.txt)
[![Build](https://img.shields.io/github/actions/workflow/status/MaicolAntali/clashJ/gradle-build.yml?style=flat-square&logo=githubactions&logoColor=%23FFFFFF&)](https://github.com/MaicolAntali/clashJ/actions/workflows/gradle-build.yml)
[![Validation](https://img.shields.io/github/actions/workflow/status/MaicolAntali/clashJ/gradle-wrapper-validation.yml?style=flat-square&logo=githubactions&logoColor=%23FFFFFF&label=Validation)](https://github.com/MaicolAntali/clashJ/actions/workflows/gradle-wrapper-validation.yml)


ClashJ is a fully asynchronous Clash of Clans API wrapper, written in Kotlin.

## 🔑 Key points

- Fully asynchronous library
- Support the login with the `Email` and `Password`
- 100% Clash of Clans API
- Events

## ℹ️ Getting started

The library required **Java 17** or higher versions.

### Download with Gradle

1. Add it in your root `build.gradle`e at the end of repositories:
   ```gradle
   repositories {
      // ...
      maven { url 'https://jitpack.io' }
   }
   ```

2. Add the dependency
   ```gradle
   dependencies {
      implementation 'com.github.MaicolAntali:clashJ:1.0.3'
   }
   ```

### Download with Maven

1. Add it in your root `pom.xml` at the end of repositories:
   ```xml
   <repositories>
       <repository>
           <id>jitpack.io</id>
           <url>https://jitpack.io</url>
       </repository>
   </repositories>
   ```

2. Add the dependency
   ```xml
   <dependency>
       <groupId>com.github.MaicolAntali</groupId>
       <artifactId>clashJ</artifactId>
       <version>1.0.3</version>
   </dependency>
   ```

### Usage Example

#### Simple Usage Example

```kotlin
fun main() = runBlocking {
    // Build a client with the default option 
    val client = Client("email", "pwd")
   
   // Login into the Clash of Clans developer website
   client.login()

    // Retrive player info
    val player = client.getPlayer("#tag").await()
    println("Name: ${player.name}, TH lvl: ${player.townHallLevel}")

    // Retrive clan info
    val clan = client.getClan("#tag").await()
    println("Name: ${clan.name}, Tag: ${clan.tag}")
}
```

#### Event Usage Example

```kotlin
fun main() = runBlocking {
    val eventClient = EventClient("email", "pwd")

    // Add player and clan to monitored events
    eventClient.addPlayerToMonitoredEvent("#playerTag")
    eventClient.addClanToMonitoredEvent("#clanTag")

    // Register a callback for the "JoinClan" event
    eventClient.registerPlayerCallback(MonitoredEvent.PlayerEvents.JoinClan) { _, current ->
        println("${current.name} has joined a clan.")
    }

    // Register a callback for the "MemberJoin" event
    eventClient.registerClanCallback(MonitoredEvent.ClanEvents.MemberJoin) { _, current, member ->
        println("${member.name} has joined the clan ${current.name}.")
    }

    // Login and start polling for events
    eventClient.login()
    eventClient.startPolling()
}
```

## 🔗Links

- [📜 Docs](https://maicolantali.github.io/clashJ/)
- [📖 Wiki](https://github.com/MaicolAntali/clashJ/wiki)

## ✅ Contributing

Thank you for considering contributing to clashJ!

Here's how you can contribute:

1. **Check for Issues**: See if there's an existing issue or feature request related to your contribution.
   If not, create a new one, so we can discuss it.
2. **Fork & Branch**: Fork the repository and create a new branch for your changes.
3. **Code**: Write your code following the Kotlin coding standards.
4. **Tests are Awesome**: If you're adding new features, don't forget to include tests.
5. **Pull Request**: Submit a pull request with a brief description of your changes.

## Disclaimer

This content is not affiliated with, endorsed, sponsored, or specifically approved by Supercell and Supercell is not
responsible for it.
For more information, see [Supercell's Fan Content Policy](https://supercell.com/en/fan-content-policy/).
