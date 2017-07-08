package util;

import java.io.*;

public class TextFileHandler {

  /** saves a text to a file
   * @param text the text to be saved
   * @param file the file to save to
   */
  public static void saveText(Text text, File file) {
    try {
      BufferedWriter out = 
	new BufferedWriter(new FileWriter(file));
      for (int i = 0; i < text.getLineCount(); i++) {
	out.write(text.getLine(i));
	out.newLine();
      }
      out.close();
    }
    catch (IOException e) {
      System.out.println("Error saving file: " + e);
    }
  } // saveText
 
 /** loads a text from a file
   * @param file the file to load from
   */
  public static Text loadText(File file) {
    Text inData = new Text();
    try {
      BufferedReader inStream = 
	new BufferedReader(new FileReader(file));
      String line;
      while ((line = inStream.readLine()) != null)
	inData.append(line);
      inStream.close();
    }
    catch (IOException e) {
      System.out.println("Error loading file: " + e);
      return null;
    }
    return inData;
  } // loadText

} // TextFileHandler
