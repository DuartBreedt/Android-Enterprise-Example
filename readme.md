# Android Template

This Android example project outlines some architectural and technology choices best suited for corporate or enterprise Android projects.

## Getting started

The Android Template app communicates with the a KTOR API found in the `android-template-api` project.
This API will need to be launched in order to have the app fully functional.

1. Open up the `android-template-api` in your IDE of choice and run the project. The server should be running on port `8080`.
2. Open up the `android-template` project in Android Studio.
3. Launch an emulator or connect a physical device using USB.
4. In a terminal run `adb reverse tcp:8080 tcp:8080`. This routes the address `localhost` with port `8080` on your mobile device or emulator to the address `localhost` with port `8080` o nyour development environment.
5. Build and deploy the app to your test device (emualtor or physical device) from Android Studio.

## Module Structure
[Module Diagram](resources/module-diagram.jpg) 

## Concepts, technology, and architecture used
