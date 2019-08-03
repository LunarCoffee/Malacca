import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins { kotlin("jvm") version "1.3.41" }

group = "dev.lunarcoffee"
version = "0.1.0"

repositories { mavenCentral() }
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("junit:junit:4.11")
}

sourceSets {
    getByName("main").java.srcDirs("src/main/kotlin")
    getByName("test").java.srcDirs("src/test/kotlin")
}

val fatJar = task("fatJar", type = Jar::class) {
    baseName = "${project.name}-fjar"
    manifest {
        attributes["Main-Class"] = "dev.lunarcoffee.malacca.MainKt"
        attributes["Implementation-Title"] = "Malacca"
        attributes["Implementation-Version"] = version
    }
    from(configurations.compileClasspath.map { if (it.isDirectory) it else zipTree(it) })
    from(configurations.runtimeClasspath.map { if (it.isDirectory) it else zipTree(it) })
    with(tasks["jar"] as CopySpec)
}

tasks { "build" { dependsOn(fatJar) } }
tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }
