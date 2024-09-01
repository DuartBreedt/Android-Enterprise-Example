package com.duartbreedt.androidtemplate.pluginmanagement

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.Project

class ApplicationGradlePlugin : BaseGradlePlugin() {

    override fun apply(project: Project) {
        super.apply(project)

        project.configure<BaseExtension> {

            defaultConfig {
                it.targetSdk = ProjectConfig.TARGET_SDK
                it.vectorDrawables {
                    useSupportLibrary = true
                }
            }
        }

        project.configure<ApplicationExtension> {
            buildFeatures {
                compose = true
                viewBinding = true
            }
        }
    }
}