plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.berakahnd.quote"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.berakahnd.quote"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    ksp{
        arg("room.schemaLocation","$projectDir/schemas")
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
    implementation(libs.androidx.hilt.common)
    implementation(libs.androidx.hilt.work)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //WORK
    implementation(libs.androidx.work.runtime.ktx)
    implementation("androidx.hilt:hilt-work:1.0.0")
    // ROOM
    val roomVersion = "2.6.1"
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    // ROOM KTX
    implementation(libs.androidx.room.ktx)
    // ROOM KSP
    ksp(libs.room.compiler)
    // HILT
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt ("com.google.dagger:hilt-compiler:2.45")
    kapt ("androidx.hilt:hilt-compiler:1.0.0" )
    implementation (libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    // ICON
    //noinspection UseTomlInstead
    implementation("androidx.compose.material:material-icons-extended")
    // WINDOW SIZE
    //noinspection UseTomlInstead
    implementation("androidx.compose.material3:material3-window-size-class")
    //VIEW MODEL
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Retrofit for API requests
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    //COIL
    implementation(libs.coil.compose)
    // NAVIGATION
    implementation (libs.androidx.navigation.compose)
}