pluginManagement {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
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
                from("com.duartbreedt:android-template.android-template-catalog:1.0.0-SNAPSHOT")
            }
        }
    }
}

rootProject.name = "android-template"
include(":app")

// Local Modules
private val localModules: List<String> = listOf(
    "../android-template-catalog",
    "../android-template-core",
    "../android-template-ui",
    "../android-template-registration",
)

localModules.forEach {
    if (File(rootDir, it).exists()) {
        includeBuild(it)
    }
}
