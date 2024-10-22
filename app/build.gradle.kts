plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt") // For annotation processing (needed for Hilt)
    id("com.google.dagger.hilt.android") // For Hilt Dependency Injection
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nit3213project"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    // Add other dependencies here
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit and Gson for API calls
    implementation(libs.retrofit.v290)
    implementation(libs.retrofit2.converter.gson)

    // Retrofit, Moshi, and OkHttp for networking
    implementation(libs.squareup.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.logging.interceptor)

    // Lifecycle components for LiveData and ViewModel
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Coroutines support
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Hilt for dependency injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Unit Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.5.0") // Updated version
    testImplementation("androidx.arch.core:core-testing:2.1.0") // Check the latest version
    testImplementation("androidx.lifecycle:lifecycle-testing:2.6.1") // Ensure you have the right version
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    // Mockito Inline Dependency
    testImplementation("org.mockito:mockito-inline:5.5.0") // Add this line
    testImplementation(libs.androidx.lifecycle.testing) // Check for availability
    testImplementation(libs.kotlinx.coroutines.test) // Check for availability
    testImplementation(libs.mockito.mockito.inline) // Check for availability
}
