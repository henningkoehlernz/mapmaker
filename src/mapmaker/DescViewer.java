package mapmaker;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;

import util.*;

public class DescViewer 
  extends JTextArea 
  implements Observer, DocumentListener {

  CMap map;
  DescObj descObj = null;
  private boolean handleDocuUpdate = true;

  public DescViewer(CMap map) {
    this.map = map;
    //map.addObserver(this);
    setLineWrap(true);
    setWrapStyleWord(true);
    setEditable(true);
    //setColumns(80);
    setRows(5);
    // add self as DocumentListener to automatically update room of changes
    getDocument().addDocumentListener(this);
    update(null, null);
  } // DescViewer

  /** returns its contents as an ARRAY of strings rather than
   * a single string
   * returns null if empty
   */
  protected Text getTextAsText() {
    int size = getLineCount();
    if (size == 0)
      return null;
    Text text = new Text();
    for (int i = 0; i < size; i++) {
      try {
	text.append(SwingUtil.getLine(this, i));
      } catch (BadLocationException e) {}
    }
    return text;
  } // getTextAsText

  /** loads its text from the room stored in selected
   * if selected == null clears text
   */
  protected void loadText() {
    // don't update on document changes, as changes done
    // ARE already updated!
    boolean oldPolicy = handleDocuUpdate;
    handleDocuUpdate = false;
    setText(null);
    Text desc = null;
    // load text from map if no room selected
    if (descObj != null)
      desc = descObj.getDesc();
    if (desc != null)
      SwingUtil.appendText(this, desc);
    handleDocuUpdate = oldPolicy;
  } // loadText

  /** saves the text back to descObj
   */
  protected void saveTextBack() {
    Text desc = getTextAsText();
    if (descObj != null)
      descObj.setDesc(desc);
  } // saveTextBack

  /** makes sure that the previous selected room recieves its
   * description and that the desription of the newly selected
   * room gets displayed
   */
  public void update(Observable o, Object arg) {
    // choose descObj
    Room selected = map.getSelected();
    if (selected == null)
      descObj = map;
    else
      if (selected instanceof DescObj)
	descObj = (DescObj)selected;
      else
	descObj = null;
    // do update
    setEditable(descObj != null);
    loadText();
  } // update

  public void changedUpdate(DocumentEvent e) {
    if (handleDocuUpdate)
      saveTextBack();
  } // changedUpdate

  public void insertUpdate(DocumentEvent e) {
    if (handleDocuUpdate)
      saveTextBack();
  } // insertUpdate

  public void removeUpdate(DocumentEvent e) {
    if (handleDocuUpdate)
      saveTextBack();
  } // removeUpdate

  public Dimension getPreferredSize() {
    // returns 0 as preferred width, so it won't be larger
    // than the mapView
    Dimension size = super.getPreferredSize();
    size.width = 0;
    return size;
  } // getPreferredSize

} // DescViewer
	       




