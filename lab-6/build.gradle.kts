plugins {
    id("java")
    id("war")
    id("org.gretty") version "4.0.3"
}

group = "ru.iuribabalin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.glassfish.jersey.containers:jersey-container-servlet-core:3.1.9")
    implementation("org.glassfish.jersey.media:jersey-media-json-processing:3.1.9")
    implementation("org.glassfish.jersey.inject:jersey-hk2:3.1.9")
    implementation("org.glassfish:jakarta.json:2.0.1")

    implementation("jakarta.xml.bind:jakarta.xml.bind-api:3.0.1")

    implementation("com.sun.xml.ws:jaxws-rt:3.0.0")
    implementation("jakarta.jws:jakarta.jws-api:3.0.0")
    implementation("jakarta.xml.ws:jakarta.xml.ws-api:3.0.0")
    implementation("javax.xml.bind:jaxb-api:2.3.1")

    implementation("jakarta.platform:jakarta.jakartaee-api:9.1.0")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("jakarta.servlet:jakarta.servlet-api:4.0.4")

    implementation("org.apache.tomcat:tomcat-jdbc:10.0.0")

    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("org.slf4j:slf4j-simple:2.0.9")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    implementation("org.flywaydb:flyway-core:10.19.0")
    implementation("org.flywaydb:flyway-database-postgresql:10.19.0")
    implementation("org.postgresql:postgresql:42.7.2")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<War>("war") {
    archiveFileName.set("employees-service.war") // Устанавливаем имя WAR-файла
    archiveBaseName.set("employees-service")
}

gretty {
    contextPath = "/"
    servletContainer = "tomcat10"
    httpPort = 9091
    jvmArgs = listOf("--add-opens", "java.base/jdk.internal.misc=ALL-UNNAMED")
    enableNaming = true
}
