package com.github.nikywilliams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Configuration {

  // Variables used to help define the configuration
  private String _blenderExecutableFileLocation;
  private String _blendFilesLocation;
  private String _imageOutputLocation;
  private String _imageOutputLocation;
  private String _threadParameter;
  private String _renderType;
  private final String _conifigFileName = "config.conf";
  
  /**
   * This will auto-read a configuration file...expected name is config.conf
   */
  public Configuration() {
 // Used for config file parsing        
    ArrayList<String> configLines = new ArrayList<String>();
    int lineCounter = 1;
    
    
    // Pull in config file
    try{      
      String configLine = "";
      BufferedReader reader = new BufferedReader(new FileReader(_configFileName));
      
      while ((configLine = reader.readLine()) != null) {
        configLines.add(configLine);
      }
      reader.close();
      
    }
    catch (Exception e) {
      System.out.println(e);
    }
    
    // Parse the config file for information    
    for (String configLine : configLines) {
      
      // Check for blank lines or comments
      if (configLine.trim().compareTo("") == 0 || configLine.getBytes()[0] == '#') 
       continue;
      
      // See which config information this is
      switch (lineCounter) {
        case 1:
          _blenderExecutableLocation = configLine;
          break;
        case 2:
          _blendFilesLocation = configLine;
          break;
        case 3:
          _imageOutputLocation = configLine;
          break;
        case 4:
          _threadParameter = configLine;
          break;
        case 5:
          _renderType = configLine;
          break;
      }
      
      lineCounter++;
    }   
  }
  
}
