package util;

import java.util.*;

/** ensures that no two Objects a, b with a.equals(b) are mapped
 * with different keys;
 * if trying to map two equal Objects, an IllegalArgumentException
 * will be thrown
 */
public class NoEqualValuesMap
  extends HashMap {

  /** returns wether the map already contains a value that
   * equals the given value
   */
  public boolean containsEqual(Object value) {
    return GeneralUtil.containsEqual(values(), value);
  } // containsEqual

  public Object put(Object key, Object value) {
    if (containsEqual(value))
      throw new IllegalArgumentException("Double Value: " + value);
    return super.put(key, value);
  } // put

} // NoEqualValuesMap
