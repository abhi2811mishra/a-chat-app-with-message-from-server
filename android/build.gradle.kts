buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal() // ✅ important for resolving plugins
        maven { url = uri("https://storage.googleapis.com/download.flutter.io") } // ✅ Flutter plugin repo
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.3.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.23")
        
        classpath("com.google.gms:google-services:4.4.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

// Optional: relocate build output folders (you're already doing this)
val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}

// ✅ clean task
tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
