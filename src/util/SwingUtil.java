package util;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class SwingUtil {

  /** appends a line to textArea and adds a line-feed afterwards
   */
  public static void appendLine(JTextArea textArea, String line, boolean addEol) {
    if (addEol)
      textArea.append(line + (char)10);
    else
      textArea.append(line);
  } // appendLine

  /** appends a text to textArea
   */
  public static void appendText(JTextArea textArea, Text text) {
    if (text == null)
      return;
    int length = text.getLineCount();
    for (int i = 0; i < length; i++)
      appendLine(textArea, text.getLine(i), i != length-1);
  } // appendText

  /** returns a specified line in textArea
   * @param lineNr the index of the line to be returned
   */
  public static String getLine(JTextArea textArea, int lineNr) 
    throws BadLocationException {
    int start = textArea.getLineStartOffset(lineNr);
    int end = textArea.getLineEndOffset(lineNr);
    // stupid JTextArea => returns eol with endOffset
    String line = textArea.getText(start, (end - start));
    int length = line.length();
    if (length > 0 && line.charAt(length - 1) == (char)10)
      line = line.substring(0, length - 1);
    return line;
  } // getLine

} // SwingUtil
