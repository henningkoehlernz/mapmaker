package mapmaker.mapcmd;

import java.awt.*;

public class RCmdSwapExits extends RoomCommand {

  public int dir1, dir2;

  public RCmdSwapExits(Point mapPos, int dir1, int dir2) {
    super(mapPos);
    this.dir1 = dir1;
    this.dir2 = dir2;
  } // RCmdSwap Exits

} // RCmdSwapExits
