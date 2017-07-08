package util;

import java.util.*;

public class NodeCollector {

  /** returns all Nodes linked to root (recursively)
   * @param arg is passed through to the nodes
   */
  public Set getNodeCollection(Node root, Object arg) {
    LinkedList queue = new LinkedList();
    Set collection = new HashSet();
    queue.add(root);
    collection.add(root);
    while (queue.size() > 0) {
      // INV = all elements in queue are already in collection
      // INV = all nodes recursively connected to root are
      // either contained in collection or reachable from
      // an element in queue without 'passing' any element
      // already in collection
      Node current = (Node)queue.removeFirst();
      Iterator it = current.neighbours(arg).iterator();
      while (it.hasNext()) {
	Object neighbour = it.next();
	if (!collection.contains(neighbour)) {
	  collection.add(neighbour);
	  queue.addLast(neighbour);
	}
      }
    } // queue is empty -> all elements reachable in collection
    return collection;
  } // getNodeCollection

} // NodeCollector
