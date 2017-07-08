package util;

import java.awt.*;
import java.io.*;
import java.util.*;

/** updates the title of a frame to display a filename;
 * should be registered with a FileWrapper
 */
public class FileTitleHandler 
  implements Observer {

  Frame frame;
  String mainTitle;

  /** @param mainTitle the String to precede the filename
   * as title, e.g. "Editor" for "Editor: current.file" as title
   * @param frame the frame to update with the new title
   */
  public FileTitleHandler(Frame frame, String mainTitle) {
    this.frame = frame;
    this.mainTitle = mainTitle;
    update(null , null);
  } // FileTitleHandler

  /** updates the title to display the current file
   * @param arg must be the File object to display
   */
  public void update(Observable o, Object arg) {
    if (arg != null && arg instanceof File)
      frame.setTitle(mainTitle + ": " + ((File)arg).getName());
    else
      frame.setTitle(mainTitle);
  } // update

} // FileTitleHandler
