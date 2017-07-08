package util;

import javax.swing.*;

public class ButtonStateController
  implements StateController {

  AbstractButton button;

  public ButtonStateController(AbstractButton button) {
    this.button = button;
  } // ButtonStateController

  /** returns a Boolean object indicating wether the button
   * given in contructor is selected or not
   */
  public Object state() {
    return new Boolean(button.isSelected());
  } // state

} // ButtonStateController
    
