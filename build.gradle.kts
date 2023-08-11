plugins {
    `java-library`
    `maven-publish`
    id("io.izzel.taboolib") version "1.56"
    id("org.jetbrains.kotlin.jvm") version "1.5.10"
}

taboolib {
    description {
        contributors {
            name("枫溪")
        }
        dependencies {
            name("RaySkillSystem")
        }
    }
    install("common")
    install("common-5")
    install("module-configuration")
    install("module-database")
    install("platform-bukkit")
    install("expansion-command-helper")
    install("expansion-alkaid-redis")
    install("expansion-persistent-container-object")
    relocate("com.alibaba.fastjson2","top.maplex.rayskilljob.lib.fastjson2")

    classifier = null
    version = "6.0.11-local"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("ink.ptms.core:v11902:11902-minimize:mapped")
    compileOnly("ink.ptms.core:v11902:11902-minimize:universal")
    compileOnly("top.maplex.rayskillsystem:RaySkillSystem:2.5.1")
    taboo("com.alibaba.fastjson2:fastjson2-kotlin:2.0.39-SNAPSHOT")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    repositories {
        maven {
            url = uri("https://repo.tabooproject.org/repository/releases")
            credentials {
                username = project.findProperty("taboolibUsername").toString()
                password = project.findProperty("taboolibPassword").toString()
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
            groupId = project.group.toString()
        }
    }
}
