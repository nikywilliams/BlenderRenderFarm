package com.github.nikywilliams;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BlenderRanderFarm {

  private static Configuration _configuration = null;
  private static ProcessedFiles _processedFiles = null;

  /**
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

    // Parses configuration
    parseConfiguration();

    // Parses already processed blend files
    parseProcessedFiles();

    // Begins watching for things
    startWatchdogLoop();
  }

  /**
   * Parses the config files for information
   */
  private static void parseConfiguration() {
    _configuration = new Configuration();
  }

  /**
   * This will pull the list of already processed blend files so it won't try to process them again
   */
  private static void parseProcessedFiles() {
    _processedFiles = new ProcessedFiles();
  }

  /**
   * Application loop
   */
  private static void startWatchdogLoop() {
    // Just loop until program is terminated
    while (true) {
      try {
        // Hold list of blend files that will be processed
        ArrayList<String> blendFilesList = new ArrayList<String>();

        if (blendFilesList.size() == 0) {
          // See if there are any blend files to process
          blendFilesList = getBlendFilesList();

          // Now process the files
          for (String blendFile : blendFilesList) {
            System.out.println("Processing: " + blendFile);

            // Process the blend file here
            processBlenderFile(blendFile);
          }
          // Clear the list as it should have all been processed by now
          blendFilesList.clear();
        }
        // Waits a bit before continuing the loop
        Thread.sleep(3000);

        // Refresh our list as files may have been processed
        _processedFiles.refreshList();
      }
      catch (Exception e) {
        System.out.println(e);
      }
    }
  }

  /**
   * Pulls a list of the blend files available on the target
   *
   * @return
   */
  private static ArrayList<String> getBlendFilesList() {
    // try to read the directory
    ArrayList<String> blendFilesList = new ArrayList<String>();

    try {
      // First get a list of files here
      File directory = new File(_configuration.getBlendFilesLocation());
      File[] fileList = directory.listFiles();

      // Now parse it, only getting the blend files that haven't already been processed
      for (int i = 0; i < fileList.length; i++) {
        if (fileList[i].isFile() == true && fileList[i].getName().contains(".blend") && _processedFiles.hasFileAlreadyBeenProcessed(fileList[i].getName()) == false) {
          blendFilesList.add(fileList[i].getName());
        }
      }
    }
    catch (Exception e) {
      System.out.println(e);
    }

    return (blendFilesList);
  }

  /**
   * Process the blend file here
   * @param blendFile
   */
  private static void processBlenderFile(String blendFile) {

    // Build the command line needed to start blender up and execute blender
    /*String commandLine = _configuration.getBlenderExecutableLocation() + "blender.exe"
            + " -b " + _configuration.getBlendFilesLocation() + blendFile
            + " -o " + _configuration.getOutputLocation() + blendFile + "\\IMG_####"
            + " " + _configuration.getThreads()
            + " " + _configuration.getRenderType();*/
    //System.out.println(commandLine);

    try {
      String[] commands = {_configuration.getBlenderExecutableLocation() + "blender.exe",
              "-b",
              _configuration.getBlendFilesLocation() + blendFile,
              "-o",
              _configuration.getOutputLocation() + blendFile + "\\IMG_####",
              "-t",
              _configuration.getThreads(),
              "-a" };
      ProcessBuilder pb = new ProcessBuilder(commands);

      System.out.println(pb.command().toString());
      Process p = pb.start();
      InputStream is = p.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      String line;

      System.out.println("Blender is running...");

      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }

      // Once the processing is done and check for success, make sure the file is now in our "processed" list
      _processedFiles.addProcessedFile(blendFile);
    }
    catch (Exception e) {
      System.out.println(e);
    }
  }
}
