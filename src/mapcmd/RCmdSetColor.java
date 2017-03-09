package mapmaker.mapcmd;

import java.awt.*;
import util.*;

public class RCmdSetColor extends RoomCommand {
  
  public NamedColor color;

  public RCmdSetColor(Point mapPos, NamedColor color) {
    super(mapPos);
    this.color = color;
  } // RCmdSetMarked

} // RCmdSetColor
