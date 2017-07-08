package util;

import java.util.*;

public class GeneralUtil {

  /** returns wether any of the Objects in set equals obj
   */
  public static boolean containsEqual(Collection set, Object obj) {
    Iterator it = set.iterator();
    while (it.hasNext())
      if (it.next().equals(obj))
	return true;
    return false;
  } // containsEqual

} // GeneralUtil
