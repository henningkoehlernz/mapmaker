package mapmaker;

import java.awt.*;
import mapmaker.mapcmd.*;
import util.*;

public interface Map {
  
  // state-changing interface
  public void execute(MapCommand cmd);
  public void deepCopyFrom(Map original);

  // state-query interface
  public Room getRoom(Point pos);
  public Point getRoomPos(Room room);
  public Room getSelected();
  public Room[] getRooms();
  public Link[] getLinks();
  public Dimension getSize();
  public Text getDesc();

  // special interface for classes Link and Room
  public void removeLink(Link link);
  public void removeRoom(Room room);

  // special interface for DescViewer
  public void setDesc(Text desc);

  // interface to handle notifies
  public void notifyOfChange(Object arg);

} // Map
