package mapmaker;

import java.awt.*;

/** implements partial order defined by
 * (a < b) <=> (scalar(a-b, dir) < 0)
 * where a, b are vectors, dir a constant vector
 */
public class DirComparer extends PartialComparer {

  Point dir;

  public DirComparer(Point dir) {
    this.dir = dir;
  } // DirComparer

  int pointScalar(Point p) {
    return (dir.x * p.x) + (dir.y * p.y);
  } // pointScalarx

  /** @param a must be of class Point
   * @param b must be of class Point
   */
  public boolean less(Object a, Object b) {
    Point differ = new Point(((Point)a).x - ((Point)b).x, 
			     ((Point)a).y - ((Point)b).y);
    return pointScalar(differ) < 0;
  } // less

}
