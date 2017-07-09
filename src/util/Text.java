package util;

import java.util.*;

public class Text 
implements Cloneable {

  private Vector<String> lines;

  public Text() {
    lines = new Vector<String>();
  } // Text

  /** returns the number of lines in text
   */
  public int getLineCount() {
    return lines.size();
  } // getLineCount

  /** appends a String as a new line to the end of the Text
   * @param s the line to append
   */
  public void append(String s) {
    lines.addElement(s);
  } // append

  /** appends the given text to the end
   * @param text the text to append
   */
  public void append(Text text) {
    // use lineNr in case this == text
    int lineNr = text.getLineCount();
    for (int i = 0; i < lineNr; i++)
      append(text.getLine(i));
  } // append

  /** returns the line of the given index
   * @param index must be within 0 and (getLineCount() - 1)
   */
  public String getLine(int index) {
    return lines.elementAt(index);
  } // getLine

  /** removes the line of the given index from the text, then
   * returns itself
   * @param index  0 <= index < getLineCount() must be true
   */
  public Text removeLine(int index) {
    lines.remove(index);
    return this;
  } // getLine

  /** removes all empty lines at the end, then returns itself
   */
  public Text trim() {
    int pos = getLineCount() - 1;
    while (pos >= 0 &&
	   getLine(pos).length() == 0)
      removeLine(pos--);
    return this;
  } // trim

  /** replaces the line at given index with newLine String
   */
  public void replaceLine(int index, String newLine) {
    lines.set(index, newLine);
  } // replaceLine

  /** removes all lines from the text
   */
  public void clear() {
    lines.clear();
  } // clear
    
  /** returns wether a String is contained withing the text;
   * ignores case
   * @param s the String to search for
   */
  public boolean contains(String s) {
    // even an empty text contains an empty String..
    if (s.length() == 0)
      return true;
    int lineCount = getLineCount();
    if (lineCount == 0)
      return false;
    // append all lines with a blank in between
    String lineSum = getLine(0);;
    for (int i = 1; i < lineCount; i++)
      lineSum += " " + getLine(i);
    int lastTestIndex = lineSum.length() - s.length();
    int testLength = s.length();
    for (int i = 0; i <= lastTestIndex; i++)
      if (lineSum.substring(i, i + testLength).equalsIgnoreCase(s))
	return true;
    return false;
  } // contains

  public Object clone() {
    Text clone = new Text();
    clone.lines = new Vector<String>(lines);
    return clone;
  } // clone

} // Text
