plugins {
    `maven-publish`
    `java-gradle-plugin`
    kotlin("jvm") version "2.0.0"
}

// TODO Extract from catalog in plugin
version = "1.0.0-SNAPSHOT"
group = "com.duartbreedt.androidtemplate"

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "plugins-management"
        }
    }
}

configure<JavaPluginExtension> {
    withSourcesJar()
}

configure<GradlePluginDevelopmentExtension> {
    plugins {
        create("application-plugin-management") {
            id = "application-gradle-plugin"
            implementationClass = "com.duartbreedt.androidtemplate.pluginmanagement.ApplicationGradlePlugin"
        }


        create("library-plugin-management") {
            id = "library-gradle-plugin"
            implementationClass = "com.duartbreedt.androidtemplate.pluginmanagement.LibraryGradlePlugin"
        }
    }
}

dependencies {
    // TODO Extract to catalog
    implementation("com.android.tools.build:gradle:8.5.1")
    implementation(kotlin("gradle-plugin", version = "2.0.10"))
}