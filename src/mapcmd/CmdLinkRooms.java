package mapmaker.mapcmd;

import java.awt.*;

public class CmdLinkRooms implements MapCommand {

  public Point pos1, pos2;
  public int exit1, exit2;
  public boolean autoLink;

  public CmdLinkRooms(Point pos1, Point pos2) {
    this.pos1 = pos1;
    this.pos2 = pos2;
    autoLink = true;
  } // CmdLinkRooms

  public CmdLinkRooms(Point pos1, int exit1, Point pos2, int exit2) {
    this.pos1 = pos1;
    this.pos2 = pos2;
    this.exit1 = exit1;
    this.exit2 = exit2;
    autoLink = false;
  } // CmdLinkRooms

} // CmdLinkRooms

