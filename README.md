# Tapper README

Tapper is inspired by one of the mini games from the app TikTok. The goal is to test and enhance your responsiveness by challenging you to tap circles in the same order as displayed on the screen. 



Welcome to MyLibktxGame! This is a game project built using the LibKTX library, which is an extension of the LibGDX game framework for Kotlin developers. This README file will provide you with an overview of the project structure and instructions on how to compile and run the game.

## Table of Contents

1. [Project Structure](#project-structure)
2. [Prerequisites](#prerequisites)
3. [How to Compile](#how-to-compile)
4. [How to Run](#how-to-run)
5. [Contributing](#contributing)
6. [License](#license)

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
- `desktop/`: Contains the desktop-specific source code and resources.
- `gradle/`: Holds the Gradle wrapper files.
- `ios/`: Contains the iOS-specific source code and resources.
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

./gradlew build

On Windows:

gradlew.bat build



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
