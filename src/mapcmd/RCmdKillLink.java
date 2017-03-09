package mapmaker.mapcmd;

import java.awt.*;

public class RCmdKillLink extends RoomCommand {

  public int dir;

  public RCmdKillLink(Point mapPos, int dir) {
    super(mapPos);
    this.dir = dir;
  } // RCmdKillLink

} // RCmdKillLink
