package mapmaker.mapcmd;

import java.awt.*;
import util.*;

public class RCmdSetRoomDesc extends RoomCommand {

  public Text desc;

  public RCmdSetRoomDesc(Point mapPos, Text desc) {
    super(mapPos);
    this.desc = desc;
  } // RCmdSetRoomDesc

} // RCmdSetRoomDesc
