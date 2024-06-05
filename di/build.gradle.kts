plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.secretsGradle)
}

android {
    namespace = "com.baker.eggtart.di"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    flavorDimensions += "tokenExpired"

    productFlavors {
        create("shortExpired")

        create("normalExpired")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    secrets {
        propertiesFileName = "secrets.properties"

        defaultPropertiesFileName = "local.defaults.properties"

        ignoreList.add("keyToIgnore")
        ignoreList.add("sdk.*")
    }
}

dependencies {
    implementation(project(":common:util"))

    implementation(project(":core:room"))
    implementation(project(":core:network"))
    implementation(project(":core:datastore"))

    implementation(project(":domain:mandalart"))
    implementation(project(":domain:kakao"))
    implementation(project(":domain:user"))

    implementation(libs.core.ktx)

    // Hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)

    // Room
    implementation(libs.room.ktx)

    // Ktor
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.client.negotiation)
    implementation(libs.ktor.client.auth)

    // Datastore
    implementation(libs.androidx.datastore)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}