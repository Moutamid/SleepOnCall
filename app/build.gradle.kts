plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.moutamid.sleeponcall"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.moutamid.sleeponcall"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        setProperty("archivesBaseName", "SleepOnCall-$versionName")
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
}

dependencies {
    implementation("com.github.akshay2211:Stash:1c45b0e5d5")

    implementation("com.github.angads25:toggle:1.1.0")
    implementation("io.github.ShawnLin013:number-picker:2.4.13")
    implementation("com.github.raheemadamboev:image-radio-button-android:1.0.7")

    implementation("com.google.firebase:firebase-auth:22.1.1")
    implementation("com.google.firebase:firebase-database:20.2.2")
    implementation("com.google.firebase:firebase-crashlytics:18.4.0")
    implementation("com.google.firebase:firebase-analytics:21.3.0")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}