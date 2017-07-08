package util;

import java.util.*;
import java.io.*;
import javax.swing.*;

/** updates a filechooser with new file selections;
 * best used in combination with FileWrapper
 */
public class FileChooserSynchronizer
  implements Observer {

  private JFileChooser fc;

  public FileChooserSynchronizer(JFileChooser fc) {
    this.fc = fc;
  } // FileChooserSynchronizer

  /** @param arg must be of type File or null
   */
  public void update(Observable obs, Object arg) {
    if (arg == null)
      fc.setSelectedFile(null);
    else
      fc.setSelectedFile((File)arg);
  } // update

} // FileChooserSynchronizer
