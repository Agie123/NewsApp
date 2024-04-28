import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.newsapp"
    compileSdk = 34

    defaultConfig {
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        // Set API keys in BuildConfig
        buildConfigField("String", "API_KEY", properties.getProperty("API_KEY"))

        applicationId = "com.example.newsapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation (libs.androidx.fragment.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.navigation.ui.ktx)

    //Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit 2 for handling API calls
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    //Coroutines
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //Glide
    implementation (libs.glide)
    annotationProcessor (libs.glide.compiler)
}