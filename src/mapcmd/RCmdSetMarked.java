package mapmaker.mapcmd;

import java.awt.*;

public class RCmdSetMarked extends RoomCommand {
  
  public boolean marked;

  public RCmdSetMarked(Point mapPos, boolean marked) {
    super(mapPos);
    this.marked = marked;
  } // RCmdSetMarked

} // RCmdSetMarked
