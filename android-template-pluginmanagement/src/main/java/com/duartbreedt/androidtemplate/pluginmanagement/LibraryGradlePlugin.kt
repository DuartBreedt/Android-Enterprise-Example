package com.duartbreedt.androidtemplate.pluginmanagement

import org.gradle.api.Project

open class LibraryGradlePlugin : BaseGradlePlugin() {

    override fun apply(project: Project) {
        super.apply(project)

        project.group = "com.duartbreedt.androidtemplate"

    }
}