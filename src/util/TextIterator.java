package util;

public class TextIterator {

  Text text;
  int next;

  public TextIterator(Text text) {
    this.text = text;
    next = 0;
  } // TextIterator

  public boolean hasNext() {
    return next < text.getLineCount();
  } // hasNext

  public String next() {
    return text.getLine(next++);
  } // next

  /** returns the next line without changing the iterator's position
   */
  public String peek() {
    return text.getLine(next);
  } // peek

  /** jumps one line forward
   */
  public void jump() {
    next++;
  } // jump

} // TextIterator
