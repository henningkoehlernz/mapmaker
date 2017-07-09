package util;

import java.util.*;

public interface Node {

  /** returns all nodes directly connected to node
   * @param arg can specify which neighbours to return
   */
  public Set<Node> neighbours(Object arg);

} // Node
