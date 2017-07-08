package util;

import java.util.*;
import java.io.*;

public class FileUtil {

  /** returns the extension of fileName, which is the String consisting
   * of the characters below the last '.' in filename
   * if filename contains no '.' character, returns ""
   */
  public static String getExtension(String fileName) {
    int index = fileName.lastIndexOf('.');
    if (index == -1)
      return "";
    else
      return fileName.substring(index + 1);
  } // getExtension

  /** substitutes the Extension of fileName with newExtension;
   * if fileName has no extension, just adds newExtension
   */
  public static String substituteExtension(String fileName,
					   String newExtension) {
    int index = fileName.lastIndexOf('.');
    if (index == -1)
      return fileName + '.' + newExtension;
    else
      return fileName.substring(0, index + 1) + newExtension;
  } // substituteExtension

} // StringUtil
	



