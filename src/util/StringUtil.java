package util;

import java.util.*;
import java.io.*;

public class StringUtil {

  /** splits s into substrings of s that contain no seperator
   * character and are seperated by one or more seperator characters
   * @param seperator the seperator character
   */
  public static String[] splitString(String s, int seperator) {
    Vector<String> strings = new Vector<String>();
    int pos = 0;
    while (pos < s.length()) {
      int nextSep = s.indexOf(seperator, pos);
      if (nextSep == -1)
	nextSep = s.length();
      if (nextSep > pos)
	strings.addElement(s.substring(pos, nextSep));
      pos = nextSep + 1;
    } // while
    String[] buf = new String[strings.size()];
    strings.copyInto(buf);
    return buf;
  } // splitString

  /** splits s with the blank character as seperator
   */
  public static String[] splitString(String s) {
    return splitString(s, ' ');
  } // splitString

  /** word-wrappes a given String and returns it as Text
   * @param line the String to be wrapped
   * @param columns the maximum length of a wrapped line; 
   * must be positive
   */
  public static Text wordWrap(String line, int collumns) {
    String textLine = line;
    Text wrappedText = new Text();
    // wrap until nothing's left
    while (textLine.length() > 0) {
      // find position where to trunc
      int pos;
      if (textLine.length() <= collumns)
	pos = textLine.length();
      else {
	pos = collumns;
	while (pos > 0 && textLine.charAt(pos) != ' ')
	  pos--;
	if (pos == 0)
	  pos = collumns;
      }
      // trunc it
      wrappedText.append(textLine.substring(0, pos));
      if (pos + 1 < textLine.length())
	textLine = textLine.substring(pos + 1).trim();
      else
	textLine = "";
    }
    return wrappedText;
  } // wordWrap

  /** word-wrappes a given text as one paragraph and returns it
   * @param text the text to be wrapped
   * @param columns the maximum length of a wrapped line;
   * must be positive
   */
  public static Text wordWrap(Text text, int collumns) {
    if (collumns <= 0)
      throw new IllegalArgumentException("can't wrap to " + collumns + 
					 " collumns");
    // create one String containing all the text
    String textLine = "";
    for (int i = 0; i < text.getLineCount(); i++) {
      String nextLine = text.getLine(i).trim();
      if (i > 0 && nextLine.length() > 0)
	textLine += " ";
      textLine += nextLine;
    }
    return wordWrap(textLine, collumns);
  } // wordWrap

  /** word-wrappes each line of a given text and returns it;
   * @param text the text to be wrapped
   * @param columns the maximum length of a wrapped line;
   * must be positive
   */
  public static Text wordWrapLines(Text text, int collumns) {
    Text wrappedText = new Text();
    for (int i = 0; i < text.getLineCount(); i++)
      wrappedText.append(wordWrap(text.getLine(i), collumns));
    return wrappedText;
  } // wordWapLines

  /** if text contains a line starting with the first word
   * of newLine, this line (or the first line that matches)
   * gets replaced with newLine;
   * otherwise, newLine is appened to the text;
   * returns the modified text
   */
  public static Text replaceMatchingLine(Text text, String newLine) {
    Text newText = (Text)text.clone();
    String keyword = splitString(newLine)[0];
    int pos = firstMatch(text, keyword);
    if (pos == -1)
      newText.append(newLine);
    else
      newText.replaceLine(pos, newLine);
    return newText;
  } // replaceMatchingLine

  /** returns the index of the first line that starts with match;
   * if no such line found, returns -1
   * @param text the text to be searched
   * @param match the String to be matched
   */
  public static int firstMatch(Text text, String match) {
    for (int i = 0; i < text.getLineCount(); i++)
      if (text.getLine(i).startsWith(match))
	return i;
    return -1;
  } // firstMatch

} // StringUtil
	



