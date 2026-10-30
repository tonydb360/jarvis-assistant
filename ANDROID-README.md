# JARVIS Android — Foundation

This is the native Android foundation for the JARVIS project. It lives on the `android-native` branch so the original GitHub Pages web app on `main` remains unchanged.

## Build without a computer

GitHub Actions builds the debug APK in the cloud:
1. Open the repository's **Actions** tab.
2. Select **Build JARVIS Android**.
3. Tap **Run workflow** and select the `android-native` branch.
4. Open the completed workflow run.
5. Under **Artifacts**, download `jarvis-debug-apk`.
6. Extract the ZIP and install `app-debug.apk` on your Android phone.

## Current foundation
- Native Android app using Kotlin and Jetpack Compose
- JARVIS-inspired dashboard
- Live clock and date
- Basic network connectivity indicator
- Cloud APK build workflow

Voice recognition and AI are intentionally not wired up yet. The next stages will add voice input/output, local assistant features, and then investigate a secure supported ChatGPT sign-in route. Never put passwords, access tokens, or API keys in this public repository.

## Build requirements
- Android Gradle Plugin 8.7.3
- Gradle 8.9
- JDK 17
- Android SDK 35
