pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    // Local Plugins
    val localPlugins: List<String> = listOf(
        "../android-template-pluginmanagement"
    )

    localPlugins.forEach {
        if (File(rootDir, it).exists()) {
            includeBuild(it)
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            if (File(rootDir, "../android-template-catalog").exists()) {
                from(files("../android-template-catalog/gradle/libs.versions.toml"))
            } else {
                from("com.duartbreedt.androidtemplate:android-template-catalog:1.0.0-SNAPSHOT")
            }
        }
    }
}

rootProject.name = "android-template-data-user"
include(
    ":data-user-api"
)

// Local Modules
private val localModules: List<String> = listOf(
    "../android-template-catalog"
)

localModules.forEach {
    if (File(rootDir, it).exists()) {
        includeBuild(it)
    }
}
