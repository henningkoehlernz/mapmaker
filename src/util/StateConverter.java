package util;

import java.util.*;

/** filters the state another StateController delivers and
 * converts it into another one
 */
public class StateConverter
  implements StateController {

  private StateController stateController;
  private Map<Object,Object> converter = new HashMap<Object,Object>();

  public StateConverter(StateController stateController) {
    this.stateController = stateController;
  } // StateConverter

  /** orders the StateConverter to convert oldState into newState
   */
  public void convert(Object oldState, Object newState) {
    converter.put(oldState, newState);
  } // convert

  public Object state() {
    Object orgState = stateController.state();
    if (converter.containsKey(orgState))
      return converter.get(orgState);
    else {
      System.out.println("orgState " + orgState + " not found in " + converter);
      return orgState;
    }
  } // state

} // StateConverter
