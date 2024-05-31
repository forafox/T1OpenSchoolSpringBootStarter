plugins {
    id("java")
    id("maven-publish")
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.forafox"
version = "1.0"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.forafox"
            artifactId = "t1-open-school-spring-boot-starter"
            version = "1.0"
            from(components["java"])
            // Укажите минимальную версию
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("implementation")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
    }
    repositories {
        mavenLocal()
    }
}


tasks.test {
    useJUnitPlatform()
}