plugins {
    `version-catalog`
    `maven-publish`
}

version = "1.0.0-SNAPSHOT"
group = "com.duartbreedt.androidtemplate"

catalog {
    // declare the aliases, bundles and versions in this block
    versionCatalog {
        from(files("./gradle/libs.versions.toml"))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["versionCatalog"])
        }
    }
}