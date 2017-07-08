package util;

import java.util.*;

public class MyObservable extends Observable {
  // cheap bug-fix for Observable class

  Observer[] observers = new Observer[0];

  public void addObserver(Observer o) {
    Observer[] newObs = new Observer[observers.length + 1];
    for (int i = 0; i < observers.length; i++)
      newObs[i] = observers[i];
    newObs[observers.length] = o;
    observers = newObs;
  } // addObserver

  public void notifyObservers(Object arg) {
    for (int i = 0; i < observers.length; i++)
      observers[i].update(this, arg);
  } // notifyObservers

  public void notifyObservers() {
    notifyObservers(null);
  } // notifyObservers

} // MyObservable
