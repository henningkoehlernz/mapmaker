package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/** a swing wrapper for AwtViews
 */
public class JView 
  extends JPanel 
  implements Observer {

  AwtView view;
  Dimension size;

  /** @param model should be the same Observerable as given
   * to the Model object
   * @param view the View object
   */
  public JView(Observable model, AwtView view) {
    this.view = view;
    model.addObserver(this);
    view.addListeners(this);
    size = view.getSize();
    setOpaque(true);
  } // JView

  public void paintComponent(Graphics g) {
    super.paintComponent(g); //paint background
    view.paint(g);
  } // paintComponent

  public void update(Observable o, Object arg) {
    Dimension newSize = view.getSize();
    if (!size.equals(newSize)) {
      size = newSize;
      // ensure that resizing takes place
      // even if enclosing component doesn't deem it neccessary
      revalidate();
      repaint();
    }
    else
      repaint();
    requestFocus();
  } // update

  public Dimension getPreferredSize() {
    return view.getSize();
  } // getPreferredSize

  public Dimension getMaximumSize() {
    return getPreferredSize();
  } // getMaximumSize

} // JView


