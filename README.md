# Tapper README

Tapper is inspired by one of the mini games from the app TikTok. The goal is to test and enhance your responsiveness by challenging you to tap circles in the same order as displayed on the screen. 

This is a game project generated with gdx-liftoff and built using the LibKTX library, which is an extension of the LibGDX game framework for Kotlin developers. This README file will provide you with an overview of the project structure and instructions on how to compile and run the game. 

This project was generated with a Kotlin project template that includes Kotlin application launchers and KTX utilities.

## Table of Contents

1. [Project Structure](#project-structure)
2. [Prerequisites](#prerequisites)
3. [How to Compile](#how-to-compile)
4. [How to Run](#how-to-run)
5. [Platforms](#platforms)
6. [Gradle](#gradle)

## Project Structure

The project structure is organized as follows:

<pre>
MyLibktxGame/
├── android/
├── core/
│   └── /src/main/kotlin/com/group4/tapper/
        └── controller
        └── assets
        └── view
        └── model
        └── FirebaseRepository
        └── Tapper
├── lwjgl3/
├── build.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
└── settings.gradle
</pre>

- `android/`: Contains the Android-specific source code and resources.
- `core/`: Holds the core game logic and assets, which are shared among all platforms.
- `lwjgl3/`: Holds desktop-specific code, resources, and LWJGL3 config for Windows, macOS, and Linux.
- `build.gradle`: The main Gradle build file for the entire project.
- `gradle.properties`: Gradle properties file for the project.
- `gradlew` and `gradlew.bat`: Gradle wrapper scripts for Unix and Windows, respectively.
- `local.properties`: Local properties file (not included in version control) for platform-specific settings.
- `settings.gradle`: Gradle settings file for the project.

## Prerequisites

To compile and run this project, you will need:

1. Java Development Kit (JDK) 11 or later.
2. Android Studio (optional, for Android development) or IntelliJ IDEA or another Kotlin-compatible IDE.


## How to Compile

1. Clone the project from the repository
2. Navigate to the project directory
3. Run the Gradle wrapper script to compile the project:

On Unix-based systems:

`./gradlew build`

On Windows:

`gradlew.bat build`



## How to Run

### Desktop

To run the desktop version of the game, execute the following command:

On Unix-based systems:

 `./gradlew desktop:run `

On Windows:

 `gradlew.bat desktop:run `



### Android

To run the Android version, you will need to have an emulator or physical device connected. Execute the following command:

On Unix-based systems:

 `./gradlew android:installDebug `

On Windows:

 `gradlew.bat android:installDebug `



### iOS

To run the iOS version, follow these steps:

1. Open the `ios/` folder in Xcode.
2. Connect an iOS device or start an iOS simulator.
3. Select the device/simulator from the target device list.
4. Press the "Run" button


## Platforms
* `core:` Main module with the application logic shared by all platforms.
* `lwjgl3:` Primary desktop platform using LWJGL3.
* `android:` Android mobile platform. Needs Android SDK.

## Gradle
This project uses Gradle to manage dependencies. The Gradle wrapper was included, so you can run Gradle tasks using gradlew.bat or ./gradlew commands. Useful Gradle tasks and flags:

* `--continue:` when using this flag, errors will not stop the tasks from running.
* `--daemon:` thanks to this flag, Gradle daemon will be used to run chosen tasks.
* `--offline:` when using this flag, cached dependency archives will be used.
* `--refresh-dependencies:` this flag forces validation of all dependencies. Useful for snapshot versions.
* `android:lint:` performs Android project validation.
* `build:` builds sources and archives of every project.
* `cleanEclipse:` removes Eclipse project data.
* `cleanIdea:` removes IntelliJ project data.
* `clean:` removes build folders, which store compiled classes and built archives.
* `eclipse:` generates Eclipse project data.
* `idea:` generates IntelliJ project data.
* `lwjgl3:jar:` builds application's runnable jar, which can be found at lwjgl3/build/libs.
* `lwjgl3:run:` starts the application.
* `test:` runs unit tests (if any).
Note that most tasks that are not specific to a single project can be run with name: prefix, where the name should be replaced with the ID of a specific project. For example, core:clean removes build folder only from the core project.
