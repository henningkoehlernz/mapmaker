package util;

import java.awt.*;

/** provides a global access point to the central frame
 */
public class MainFrame {
  
  private static Frame mainFrame;
  
  /** returns the central frame
   */
  public static Frame get() {
    return mainFrame;
  } // get

  /** sets the central frame
   * @param mainFrame the frame that will be returned via get()
   */
  public static void set(Frame mainFrame) {
    MainFrame.mainFrame = mainFrame;
  } // set

} // MainFrame
