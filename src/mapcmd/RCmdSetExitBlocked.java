package mapmaker.mapcmd;

import java.awt.*;

public class RCmdSetExitBlocked extends RoomCommand {
  
  public int dir;
  public boolean blocked;

  public RCmdSetExitBlocked(Point mapPos, int dir, boolean blocked) {
    super(mapPos);
    this.dir = dir;
    this.blocked = blocked;
  } // RCmdSetExitBlocked

} // RCmdSetExitBlocked
