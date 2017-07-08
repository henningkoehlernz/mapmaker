package util;

import java.awt.*;
import java.awt.event.*;

/** converts mouse-drags with a distance below a tolerance value
 * into mouse-clicks
 */
public class ClickToleranceFilter
  implements MouseListener {

  MouseListener ml;
  int tol;
  Point pressedPos;

  /** @param ml the MouseListener to recieve the events
   */
  public ClickToleranceFilter(MouseListener ml) {
    this(ml, 1);
  } // ClickToleranceFilter

  /** @param ml the MouseListener to recieve the events
   * @param tol the tolerance value for click-conversion
   */
  public ClickToleranceFilter(MouseListener ml, 
			      int tol) {
    this.ml = ml;
    this.tol = tol;
  } // ClickToleranceFilter

  public void mouseClicked(MouseEvent e) {
    // ignore
  } // mouseClicked

  public void mouseEntered(MouseEvent e) {
    ml.mouseEntered(e);
  } // mouseEntered

  public void mouseExited(MouseEvent e) {
    ml.mouseExited(e);
  } // mouseExited

  public void mousePressed(MouseEvent e) {
    pressedPos = e.getPoint();
    ml.mousePressed(e);
  } // mousePressed

  public void mouseReleased(MouseEvent e) {
    Point pos = e.getPoint();
    if (pressedPos != null &&
	Math.abs(pos.x - pressedPos.x) <= tol &&
	Math.abs(pos.y - pressedPos.y) <= tol)
      ml.mouseClicked(e);
    ml.mouseReleased(e);
  } // mouseReleased

} // ClickToleranceFilter
    


