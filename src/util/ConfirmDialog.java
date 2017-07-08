package util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/** displays a given component object in a dialog and lets the user
 * confirm his operations on the component;
 * is always modal
 */
public class ConfirmDialog
  extends JDialog 
  implements ActionListener {

  protected Component inputComponent;
  protected boolean confirmed = false;

  protected static String Button_Ok = "Ok";
  protected static String Button_Cancel = "Cancel";

  protected Component owner;

  public ConfirmDialog(String title) {
    super(MainFrame.get(), title, true);
    owner = MainFrame.get();
    initConfirmDialog();
  } // ConfirmDialog

  private void initConfirmDialog() {
    // set general parameters
    setResizable(false);
    getContentPane().setLayout(new BoxLayout(getContentPane(), 
					     BoxLayout.Y_AXIS));
    // create buttons
    JButton okButton = new JButton(Button_Ok);
    okButton.addActionListener(this);
    JButton cancelButton = new JButton(Button_Cancel);
    cancelButton.addActionListener(this);
    // create containers for grouping
    Container buttons = new JPanel();
    buttons.add (okButton);
    buttons.add(cancelButton);
    getContentPane().add(buttons);
  } // multiDialogInit

  /** returns true if the user pressed the OK-Button
   */
  public boolean confirmed() {
    return confirmed;
  } // confirmed

  /** sets the component to be displayed
   */
  public void setInputComponent(Component inputComponent) {
    if (this.inputComponent != null)
      throw new IllegalArgumentException("inputComponent already set");
    this.inputComponent = inputComponent;
    getContentPane().add(inputComponent, 0);
  } // setInputComponent
    
  /** returns the component set by setInputComponent
   */
  public Component getInputComponent() {
    return inputComponent;
  } // getInputComponent

  /** handles button-clicks
   */
  public void actionPerformed(ActionEvent e) {
    confirmed = Button_Ok.equals(e.getActionCommand());
    // close dialog
    dispose();
  } // actionPerformed

  public void pack(){
    super.pack();
    setLocationRelativeTo(owner);
  } // pack

} // ConfirmDialog
