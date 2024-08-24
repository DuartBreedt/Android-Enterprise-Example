package com.duartbreedt.androidtemplate.pluginmanagement

import com.android.build.gradle.BaseExtension
import com.duartbreedt.androidtemplate.pluginmanagement.ProjectConfig.JAVA_VERSION
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinTopLevelExtension

abstract class BaseGradlePlugin : Plugin<Project> {

    override fun apply(project: Project) {

        project.configure<BaseExtension> {

            compileSdkVersion(ProjectConfig.COMPILE_SDK)

            defaultConfig {
                it.minSdk = ProjectConfig.MIN_SDK

                it.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
        }

        project.configure<KotlinTopLevelExtension> {
            jvmToolchain(JAVA_VERSION)
        }
    }
}