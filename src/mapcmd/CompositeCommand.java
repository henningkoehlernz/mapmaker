package mapmaker.mapcmd;

import java.util.*;

/** groups several MapCommands into one
 */
public class CompositeCommand implements MapCommand {

  Vector commands = new Vector();

  /** adds cmd to the list of commands contained
   */
  public void add(MapCommand cmd) {
    commands.addElement(cmd);
  } // add

  /** returns an iterator that iterates through the contained
   * commands in the same order as they where added
   */
  public Iterator iterator() {
    return new CommandIterator(commands);
  } // iterator

} // CompositeCommand

class CommandIterator implements Iterator {

  int pos = 0;
  Vector commands;

  CommandIterator(Vector commands) {
    this.commands = commands;
  } // CommandIterator

  public boolean hasNext() {
    return pos < commands.size();
  } // hasNext

  public Object next() {
    return commands.elementAt(pos++);
  } // next

  /** does nothing
   */
  public void remove() {
  } // remove

} // CommandIterator
