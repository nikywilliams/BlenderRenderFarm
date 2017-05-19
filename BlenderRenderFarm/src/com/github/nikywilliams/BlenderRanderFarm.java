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
    
    parseConfigFile("config.conf");    
    
  }
  
  /**
   * 
   */
  public void parseConfigFile(String configFileName) {
    
  }
}
