package util;

import java.util.*;

/** ensures that no two Objects a, b with a.equals(b) are mapped
 * with different keys;
 * if trying to map two equal Objects, an IllegalArgumentException
 * will be thrown
 */
public class NoEqualValuesMap<K,V>
  extends HashMap<K,V> {

  /** returns wether the map already contains a value that
   * equals the given value
   */
  public boolean containsEqual(V value) {
    return containsValue(value);
  } // containsEqual

  public V put(K key, V value) {
    if (containsEqual(value))
      throw new IllegalArgumentException("Duplicate Value: " + value);
    return super.put(key, value);
  } // put

} // NoEqualValuesMap
