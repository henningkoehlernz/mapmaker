package util;

import javax.swing.*;

public class ButtonGroupStateController
  implements StateController {

  private ButtonGroup buttonGroup;

  public ButtonGroupStateController(ButtonGroup buttonGroup) {
    this.buttonGroup = buttonGroup;
  } // ButtonGroupStateController

  /** returns the currently selected button in the group
   */
  public Object state() {
    return buttonGroup.getSelection();
  } // state

} // ButtonGroupStateController
