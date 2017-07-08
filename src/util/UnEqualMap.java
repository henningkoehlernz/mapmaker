package util;

import java.util.*;

/** ensures that no two Objects a, b with a.equals(b) are mapped
 * with different keys;
 * if trying to map two equal Objects, an IllegalArgumentException
 * will be thrown
 */
public class UnEqualMap
  extends HashMap {

  public boolean containsValue(Object value) {
    return GeneralUtil.containsEqual(entrySet(), value);
  } // containsValue

  public Object put(Object key, Object value) {
    if (containsValue(value))
      throw new IllegalArgumentException("Double Value: " + value);
    return super.put(key, value);
  } // put

} // UnEqualMap
