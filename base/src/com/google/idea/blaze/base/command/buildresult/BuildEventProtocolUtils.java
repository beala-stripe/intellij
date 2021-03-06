/*
 * Copyright 2017 The Bazel Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.idea.blaze.base.command.buildresult;

import com.google.common.collect.ImmutableList;
import java.io.File;
import java.util.UUID;

/**
 * Utility methods for accessing blaze build event data via the build event protocol (BEP for
 * short).
 */
public final class BuildEventProtocolUtils {

  // Instructs BEP to use local file paths (file://...) rather than objfs blobids.
  private static final String LOCAL_FILE_PATHS =
      "--noexperimental_build_event_binary_file_path_conversion";

  private BuildEventProtocolUtils() {}

  /**
   * Creates a temporary output file to write the BEP data to. Callers are responsible for deleting
   * this file after use.
   */
  public static File createTempOutputFile() {
    File tempDir = new File(System.getProperty("java.io.tmpdir"));
    String suffix = UUID.randomUUID().toString();
    String fileName = "intellij-bep-" + suffix;
    File tempFile = new File(tempDir, fileName);
    // Callers should delete this file immediately after use. Add a shutdown hook as well, in case
    // the application exits before then.
    tempFile.deleteOnExit();
    return tempFile;
  }

  /** Returns a build flag instructing blaze to write build events to the given output file. */
  public static ImmutableList<String> getBuildFlags(File outputFile) {
    return ImmutableList.of(
        "--experimental_build_event_binary_file=" + outputFile.getPath(), LOCAL_FILE_PATHS);
  }
}
