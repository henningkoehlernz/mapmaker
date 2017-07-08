package util;

public abstract class TextFilter {

  public abstract boolean accept(String line);

  /** returns a text containing those lines of the given text
   * that are accepted by the filter;
   * @param text if null, returns an empty Text
   */
  public Text filter(Text text) {
    Text filtered = new Text();
    if (text == null)
      return filtered;
    for (int i = 0; i < text.getLineCount(); i++) {
      String line = text.getLine(i);
      if (accept(line))
	filtered.append(line);
    }
    return filtered;
  } // filter

} // TextFilter
