plugins {
    id("com.android.application")
}

android {
    namespace = "com.luciano.drawar"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.luciano.drawar"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.ar:core:1.35.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
}
