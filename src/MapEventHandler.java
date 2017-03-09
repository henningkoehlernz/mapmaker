package mapmaker;

import java.awt.*;
import java.awt.event.*;

import util.*;

public abstract class MapEventHandler {

  // mouse events
  public void roomClicked(MouseEvent e, Point pos) {}
  public void roomDragged(MouseEvent e, Point pressed, Point released) {}
  public void linkClicked(MouseEvent e, Point room, int exit) {}
  public void linkDragged(MouseEvent e, Point room, 
			  int pressed, int released) {}

  // keyboard events
  public void keyTypedOnRoom(KeyEvent e, Point pos) {}
  public void keyTypedOnLink(KeyEvent e, Point room, int exit) {}
  
  /** adds a new StateController that the MapEventHandler can use
   * to determin its state
   * @param index can be used by the MapEventHandler to determin
   * which state the given StateController controls
   */
  public abstract void addStateController(StateController state,
					  Object index);

} // MapEventHandler

  
