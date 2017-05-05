package com.github.nikywilliams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class BlenderRanderFarm {

  /**
   * 
   * @param args
   */
  public static void main(String[] args) {
    
    // Runs in loop waiting for new file to be dropped into remote location
    // Keeps local file of projects started and projects completed
    //  i.e. list.wip or list.cmp    
    // Pull list of directory file names in remote location and compares to cmp list. If not in there, pull it and stick in wip
    // wip must be empty before pulling another one
    // Ignore # in config file as "comment"
    
    // Read from config file for
    // blender executable location on local box
    // blender .blend files location on remote box
    // output location of images for animation
    // animation or frame parameter i.e. -a or -f 200
    
    // Later version
    // FFMPEG
    // Optional audio param
    // Same destination as above
    // Optional kbps for audio
    // Screen aspect ratio (1280 default)
    // vbps param
    
    // Used to hold the lines of the config file
    final String fileName = "config.conf";
    ArrayList<String> configLines = new ArrayList<String>();
    int lineCounter = 1;
    String blenderExecutableLocation = "";
    String blendFilesLocation = "";
    String imageOutputLocation = "";
    String threadParameter = "";
    String renderType = "";
    
    // Pull in config file
    try{      
      String configLine = "";
      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      
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
          blenderExecutableLocation = configLine;
          break;
        case 2:
          blendFilesLocation = configLine;
          break;
        case 3:
          imageOutputLocation = configLine;
          break;
        case 4:
          threadParameter = configLine;
          break;
        case 5:
          renderType = configLine;
          break;
      }
      
      lineCounter++;
    }
    
  }
}
