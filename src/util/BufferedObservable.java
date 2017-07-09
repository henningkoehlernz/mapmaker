package util;

import java.util.*;

/** collects notifies and combines them to a single call, returning a
 * Set object containing the arguments given
 */
public class BufferedObservable 
extends Observable {
  
  // events must never be null
  private HashSet<Object> events = new HashSet<Object>();
  private int delayCount = 0;
  private boolean mustNotify = false;
  
  private Observable servant;

  public BufferedObservable(Observable servant) {
    this.servant = servant;
  } // BufferedObservable

  /** when called, Observers won't be notified of events until
   * fire is called as often as delayNotify has been called
   * before
   */
  public void delayNotify() {
    delayCount++;
  } // delayNotify

  /** cancels the delay started by delayNotify and notifies
   * observers if necessary;
   * must not be called unless delayNotify has been called before
   */
  public void fireNotify() {
    if (delayCount <= 0)
      throw new EmptyStackException();
    delayCount--;
    checkNotify();
  } // fireNotify

  /** checks if a notify is necessary and if so, does it
   */
  private void checkNotify() {
    if (delayCount == 0 && mustNotify) {
      servant.notifyObservers(events.clone());
      events.clear();
      mustNotify = false;
    }
  } // checkNotify

  public void notifyObservers() {
    mustNotify = true;
    checkNotify();
  } // notifyObservers

  public void notifyObservers(Object arg) {
    mustNotify = true;
    events.add(arg);
    checkNotify();
  } // notifyObservers

  public void addObserver(Observer obs) {
    servant.addObserver(obs);
  } // addObserver
    
} // BufferedObserverable
