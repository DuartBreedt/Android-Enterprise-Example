plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.duartbreedt.androidtemplate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.duartbreedt.androidtemplate"
        minSdk = 24
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
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.navigation)
    // TODO Extract to published artifact
    implementation("com.duartbreedt.androidtemplate:android-template-core:1.0.0-SNAPSHOT")
    implementation("com.duartbreedt.androidtemplate:android-template-ui:1.0.0-SNAPSHOT")
    implementation("com.duartbreedt.androidtemplate:android-template-registration:1.0.0-SNAPSHOT")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.expresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.bundles.android.test.compose)
    implementation(libs.bundles.android.test.navigation)
    debugImplementation(libs.bundles.debug.compose)
}