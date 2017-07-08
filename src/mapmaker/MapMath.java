package mapmaker;

import java.awt.*;

public class MapMath {

  /** returns scalar(a/|a|, b/|b|)
   */
  public static double dirScalar(int[] a, int[] b) {
    if (a.length != b.length)
      throw new IllegalArgumentException();
    double[] scalars = new double[3];
    for (int i = 0; i < a.length; i++) {
      scalars[0] += a[i] * b[i];
      scalars[1] += a[i] * a[i];
      scalars[2] += b[i] * b[i];
    }
    return scalars[0] / Math.sqrt(scalars[1] * scalars[2]);
  } // dirScalar

  /** returns scalar(a/|a|, b/|b|)
   */
  public static double dirScalar(Point a, Point b) {
    int[] aArr = new int[2];
    int[] bArr = new int[2];
    aArr[0] = a.x;
    aArr[1] = a.y;
    bArr[0] = b.x;
    bArr[1] = b.y;
    return dirScalar(aArr, bArr);
  } // dirScalar

  /** performs a partial ascending sort on data, 
   * using comp to compare two objects in data
   */
  public static void partialSort(PartialComparer comp, Object[] data) {
    // implements a select-sort as it works for partial orders too
    for (int pos = 0; pos < data.length - 1; pos++) {
      int lowestPos = pos;
      for (int i = pos + 1; i < data.length; i++)
	if (comp.less(data[i], data[lowestPos]))
	  lowestPos = i;
      // swap lowest object to new position
      Object buf = data[pos];
      data[pos] = data[lowestPos];
      data[lowestPos] = buf;
    }
  } // partialSort

  /** if v1 lies within a Quadrant, returns wether v2 lies within 
   * the opposing Quadrant
   * if v1 lies on an axis, returns wether v2 lies on the 
   * opposing half-plane
   * if v1 is 0, returns false
   */
  public static boolean opposeQuadrant(Point v1, Point v2) {
    if (v1.x == 0)
      return (v1.y * v2.y < 0);
    if (v1.y == 0)
      return (v1.x * v2.x < 0);
    return (v1.x * v2.x < 0) && (v1.y * v2.y < 0);
  } // opposeQuadrant

  /** returns wether the two Vectors v1 and v2 are parallel
   * and point in the same direction
   * @param v1 if 0, returns true
   * @param v2 if 0, returns true
   */
  public static boolean positiveParallel(Point v1, Point v2) {
    boolean parallel = v1.x * v2.y == v1.y * v2.x;
    return parallel && (v1.x * v2.x >= 0) && (v1.y * v2.y >= 0);
  } // positiveParallel

  /** returns the greatest commen divider of a and b;
   * a and b must not both be 0
   */
  public static int gcd(int a, int b) {
    int buf;
    while (a != 0) {
      buf = b % a;
      b = a;
      a = buf;
    }
    return Math.abs(b);
  } // gcd

  /** returns an array containing all points with integer coordinates
   * that lie between start and end, start and end included;
   * the points will be in order from start to end
   */
  public static Point[] meanPoints(Point start, Point end) {
    // (vx, vy) is vector end - start
    int vx = end.x - start.x;
    int vy = end.y - start.y;
    // (dx, dy) is vector of minimum increase
    int gcd = gcd(vx, vy);
    int dx = vx / gcd;
    int dy = vy / gcd;
    Point[] meanPoints = new Point[gcd + 1];
    meanPoints[0] = new Point(start);
    meanPoints[gcd] = new Point(end);
    for (int i = 1; i < gcd; i++)
      meanPoints[i] = new Point(start.x + i * dx,
				start.y + i * dy);
    return meanPoints;
  } // meanPoints

} // MapMath




