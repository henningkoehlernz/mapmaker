package util;

public class ConstStateController
  implements StateController {
  
  private Object state;

  public ConstStateController(Object state) {
    this.state = state;
  } // ConstStateController

  public Object state() {
    return state;
  } // state

} // ConstStateController
