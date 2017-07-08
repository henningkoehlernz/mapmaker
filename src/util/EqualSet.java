package util;

import java.util.*;

/** implements a set where two objects a, b are considered equal if
 * a.equals(b) instead of a == b as in HashSet
 */
public class EqualSet
  extends HashSet {

  /** returns the object in set that equals o or null if no object
   * equals it
   */
  public Object match(Object o) {
    Iterator it = iterator();
    while (it.hasNext()) {
      Object next = it.next();
      if (next.equals(o))
	return next;
    }
    return null;
  } // match

  public boolean add(Object o) {
    if (contains(o))
      return false;
    else
      return super.add(o);
  } // add

  public boolean contains(Object o) {
    return match(o) != null;
  } // contains

  public boolean remove(Object o) {
    return super.remove(match(o));
  } // remove

} // EqualSet
