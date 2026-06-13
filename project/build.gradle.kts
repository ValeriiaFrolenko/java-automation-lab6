plugins {
    id("java")
    id("checkstyle")
}

group = "frolenko"
version = "1.0-SNAPSHOT"

checkstyle {
    toolVersion = "13.5.0"
    configFile = file("config/checkstyle/checkstyle.xml")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.1.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.register<Test>("unitTests") {
    useJUnitPlatform {
        includeTags("unit")
    }
    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.register<Test>("integrationTests") {
    useJUnitPlatform {
        includeTags("integration")
    }
    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath
    testLogging {
        events("passed", "skipped", "failed")
    }
}