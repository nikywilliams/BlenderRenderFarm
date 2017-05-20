package com.github.nikywilliams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

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
    ArrayList<String> configLinesList = new ArrayList<String>();

    // Pull in config file
    try {
      String configLine;
      BufferedReader reader = new BufferedReader(new FileReader(_configFileName));

      while ((configLine = reader.readLine()) != null) {
        configLinesList.add(configLine);
      }
      reader.close();

    }
    catch (Exception e) {
      System.out.println(e);
    }

    // Parse the config file for information
    for (int i = 0; i < configLinesList.size(); i++) {

      // Check for blank lines or comments, and skip if we have that
      if (configLinesList.get(i).trim().compareTo("") == 0 || configLinesList.get(i).getBytes()[0] == '#')
        continue;

      // See which config information this is
      if (configLinesList.get(i).compareTo("[BLENDER_EXECUTABLE]") == 0)
        _blenderExecutableLocation = configLinesList.get(i + 1);
      else if (configLinesList.get(i).compareTo("[BLEND_FILES_LOCATION]") == 0)
        _blendFilesLocation = configLinesList.get(i + 1);
      else if (configLinesList.get(i).compareTo("[OUTPUT_LOCATION]") == 0)
        _outputLocation = configLinesList.get(i + 1);
      else if (configLinesList.get(i).compareTo("[THREADS]") == 0)
        _threads = configLinesList.get(i + 1);
      else if (configLinesList.get(i).compareTo("[RENDER_TYPE]") == 0)
        _renderType = configLinesList.get(i + 1);
    }

    System.out.println(_blenderExecutableLocation + "\n" + _blendFilesLocation + "\n" + _outputLocation + "\n" + _threads + "\n" + _renderType);
  }

  /**
   * Getters/Setters
   */
  public String getBlenderExecutableLocation() {
    return (_blenderExecutableLocation);
  }

  public String getBlendFilesLocation() {
    return (_blendFilesLocation);
  }

  public String getOutputLocation() {
    return (_outputLocation);
  }

  public String getThreads() {
    return (_threads);
  }

  public String getRenderType() {
    return (_renderType);
  }
}
