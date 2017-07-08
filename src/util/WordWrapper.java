package util;

import java.util.*;
import java.io.*;

/* class to wrap Text to a maximum line length such that words aren't
 * split in the middle
 */
public class WordWrapper {

  protected int wrapLength;
  protected char seperator = ' ';

  /* @param wrapLength the length the lines should be wrapped to
   */
  public WordWrapper(int wrapLength) {
    this.wrapLength = wrapLength;
  }

  /* @param wrapLength the length the lines should be wrapped to
   * @param seperator the character that seperates words
   */
  public WordWrapper(int wrapLength, char seperator) {
    this.wrapLength = wrapLength;
  }

  /* returns the weight for a character for weighted wrapping
   * return value may be negative
   */
  protected int charWeight(char ch) {
    return 1;
  }

  /* returns the position of the first character that should be wrapped
   * into a new line; if s has weight <= wrapLength, s.length() will be
   * returned
   */
  protected int wrapPos(String s) {
    int length = s.length();
    if (length == 0)
      return 0;

    int pos = 0;
    int weight = 0; // weight of s before (excluding) pos

    // go forward until weight gets too high
    while (pos < length && weight <= wrapLength)
      weight += charWeight(s.charAt(pos++));
    if (weight <= wrapLength)
      return pos;

    // weight is too high, go one back unless the first character was already too heavy
    if (pos > 1)
      pos--;

    // weight is ok - now go back until a seperator character is found
    int sepPos = pos;
    while (sepPos > 0 && s.charAt(sepPos) != seperator)
      sepPos--;
    if (sepPos <= 0)
      // can't seperate without word splitting
      return pos;
    else
      return sepPos;
  }

  /* wraps a single string
   */
  public Text wrap(String s) {
    Text wrappedText = new Text();
    // wrap until nothing's left
    while (s.length() > 0) {
      int wrapPos = wrapPos(s);
      wrappedText.append(s.substring(0, wrapPos));
      s = s.substring(wrapPos);
      // eliminate leading seperators
      while (s.length() > 0 && s.charAt(0) == seperator)
	s = s.substring(1);
    }
    return wrappedText;
  }

  /* wraps a text line by line
   */
  public Text wrap(Text text) {
    Text wrappedText = new Text();
    for (int i = 0; i < text.getLineCount(); i++)
      wrappedText.append(wrap(text.getLine(i)));
    return wrappedText;
  }

  /* combines the lines of text to one string, placing a seperator
   * between two lines
   */
  public String unwrap(Text text) {
    int lines = text.getLineCount();
    if (lines == 0)
      return "";
    String s = text.getLine(0);
    for (int i = 1; i < lines; i++)
      s += seperator + text.getLine(i);
    return s;
  }

}
