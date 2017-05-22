package com.github.nikywilliams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ProcessedFiles {

  // Used for seeing what files have already been processed
  private ArrayList<String> _processedFilesNameList = new ArrayList<String>();
  private final String _processedFilesName = "processed-files.prc";

  /**
   * This will auto-read a processed file list...expected name is processed-files.prc
   */
  public ProcessedFiles() {
   refreshList();
  }

  /**
   * Checks to see if string is in the processed files list
   */
  public boolean hasFileAlreadyBeenProcessed(String fileName) {
    return (_processedFilesNameList.contains(fileName));
  }

  /**
   * Refreshes the list
   */
  public void refreshList() {
    // Pull in list of files
    try {
      String fileNameLine;

      // Check to see if file exists, if not, create it
      File fileObject = new File(_processedFilesName);
      if (fileObject.exists() == false)
        fileObject.createNewFile();

      // Now read the file
      BufferedReader reader = new BufferedReader(new FileReader(_processedFilesName));

      _processedFilesNameList.clear();
      while ((fileNameLine = reader.readLine()) != null) {
        _processedFilesNameList.add(fileNameLine);
      }
      reader.close();

    }
    catch (Exception e) {
      System.out.println(e);
    }
  }

  /**
   * Adds the file to the list of processed files
   * @param processedFileName
   */
  public void addProcessedFile(String processedFileName) {
    try {
      // Now read the file
      BufferedWriter writer = new BufferedWriter(new FileWriter(_processedFilesName, true));
      writer.append(processedFileName + "\r\n");
      writer.close();
    }
    catch (Exception e) {
      System.out.println(e);
    }
  }
}
