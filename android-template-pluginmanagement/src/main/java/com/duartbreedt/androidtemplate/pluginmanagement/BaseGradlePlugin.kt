package com.duartbreedt.androidtemplate.pluginmanagement

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class BaseGradlePlugin : Plugin<Project> {

    override fun apply(project: Project) {

        project.configure<BaseExtension> {

            compileSdkVersion(ProjectConfig.COMPILE_SDK)

            defaultConfig {
                it.minSdk = ProjectConfig.MIN_SDK

                it.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            compileOptions {
                it.sourceCompatibility = JavaVersion.VERSION_17
                it.targetCompatibility = JavaVersion.VERSION_17
            }
        }
    }
}