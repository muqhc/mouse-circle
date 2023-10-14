@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.js)
}

group = "org.example"
version = "1.0-SNAPSHOT"

/*  Which additional multiplatform (ORX) libraries should be added to this project. */
val orxFeatures = setOf<String>(
//  "orx-boofcv",
    "orx-camera",
//  "orx-chataigne",
    "orx-color",
    "orx-compositor",
//  "orx-dnk3",
//  "orx-easing",
//  "orx-file-watcher",
    "orx-fx",
//  "orx-glslify",
//  "orx-gradient-descent",
//    "orx-git-archiver",
    "orx-gui",
    "orx-image-fit",
//  "orx-integral-image",
//  "orx-interval-tree",
    "orx-jumpflood",
//  "orx-kdtree",
//  "orx-keyframer",      
//  "orx-kinect-v1",
//  "orx-kotlin-parser",
    "orx-mesh-generators",
//  "orx-midi",
//  "orx-minim",
    "orx-no-clear",
    "orx-noise",
//  "orx-obj-loader",
    "orx-olive",
//  "orx-osc",
//  "orx-palette",
    "orx-panel",
//  "orx-parameters",
    "orx-poisson-fill",
//  "orx-rabbit-control",
//  "orx-realsense2",
//  "orx-runway",
    "orx-shade-styles",
//  "orx-shader-phrases",
    "orx-shapes",
//  "orx-syphon",
//  "orx-temporal-blur",
//  "orx-tensorflow",    
//  "orx-time-operators",
//  "orx-timer",
//  "orx-triangulation",
//  "orx-video-profiles",
    "orx-view-box",
)
fun orx(module: String) = "org.openrndr.extra:$module:${libs.versions.orx.get()}"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(libs.kotlin.reflect)

    implementation(libs.openrndr.application)
    implementation(libs.openrndr.dds)
    implementation(libs.openrndr.draw)
    implementation(libs.openrndr.webgl)

    for (feature in orxFeatures) {
        implementation(orx(feature))
    }
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "openrndr-program.js"
                cssSupport {
                    enabled.set(true)
                }
            }
        }
        binaries.executable()
    }
}

tasks.getByName("browserDevelopmentRun").dependsOn("developmentExecutableCompileSync")