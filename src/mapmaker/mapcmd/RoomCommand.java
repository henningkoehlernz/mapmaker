package mapmaker.mapcmd;

import java.awt.*;

/** special type of command a map delegates to a room
 */
public abstract class RoomCommand implements MapCommand {

  /** the position of the room the command should be
   * delegated to
   */
  public Point mapPos;

  public RoomCommand(Point mapPos) {
    this.mapPos = mapPos;
  } // RoomCommand

} // RoomCommand
