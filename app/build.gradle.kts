plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    id("kotlin-kapt") // For annotation processing (needed for Hilt)
    id("com.google.dagger.hilt.android") // For Hilt Dependency Injection
}

android {
    namespace = "com.example.nit3213project"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nit3213project"
        minSdk = 24
        targetSdk = 34
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Retrofit and Gson for API calls
    implementation (libs.retrofit.v290)
    implementation (libs.retrofit2.converter.gson)

    // Retrofit, Moshi, and OkHttp for networking
    implementation(libs.squareup.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.logging.interceptor)
    // Optional Gson converter if needed
    implementation(libs.retrofit2.converter.gson)

    // Lifecycle components for LiveData and ViewModel
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Coroutines support
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Hilt for dependency injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}
kapt {
    correctErrorTypes = true // Ensure kapt handles error types correctly
}
