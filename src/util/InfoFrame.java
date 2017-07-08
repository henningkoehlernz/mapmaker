package util;

import java.awt.*;
import javax.swing.*;
import java.io.*;

import util.*;

public class InfoFrame {

  /** displays the content of the given file in a new frame
   * @param title the title of the dialog
   * @param file the name of the text file to display
   */
  public static void showFile(String title, File file) {
    JFrame frame = new JFrame(title);
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
    // .. and stuff it into the frame
    frame.getContentPane().add(scrollText);
    frame.pack();
    //frame.setLocationRelativeTo(MainFrame.get());
    frame.show();
  } // showFile

} // InfoFrame
