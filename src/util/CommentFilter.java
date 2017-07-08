package util;

public class CommentFilter 
  extends TextFilter {

  protected String commentString;

  public CommentFilter(String commentString) {
    this.commentString = commentString;
  } // CommentFilter

  /** accepts a String if it does not start with the commentString 
   * given in Constructor
   */
  public boolean accept(String line) {
    return !line.startsWith(commentString);
  } // accept

} // CommentFilter
