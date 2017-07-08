package util;

import javax.swing.*;

/** like MultiInputDialog but also displays a checkbox
 */
public class CheckBoxDialog
  extends MultiInputDialog {

  protected JCheckBox checkBox = new JCheckBox();

  public CheckBoxDialog(String title) {
    super(title);
    checkBoxDialogInit();
  } // CheckBoxDialog

  private void checkBoxDialogInit() {
    // add checkBox after the labels and input field
    getContentPane().add(checkBox, 1);
  } // checkBoxDialogInit

  /** returns the checkBox object of the dialog
   */
  public JCheckBox getCheckBox() {
    return checkBox;
  } // getCheckBox

} // CheckBoxDialog


