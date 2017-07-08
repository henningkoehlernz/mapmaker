package util;

import java.util.*;

public class CompositeFilter
  extends TextFilter {

  Vector filterList = new Vector();

  public void addFilter(TextFilter filter) {
    filterList.addElement(filter);
  } // addFilter

  public boolean accept(String line) {
    for (int i = 0; i < filterList.size(); i++)
      if (!((TextFilter)filterList.elementAt(i)).accept(line))
	return false;
    return true;
  } // accept

} // CompositeFilter
