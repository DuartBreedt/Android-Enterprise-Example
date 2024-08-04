package com.duartbreedt.androidtemplate.pluginmanagement

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.plugin.use.PluginDependency

internal val Project.versionCatalog: VersionCatalog
    get() = rootProject.extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

internal inline fun <reified T> ExtensionAware.configure(block: T.() -> Unit) {
    extensions.getByType(T::class.java).block()
}

internal fun Project.getPlugin(alias: String): PluginDependency =
    versionCatalog.findPlugin(alias).get().get()