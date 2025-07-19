// android/app/build.gradle.kts

plugins {
    id("com.android.application")
    // START: FlutterFire Configuration
    id("com.google.gms.google-services")
    // END: FlutterFire Configuration
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "com.example.chat_app" // Ensure this matches your package name
    compileSdk = 35
    ndkVersion = "27.0.12077973" // Ensure this is explicitly set as a String

    

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString() // Align jvmTarget with Java 8
    }

    defaultConfig {
        // TODO: Specify your own unique Application ID (https://developer.android.com/studio/build/application-id.html).
        applicationId = "com.example.chat_app" // Ensure this matches your package name
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        // THIS IS THE CRUCIAL LINE: Must use '=' for assignment
       
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        isCoreLibraryDesugaringEnabled = true

    }

    buildTypes {
        release {
            // TODO: Add your own signing config for the release build.
            // Signing with the debug keys for now, so `flutter run --release` works.
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

flutter {
    source = "../.."
}

// THIS DEPENDENCIES BLOCK MUST BE AT THE END OF THE FILE
dependencies {
    // Required for core library desugaring
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.4")

    
    // Firebase dependencies (ensure these are present if flutterfire configure added them)
    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")

    // Multidex, if enabled
    implementation("androidx.multidex:multidex:2.0.1")
}