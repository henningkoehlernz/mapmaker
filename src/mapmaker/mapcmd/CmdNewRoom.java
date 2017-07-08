package mapmaker.mapcmd;

import java.awt.*;

public class CmdNewRoom implements MapCommand {

  public Point pos;
  public Object roomType;

  public CmdNewRoom(Point pos, Object roomType) {
    this.pos = pos;
    this.roomType = roomType;
  } // CmdNewRoom

} // CmdNewRoom

