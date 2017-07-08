package util;

import java.awt.*;
import javax.swing.*;
import java.io.*;

import util.*;

public class InfoDialog {

  /** uses MainFrame.get() as dialog owner
   */
  public static void showFile(String title, File file) {
    showFile(MainFrame.get(), title, file);
  } // showFile

  /** displays the content of the given file in a non-modal dialog
   * @param owner the dialog's parent
   * @param title the title of the dialog
   * @param file the name of the text file to display
   */
  public static void showFile(Frame owner, 
			      String title,
			      File file) {
    JDialog dialog = new JDialog(owner, title);
    JTextArea textArea = new JTextArea();
    // load the text from file
    SwingUtil.appendText(textArea, TextFileHandler.loadText(file));

    textArea.setEditable(false);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    textArea.setColumns(40);
    textArea.setRows(20);
    // embed the text display in a scroll pane ..
    JScrollPane scrollText = new JScrollPane(textArea);
    // .. and stuff it into the dialog
    dialog.getContentPane().add(scrollText);
    dialog.pack();
    dialog.setLocationRelativeTo(owner);
    dialog.show();
  } // showFile

} // InfoDialog
    
