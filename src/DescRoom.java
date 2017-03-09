package mapmaker;

import util.*;
import mapmaker.mapcmd.*;

public class DescRoom
  extends CRoom
  implements DescObj {
  
  DescObj descObj = new CDescObj();

  public DescRoom(AreaMap map) {
    super(map);
  } // DescRoom

  public Text getDesc() {
    return descObj.getDesc();
  } // getDesc
  
  public void setDesc(Text desc) {
    descObj.setDesc(desc);
  } // setDesc

  /** executes RCmdSetRoomDesc in addition to those handled by CRoom
   */
  public void execute(RoomCommand cmd) {
    if (cmd instanceof RCmdSetRoomDesc) {
      setDesc(((RCmdSetRoomDesc)cmd).desc);
      return;
    }
    super.execute(cmd);
  } // execute
 
  /** copies room description if orgRoom is instance of DescObj
   */
  public void deepCopyFrom(Room orgRoom) {
    super.deepCopyFrom(orgRoom);
    if (orgRoom instanceof DescObj)
      setDesc(((DescObj)orgRoom).getDesc());
    else
      setDesc(new Text());
  } // deepCopyFrom
  
  public Room cloneRoom(AreaMap map) {
    Room room = new DescRoom(map);
    room.deepCopyFrom(this);
    return room;
  } // cloneRoom

} // DescRoom

