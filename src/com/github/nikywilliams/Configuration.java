package com.github.nikywilliams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Configuration {

  // Variables used to help define the configuration
  private String _blenderExecutableLocation;
  private String _blendFilesLocation;
  private String _outputLocation;
  private String _threads;
  private String _renderType;
  private final String _configFileName = "config.conf";

  /**
   * This will auto-read a configuration file...expected name is config.conf
   */
  public Configuration() {
    // Used for config file parsing
    ArrayList<String> configLines = new ArrayList<String>();

    String currentDirectory;
    File file = new File(".");
    currentDirectory = file.getAbsolutePath();
    System.out.println("Current working directory : "+currentDirectory);

    // Pull in config file
    try {
      String configLine;
      BufferedReader reader = new BufferedReader(new FileReader(_configFileName));

      while ((configLine = reader.readLine()) != null) {
        configLines.add(configLine);
      }
      reader.close();

    } catch (Exception e) {
      System.out.println(e);
    }

    // Parse the config file for information
    for (String configLine : configLines) {

      // Check for blank lines or comments, and skip if we have that
      if (configLine.trim().compareTo("") == 0 || configLine.getBytes()[0] == '#')
        continue;

      // See which config information this is
      if (configLine.compareTo("[BLENDER_EXECUTABLE]") == 0)
        _blenderExecutableLocation = configLine;
      else if (configLine.compareTo("[BLEND_FILES_LOCATION]") == 0)
        _blendFilesLocation = configLine;
      else if (configLine.compareTo("[OUTPUT_LOCATION]") == 0)
        _outputLocation = configLine;
      else if (configLine.compareTo("[THREADS]") == 0)
        _threads = configLine;
      else if (configLine.compareTo("[RENDER_TYPE]") == 0)
        _renderType = configLine;
    }
  }
}
