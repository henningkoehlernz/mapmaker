// external java file

package util;

import java.awt.event.*;

public class MouseLocator implements MouseListener, MouseMotionListener {

  private int last_x;
  private int last_y;
  
  public void mouseClicked(MouseEvent e) {
    setLocation(e.getX(),e.getY());
  }
  
  public void mouseEntered(MouseEvent e) {
    setLocation(e.getX(),e.getY());
  }
  
  public void mouseExited(MouseEvent e) {
    setLocation(e.getX(),e.getY());
  }
  
  public void mousePressed(MouseEvent e) {
    setLocation(e.getX(),e.getY());
  }
  
  public void mouseReleased(MouseEvent e) {
    setLocation(e.getX(),e.getY());
  }
  
  public void mouseDragged(MouseEvent e) {
    setLocation(e.getX(),e.getY());
  }
  
  public void mouseMoved(MouseEvent e) {
    setLocation(e.getX(),e.getY());
  }
  
  public void setLocation(int x, int y) {
    last_x = x;
    last_y = y;
  }
  
  public int getX() {
    return last_x;
  }
  
  public int getY() {
    return last_y;
  }
}
