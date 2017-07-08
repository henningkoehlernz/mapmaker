package util;

public class StringParser {
  // offers some basic string operations

  static boolean isDigit(char c) {
    return ('0' <= c) && (c <= '9');
  } // isDigit

  /* return true if s starts with a digit or a '-' followed
   * by a digit
   */
  static boolean startsWithDigitOrMinus(String s) {
    if (s.length() == 0)
      return false;
    if (isDigit(s.charAt(0)))
      return true;
    if (s.length() > 1)
      return s.charAt(0) == '-' && isDigit(s.charAt(1));
    return false;
  } // startsWithDigit;

  /* returns the largest part of s that starts with a digit
   * or with '-' followed by a digit
   */
  static String truncNonDigits (String s) {
    int pos = 0;
    while (pos < s.length() && !startsWithDigitOrMinus(s.substring(pos)))
      pos++;
    return s.substring(pos);
  } // truncNonDigits

  /* returns the largest part of s that starts with non-digit
   * and isn't equal to s if s starts with '-' followed by a digit
   */
  static String truncDigits(String s) {
    int pos = 0;

    while (startsWithDigitOrMinus(s.substring(pos)))
      pos++;
    return s.substring(pos);
  } // truncNonDigits

  /*
  static String getNonDigits(String s) {
    // returns the largest prefix of s which contains no digits
    int pos = 0;
    while (pos < s.length() && !isDigit(s.charAt(pos))) {
      pos++;
    }
    return s.substring(0, pos);
  } // getNonDigits
  */

  static int getFirstInt(String s) {
    // extracts first int value from s
    String opStr = truncNonDigits(s);
    int pos = 1; // for possible '-'
    while (pos < s.length() && isDigit(s.charAt(pos))) {
      pos++;
    }
    return Integer.valueOf(s.substring(0, pos)).intValue();
  } // getFirstInt

  public static int[] extractIntArray(String s) {
    // returns array containing all integer values
    // contained in s
    // e.g. "a2b45cd-81+0" -> [2,45,-81,0]

    int[] buffer = new int[(s.length()+1)/2];
    int nr = 0;
    s = truncNonDigits(s);
    while (s.length() > 0) {
      buffer[nr++] = getFirstInt(s);
      s = truncNonDigits(truncDigits(s));
    }
    // norm result to probber length
    int[] a = new int[nr];
    for (int i=0; i < nr; i++)
      a[i] = buffer[i];
    return a;
  } // extractIntArray     
      
  static String concatArgs(String[] args) {
    // returns concatination of all Strings contained in args
    String argSum = "";
    for (int i=0; i < args.length; i++)
      argSum += args[i];
    return argSum;
  } // concatArgs

  static int[] argsToInt(String[] args) {
    return extractIntArray(concatArgs(args));
  } // argsToInt

} // StringParser


