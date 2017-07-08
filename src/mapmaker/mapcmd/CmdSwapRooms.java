package mapmaker.mapcmd;

import java.awt.*;

public class CmdSwapRooms implements MapCommand {

  public Point pos1, pos2;

  public CmdSwapRooms(Point pos1, Point pos2) {
    this.pos1 = pos1;
    this.pos2 = pos2;
  } // CmdSwapRooms

} // CmdSwapRooms

