package mapmaker;

import java.awt.*;

public class MapEditFilter extends MapDecorator {

  boolean enabled;

  MapEditFilter(Map map) {
    super(map);
    enabled = true;
  } // MapEditFilter

  public void enableEdit() {
    enabled = true;
  } // enableEdit

  public void disableEdit() {
    enabled = false;
  } // disableEdit

  public void newRoom(Point pos) {
    if (enabled)
      super.newRoom(pos);
  } // newRoom
  
  public void killRoom(Point pos) {
    if (enabled)
      super.killRoom(pos);
  } // killRoom

  public void swapRooms(Point pos1, Point pos2) {
    if (enabled)
      super.swapRooms(pos1, pos2);
  } // swapRooms
  
  public void linkRooms(Point pos1, Point pos2) {
    if (enabled)
      super.linkRooms(pos1, pos2);
  } // linkRooms
 
} // MapEditFilter
