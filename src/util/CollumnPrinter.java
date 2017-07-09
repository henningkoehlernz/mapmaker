package util;

import java.util.*;
import java.awt.*;

public class CollumnPrinter {

  public static final int STYLE_MATCH_WIDTH = 0;
  public static final int STYLE_MATCH_HEIGHT = 1;

  Vector<Text> collumns = new Vector<Text>();
  int collumnWidth;
  int lineHeight;

  public CollumnPrinter(int collumnWidth, int lineHeight) {
    this(null, collumnWidth, lineHeight);
  } // CollumnPrinter
  
  public CollumnPrinter(Text text, int collumnWidth, int lineHeight) {
    setText(text);
    this.collumnWidth = collumnWidth;
    this.lineHeight = lineHeight;
  } // CollumnPrinter

  /** sets the text to be printed
   * @param text may be null
   */
  public void setText(Text text) {
    collumns.clear();
    if (text != null)
      collumns.add(text);
  } // setText

  /** sets the width (in pixels) of a collumn
  public void setCollumnWidth(int collumnWidth) {
    this.collumnWidth = collumnWidth;
  } // setCollumnWidth

  /** sets the height (in pixels) of a text line
  public void setLineHeight(int lineHeight) {
    this.lineHeight = lineHeight;
  } // setLineHeight

  /** orders the layout of the set text in several collumns;
   * @param style the style of layout;
   * valid values are STYLE_MATCH_WIDTH and STYLE_MATCH_HEIGHT
   * @param targetSize the intended width or height for the entire
   * layout, depending on layoutStyle
   */ 
  public void layout(int style, int targetSize) {
    // stuff all back into one single text..
    Text text = new Text();
    for (int i = 0; i < collumns.size(); i++)
      text.append((Text)collumns.elementAt(i));
    collumns.clear();
    int totalLineNr = text.getLineCount();

    // get the maximum line number per collumn
    int maxLineNr;
    if (style == STYLE_MATCH_WIDTH)
      maxLineNr = lineNrMatchWidth(totalLineNr, targetSize);
    else if (style == STYLE_MATCH_HEIGHT)
      maxLineNr = lineNrMatchHeight(totalLineNr, targetSize);
    else
      throw new IllegalArgumentException("layout style not supported");

    // fill the collumns with lines
    if (totalLineNr == 0)
      return;
    Text collumn = new Text();
    collumns.add(collumn);
    for (int i = 0; i < totalLineNr; i++) {
      // ensure the current collumn can still take a line
      if (collumn.getLineCount() >= maxLineNr) {
	collumn = new Text();
	collumns.add(collumn);
      }
      collumn.append(text.getLine(i));
    }      
  } // layout

  /** returns the number of lines each collumn should have when
   * using the STYLE_MATCH_WIDTH layout
   * @param totalLineNr the total number of textlines to arrange
   * @param targetWidth the width to approximate (in pixels)
   */
  protected int lineNrMatchWidth(int totalLineNr, int targetWidth) {
    double collumnSpace = (double)targetWidth / getCollumnWidth();
    int collumnNr = (int)Math.max(1, Math.round(collumnSpace));
    int maxLineNr = (int)Math.ceil((double)totalLineNr / collumnNr);
    return maxLineNr;
  } // lineNrMatchWidth

  /** returns the number of lines each collumn should have when
   * using the STYLE_MATCH_HEIGHT layout
   * @param totalLineNr the total number of textlines to arrange
   * @param targetHeight the height to approximate (in pixels)
   */
  protected int lineNrMatchHeight(int totalLineNr, int targetHeight) {
    int maxLineNr = Math.max(1, targetHeight / getLineHeight());
    // while increase of lineNr by 1 reduces nr of collumn, do so
    int collumnNr = (int)Math.ceil((double)totalLineNr / maxLineNr);
    while (collumnNr > 1 &&
	   totalLineNr % maxLineNr <= (collumnNr - 1)) {
      maxLineNr++;
      collumnNr = (int)Math.ceil((double)totalLineNr / maxLineNr);
    }
    return maxLineNr;
  } // lineNrMatchHeight

  /** returns the size (in pixels) the text will be printed to
   */
  public Dimension getSize() {
    int maxLineNr = 0;
    for (int i = 0; i < collumns.size(); i++) {
      int lineNr = collumns.elementAt(i).getLineCount();
      maxLineNr = Math.max(maxLineNr, lineNr);
    }
    return new Dimension(collumns.size() * getCollumnWidth(),
			 maxLineNr * getLineHeight());
  } // getSize

  /** prints the text, using the given graphics Object
   */
  public void print(Graphics g) {
    Shape orgClip = g.getClip();
    for (int cNr = 0; cNr < collumns.size(); cNr++) {
      Text collumn = collumns.elementAt(cNr);
      for (int lNr = 0; lNr < collumn.getLineCount(); lNr++) {
	String line = collumn.getLine(lNr);
	int posX = cNr * getCollumnWidth();
	int posY = lNr * getLineHeight();
	// do the printing
	g.setClip(posX, posY, getCollumnWidth() - 5, getLineHeight());
	// freakin text-positioning..
	g.drawString(line, posX, posY + getLineHeight() / 2 + 3);
      }
    }
    g.setClip(orgClip);
  } // print

  /** returns the height of a line in pixels
   */
  protected int getLineHeight() {
    return lineHeight;
  } // getLineHeight

  /** returns the width of a line in pixles
   */
  protected int getCollumnWidth() {
    return collumnWidth;
  } // getCollumnWidth

} // CollumnPrinter



