licenses(["notice"])  # Apache 2.0

java_library(
    name = "base",
    srcs = glob(["src/**/*.java"]),
    javacopts = ["-Xep:FutureReturnValueIgnored:OFF"],
    resources = glob(["resources/**/*"]),
    visibility = ["//visibility:public"],
    deps = [
        "//common/actionhelper",
        "//common/binaryhelper",
        "//common/concurrency",
        "//common/experiments",
        "//common/formatter",
        "//common/guava",
        "//common/transactions",
        "//intellij_platform_sdk:jsr305",
        "//intellij_platform_sdk:plugin_api",
        "//proto:proto_deps",
        "//sdkcompat",
        "//third_party/auto_value",
    ],
)

# self-contained label parsing API, exposed so other plugins can optionally depend on it
java_library(
    name = "label_api",
    srcs = [
        "src/com/google/idea/blaze/base/ideinfo/ArtifactLocation.java",
        "src/com/google/idea/blaze/base/model/primitives/InvalidTargetException.java",
        "src/com/google/idea/blaze/base/model/primitives/Kind.java",
        "src/com/google/idea/blaze/base/model/primitives/Label.java",
        "src/com/google/idea/blaze/base/model/primitives/LanguageClass.java",
        "src/com/google/idea/blaze/base/model/primitives/PackagePathValidator.java",
        "src/com/google/idea/blaze/base/model/primitives/RuleType.java",
        "src/com/google/idea/blaze/base/model/primitives/TargetExpression.java",
        "src/com/google/idea/blaze/base/model/primitives/TargetName.java",
        "src/com/google/idea/blaze/base/model/primitives/WildcardTargetPattern.java",
        "src/com/google/idea/blaze/base/model/primitives/WorkspacePath.java",
    ],
    neverlink = 1,
    deps = [
        "//intellij_platform_sdk:jsr305",
        "//intellij_platform_sdk:plugin_api",
    ],
)

# neverlink library exposed so other plugins can optionally depend on it
java_library(
    name = "workspace_path_resolver_api",
    srcs = [
        "src/com/google/idea/blaze/base/model/primitives/WorkspacePath.java",
        "src/com/google/idea/blaze/base/model/primitives/WorkspaceRoot.java",
        "src/com/google/idea/blaze/base/settings/BlazeImportSettings.java",
        "src/com/google/idea/blaze/base/settings/BlazeImportSettingsManager.java",
        "src/com/google/idea/blaze/base/settings/BuildSystem.java",
        "src/com/google/idea/blaze/base/sync/workspace/WorkspacePathResolver.java",
        "src/com/google/idea/blaze/base/sync/workspace/WorkspacePathResolverProvider.java",
    ],
    neverlink = 1,
    deps = [
        "//intellij_platform_sdk:jsr305",
        "//intellij_platform_sdk:plugin_api",
    ],
)

# neverlink library exposed so other plugins can optionally depend on it
java_library(
    name = "workspace_file_finder_api",
    srcs = [
        "src/com/google/idea/blaze/base/sync/projectview/WorkspaceFileFinder.java",
    ],
    neverlink = 1,
    deps = [
        "//intellij_platform_sdk:jsr305",
        "//intellij_platform_sdk:plugin_api",
    ],
)

# neverlink library exposed so other plugins can optionally depend on it
java_library(
    name = "event_logger_api",
    srcs = ["src/com/google/idea/blaze/base/logging/EventLogger.java"],
    neverlink = 1,
    deps = [
        "//intellij_platform_sdk:jsr305",
        "//intellij_platform_sdk:plugin_api",
    ],
)

# neverlink library exposed so other plugins can provide dependency information
java_library(
    name = "dep_finder_api",
    srcs = [
        "src/com/google/idea/blaze/base/dependencies/SourceToTargetProvider.java",
        "src/com/google/idea/blaze/base/dependencies/TargetInfo.java",
        "src/com/google/idea/blaze/base/dependencies/TestSize.java",
        "src/com/google/idea/blaze/base/run/SourceToTargetFinder.java",
        "src/com/google/idea/blaze/base/run/targetfinder/FuturesUtil.java",
        "src/com/google/idea/blaze/base/run/targetfinder/TargetFinder.java",
    ],
    neverlink = 1,
    deps = [
        ":label_api",
        "//intellij_platform_sdk:jsr305",
        "//intellij_platform_sdk:plugin_api",
    ],
)

filegroup(
    name = "plugin_xml",
    srcs = ["src/META-INF/blaze-base.xml"],
    visibility = ["//visibility:public"],
)

java_library(
    name = "unit_test_utils",
    testonly = 1,
    srcs = glob(["tests/utils/unit/**/*.java"]),
    visibility = ["//visibility:public"],
    deps = [
        "//base",
        "//intellij_platform_sdk:jsr305",
        "//intellij_platform_sdk:plugin_api_for_tests",
        "//sdkcompat",
        "@junit//jar",
    ],
)

java_library(
    name = "integration_test_utils",
    testonly = 1,
    srcs = glob(["tests/utils/integration/**/*.java"]),
    visibility = ["//visibility:public"],
    deps = [
        ":unit_test_utils",
        "//base",
        "//intellij_platform_sdk:jsr305",
        "//intellij_platform_sdk:plugin_api_for_tests",
        "//proto:proto_deps",
        "//testing:lib",
        "@junit//jar",
    ],
)

load(
    "//build_defs:build_defs.bzl",
    "stamped_plugin_xml",
    "intellij_plugin",
)

stamped_plugin_xml(
    name = "base_plugin_xml",
    plugin_id = "com.google.idea.blaze.base",
    plugin_name = "com.google.idea.blaze.base",
    plugin_xml = "plugin_xml",
)

intellij_plugin(
    name = "base_integration_test_plugin",
    testonly = 1,
    plugin_xml = ":base_plugin_xml",
    deps = [
        ":base",
    ],
)

load(
    "//testing:test_defs.bzl",
    "intellij_integration_test_suite",
    "intellij_unit_test_suite",
)

intellij_unit_test_suite(
    name = "unit_tests",
    srcs = glob(["tests/unittests/**/*.java"]),
    test_package_root = "com.google.idea.blaze.base",
    deps = [
        ":base",
        ":unit_test_utils",
        "//common/experiments",
        "//common/experiments:unit_test_utils",
        "//common/guava",
        "//intellij_platform_sdk:jsr305",
        "//intellij_platform_sdk:plugin_api_for_tests",
        "//proto:proto_deps",
        "@junit//jar",
    ],
)

intellij_integration_test_suite(
    name = "integration_tests",
    srcs = glob(["tests/integrationtests/**/*.java"]),
    required_plugins = "com.google.idea.blaze.base",
    test_package_root = "com.google.idea.blaze.base",
    runtime_deps = [
        ":base_integration_test_plugin",
    ],
    deps = [
        ":base",
        ":integration_test_utils",
        ":unit_test_utils",
        "//intellij_platform_sdk:jsr305",
        "//intellij_platform_sdk:plugin_api_for_tests",
        "//proto:proto_deps",
        "@junit//jar",
    ],
)
