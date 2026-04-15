plugins {
    alias(libs.plugins.android.application)
//    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.legacy.kapt)
//    id("kotlin-kapt")
//    id("com.google.devtools.ksp")
}

android {
    namespace = "com.peihua.chartline"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.peihua.chartline"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

//    kotlinOptions {
//        compileOptions {
//            jvmTarget = "17"
//        }
//        jvmTarget = "17"
//    }
    buildFeatures {
        compose = true
    }
}
kotlin {
    jvmToolchain(17)
}
dependencies {
    implementation("com.github.peihua8858:ComposeUtils:1.0.2")
    implementation(libs.androidx.compose.navigation)
//    implementation("androidx.navigation:navigation-compose:2.9.7")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")
    implementation("com.google.dagger:hilt-android:2.59.2")
//    kapt("com.google.dagger:hilt-compiler:2.57.1")
//    implementation("com.google.dagger:hilt-android:2.40.1")
    kapt("com.google.dagger:hilt-android-compiler:2.59.2")
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.google.code.gson:gson:2.13.2")
//    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
//    implementation("com.squareup.moshi:moshi-kotlin:1.15.2")
//    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.2")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.coil3.compose)
    implementation(libs.coil3.okhttp)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation (libs.androidx.material.icons.extended)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("com.github.AnyChart:AnyChart-Android:1.1.5")
    implementation("com.github.peihua8858:MPAndroidChart:3.1.1")
    implementation ("com.github.AAChartModel:AAChartCore-Kotlin:8.2.1")
//    implementation(platform("io.github.dautovicharis:charts-bom:2.2.0"))
    implementation("io.github.dautovicharis:charts-line:2.2.0")
    implementation("io.github.dautovicharis:charts-pie:2.2.0")
    implementation("io.github.dautovicharis:charts-bar:2.2.0")
//    implementation("io.github.dautovicharis:charts-histogram:2.2.0")
    implementation("io.github.dautovicharis:charts-stacked-bar:2.2.0")
    implementation("io.github.dautovicharis:charts-stacked-area:2.2.0")
    implementation("io.github.dautovicharis:charts-radar:2.2.0")

    implementation("com.patrykandpatrick.vico:compose-m3:3.1.0")
    implementation("com.himanshoe:charty:3.0.0-rc01")
    implementation("co.yml:ycharts:2.1.0")
    implementation ("io.github.ehsannarmani:compose-charts:0.2.5")
    implementation("io.github.thechance101:chart:1.1.0")
    implementation("com.github.wangyiqian:StockChart:1.1.16")
    implementation("com.github.peihua8858.compose-multiplatform-charts:charts:1.0.0-alpha")
}