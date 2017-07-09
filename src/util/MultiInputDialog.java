package util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/** prompts the user to input several values;
 * is always modal
 */
public class MultiInputDialog 
  extends ConfirmDialog {

  protected Vector<JTextField> inputList = new Vector<JTextField>();
  protected Panel labelPanel = new Panel();
  protected Panel inputPanel = new Panel();

  protected int inputLength = 10;

  public MultiInputDialog(String title) {
    super(title);
    initMultiInputDialog();
  } // MultiInputDialog

  private void initMultiInputDialog() {
    // set layout to group all labels and all input lines top-to-bottom
    // group labels + input lines left-to-right
    labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
    // create containers for grouping
    JPanel labelsAndInput = new JPanel();
    labelsAndInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
    labelsAndInput.setLayout(new BoxLayout(labelsAndInput, 
					   BoxLayout.X_AXIS));
    labelsAndInput.add(labelPanel);
    labelsAndInput.add(inputPanel);
    setInputComponent(labelsAndInput);
  } // multiDialogInit

  /** sets the length of the input-lines added afterwards
   */
  public void setInputLength(int inputLength) {
    this.inputLength = inputLength;
  } // setInputLength

  /** adds an input-line to the dialog
   * @param label will be displayed to describe the input
   * @param init the default value of the input-lin
   */
  public void addInput(String label, String init) {
    // add label
    labelPanel.add(new JLabel(label, JLabel.RIGHT));
    // add input-line
    JTextField newInput = new JTextField(init, inputLength);
    inputList.addElement(newInput);
    inputPanel.add(newInput);
  } // addInput
    
  /** returns the user input 
   * @param lineNr the number of the input line, starting at 0
   */
  public String getInput(int lineNr) {
    return inputList.elementAt(lineNr).getText();
  } // getInput

} // MultiInputDialog

