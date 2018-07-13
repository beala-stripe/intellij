# Description: Indirections for SDK changes to the underlying platform library.

licenses(["notice"])  # Apache 2.0

load("//intellij_platform_sdk:build_defs.bzl", "select_for_plugin_api")

java_library(
    name = "v181",
    srcs = glob([
        "com/google/idea/sdkcompat/codestyle/**",
        "com/google/idea/sdkcompat/plugin/**",
        "com/google/idea/sdkcompat/psi/**",
        "com/google/idea/sdkcompat/python/**",
        "com/google/idea/sdkcompat/query/**",
        "com/google/idea/sdkcompat/run/**",
        "com/google/idea/sdkcompat/smrunner/**",
        "com/google/idea/sdkcompat/ui/**",
        "com/google/idea/sdkcompat/vcs/**",
    ]) + select_for_plugin_api({
        "clion-2018.1": glob([
            "com/google/idea/sdkcompat/clion/**",
            "com/google/idea/sdkcompat/cidr/**",
            "v2018_1_3/com/google/idea/sdkcompat/cidr/**",
        ]),
        "intellij-2018.1": glob([
            "com/google/idea/sdkcompat/java/**",
            "com/google/idea/sdkcompat/kotlin/**",
        ]),
        "intellij-ue-2018.1": glob([
            "com/google/idea/sdkcompat/java/**",
            "com/google/idea/sdkcompat/kotlin/**",
        ]),
        "android-studio-3.2": glob([
            "com/google/idea/sdkcompat/cidr/**",
            "com/google/idea/sdkcompat/java/**",
            "com/google/idea/sdkcompat/kotlin/**",
            "v2018_1_2/com/google/idea/sdkcompat/cidr/**",
        ]),
        "android-studio-3.3": glob([
            "com/google/idea/sdkcompat/cidr/**",
            "com/google/idea/sdkcompat/java/**",
            "com/google/idea/sdkcompat/kotlin/**",
            "v2018_1_3/com/google/idea/sdkcompat/cidr/**",
        ]),
    }),
    visibility = ["//sdkcompat:__pkg__"],
    deps = [
        "//intellij_platform_sdk:jsr305",
        "//intellij_platform_sdk:junit",
        "//intellij_platform_sdk:kotlin",
        "//intellij_platform_sdk:plugin_api",
        "//third_party/python",
    ],
)
