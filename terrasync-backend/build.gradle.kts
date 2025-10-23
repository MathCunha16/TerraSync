plugins {
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("java")
    id("org.sonarqube") version "7.0.0.6105"
    id("jacoco")
}

val mapstructVersion = "1.5.5.Final" // Versão do MapStruct

group = "com.terrasync"
version = "0.0.1-SNAPSHOT"
description = "Backend/API para o sistema web de monitoramento agrícola baseado em IoT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {
    // SPRING BOOT, FLYWAY, HIBERNATE, MAPSTRUCT (Core)
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.hibernate.orm:hibernate-spatial") // Para tipos geométricos
    implementation("org.mapstruct:mapstruct:$mapstructVersion")

    // DEVTOOLS
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // DATABASE (Driver)
    runtimeOnly("org.postgresql:postgresql")

    // PROCESSADORES DE ANOTAÇÃO
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

    // TESTES
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

jacoco {
    toolVersion = "0.8.13"
}

tasks.test {
    jacoco {
        isEnabled = true
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.8".toBigDecimal()
            }
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
