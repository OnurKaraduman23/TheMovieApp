plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("androidx.navigation.safeargs")
    id("kotlin-kapt")
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.themovieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.themovieapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    // AndroidX Core KTX
    implementation(libs.androidx.core.ktx)

    // AndroidX AppCompat
    implementation(libs.androidx.appcompat)

    // Material Components
    implementation(libs.material)

    // AndroidX Activity
    implementation(libs.androidx.activity)

    // AndroidX ConstraintLayout
    implementation(libs.androidx.constraintlayout)

    // AndroidX Paging Common for Android
    implementation(libs.androidx.paging.common.android)

    // AndroidX Leanback Paging
    implementation(libs.androidx.leanback.paging)

    // JUnit
    testImplementation(libs.junit)

    // AndroidX JUnit
    androidTestImplementation(libs.androidx.junit)

    // AndroidX Espresso Core
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.converter.gson)

    // Room Database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    //Hilt
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)


    // Circle Image View
    implementation(libs.circleImageView)

    // AndroidX Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Coroutines
    implementation(libs.coroutines)

    // Logging Interceptor
    implementation(libs.logging.interceptor)

    // Coil
    implementation(libs.coil)
}
