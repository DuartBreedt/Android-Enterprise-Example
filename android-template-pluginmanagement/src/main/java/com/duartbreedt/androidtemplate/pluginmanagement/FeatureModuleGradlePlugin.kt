package com.duartbreedt.androidtemplate.pluginmanagement

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project

class FeatureModuleGradlePlugin : LibraryGradlePlugin() {

    override fun apply(project: Project) {
        super.apply(project)

        project.configure<LibraryExtension> {
            buildFeatures {
                compose = true
                viewBinding = true
            }
        }
    }
}