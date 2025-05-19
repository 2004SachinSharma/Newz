plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.1.20"
}

android {
    namespace = "com.example.newz"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.newz"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation ("androidx.core:core-splashscreen:1.2.0-beta01") // Dependency required to access Splash-Screen UI, as it is not in the base Jetpack Compose UI (Base: means the jetpack compose UI bundle , provided with Android studio by default).

    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("androidx.navigation:navigation-compose:2.8.9") // 🧭 Navigation Component for Jetpack Compose — used for navigating between composables/screens
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0") // 🔄 Kotlinx Serialization — for converting Kotlin data classes to/from JSON (similar to Gson, but from JetBrains)
    implementation("com.squareup.retrofit2:retrofit:2.11.0") //🌐 Retrofit — powerful HTTP client for API calling in Android
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")// 🔁 Retrofit Gson Converter — allows Retrofit to automatically convert JSON responses to Kotlin data classes using Gson
    implementation("com.squareup.okhttp3:okhttp:4.10.0") // ⚙️ OkHttp — the low-level HTTP client used under Retrofit for handling requests and responses (e.g., logging, interceptors)
    implementation("io.coil-kt.coil3:coil-compose:3.1.0") // coil dependency to load images from the network
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.1.0") // coil dependency to load images from the network


}
