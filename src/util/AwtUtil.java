package util;

import java.awt.*;

public class AwtUtil {

  /** returns wether the coordinates of p are contained
   * in the area of the given dimension; specifically
   * if (0 <= p.x < dim.width) && (0 <= p.y < dim.heigth)
   */
  public static boolean contains(Dimension dim, Point p) {
    return 
      (0 <= p.x) && (p.x < dim.width) &&
      (0 <= p.y) && (p.y < dim.height);
  } // contains

  /** returns the int-value closest to the give value
   * that lies within the bonds given by min and max
   * @param value value to approximate
   * @param min lower bond, inclusive
   * @param max upper bond, inclusive
   */
  public static int bond(int value, int min, int max) {
    if (min > max)
      throw new IllegalArgumentException();
    if (value < min)
      value = min;
    if (value > max)
      value = max;
    return value;
  } // bond

  /** returns true if two given points lie inside one common
   * half-plane of the four half-planes that combine to the
   * outside area of the given rectangle
   */
  public static boolean outsideSameSide(Rectangle rect, 
					Point p1, Point p2) {
    // min/max X/Y are all within rect
    int minX = rect.x;
    int minY = rect.y;
    int maxX = rect.x + rect.width;
    int maxY = rect.y + rect.height;
    // do the check
    if ((p1.x < minX && p2.x < minX) ||
	(p1.x > maxX && p2.x > maxX) ||
	(p1.y < minY && p2.y < minY) ||
	(p1.y > maxY && p2.y > maxY))
      return true;
    else
      return false;
  } // outsideSameSide

  /* draws a circle line
   * @param g the graphics context to draw on
   * @param pos the center position of the circle
   * @param radius the circle radius
   */
  public static void drawCircle(Graphics g, Point pos, int radius) {
    g.drawArc(pos.x - radius, pos.y - radius,
	      radius * 2, radius * 2,
	      0, 360);
  } // drawCircle

  /* draws a filled circle
   * @param g the graphics context to draw on
   * @param pos the center position of the circle
   * @param radius the circle radius
   */
  public static void fillCircle(Graphics g, Point pos, int radius) {
    g.fillArc(pos.x - radius, pos.y - radius,
	      radius * 2, radius * 2,
	      0, 360);
  } // fillCircle

} // AwtUtil


