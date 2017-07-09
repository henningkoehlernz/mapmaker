package util;

import java.util.*;

public class CompositeFilter
  extends TextFilter {

  Vector<TextFilter> filterList = new Vector<TextFilter>();

  public void addFilter(TextFilter filter) {
    filterList.addElement(filter);
  } // addFilter

  public boolean accept(String line) {
    for (int i = 0; i < filterList.size(); i++)
      if (!filterList.elementAt(i).accept(line))
        return false;
    return true;
  } // accept

} // CompositeFilter
