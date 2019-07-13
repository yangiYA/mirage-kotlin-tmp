/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin library project to get you started.
 */
plugins {
    val kotlinVersion = "1.3.31"
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm").version(kotlinVersion)
    java
    `maven-publish`
    signing

}
group = "com.github.yangiya"
version = "0.0.1-SNAPSHOT"


tasks.register<Jar>("sourcesJar") {
    from(sourceSets.main.get().allJava)
    archiveClassifier.set("sources")
}

tasks.register<Jar>("javadocJar") {
    from(tasks.javadoc)
    archiveClassifier.set("javadoc")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "mirage-kotlin"
            //            groupId = "com.github.yangiya"
            //            version = "0.0.1-SNAPSHOT"
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
        create<MavenPublication>("mavenJava") {
            pom {
                name.set("mirage Kotlin")
                description.set("Mirage-SQL Ktlin - the SQL Centric Database Access Library for Kotlin!")
                url.set("https://github.com/yangiYA/mirage-kotlin-tmp")
//                properties.set(mapOf(
//                        "myProp" to "value",
//                        "prop.with.dots" to "anotherValue"
//                ))
                licenses {
                    license {
                        name.set("MIT License")
//                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
            }
        }
        repositories {
            maven {
                // change URLs to point to your repos, e.g. http://my.org/repo
                val releasesRepoUrl = uri("$buildDir/repos/releases")
                val snapshotsRepoUrl = uri("$buildDir/repos/snapshots")
                url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            }
        }
    }
}
signing {
    sign(publishing.publications["mavenJava"])
}
tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}

repositories {
    mavenLocal()
    jcenter()
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}
