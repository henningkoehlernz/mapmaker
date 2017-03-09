package mapmaker;

import java.awt.*;
import java.util.*;

import mapmaker.mapcmd.*;
import util.*;

public class MapUtil {

  /** checks if the rooms can be moved in the given direction on the map
   * without conflicting with other rooms or the map dimensions
   * @param rooms must contain nothing but Room objects part of map
   */
  public static boolean isMoveable(AreaMap map, Set rooms, Point dir) {
    Dimension dim = map.getSize();
    Iterator it = rooms.iterator();
    while (it.hasNext()) {
      Room room = (Room)it.next();
      Point newPos = room.getPos();
      newPos.translate(dir.x, dir.y);
      // check if within map dimensions
      if (newPos.x < 0 || newPos.x >= dim.width || 
	  newPos.y < 0 || newPos.y >= dim.height)
	return false;
      // check if new Position free of occupied with room in rooms
      Room occupier = map.getRoom(newPos);
      if (occupier != null && !rooms.contains(occupier))
	return false;
    }
    return true;
  } // isMoveable

  /** moves all rooms in given direction, 
   * isMoveable(map, rooms, dir) must be true
   */
  public static void moveRooms(AreaMap map, Set rooms, Point dir) {
    // swap first those with biggest <pos, dir> to ensure
    // that only swaps to empty positions are done
    // otherwise positions given in commands could invalidate
    // during execution of previous commands
    Iterator it = rooms.iterator();
    int size = rooms.size();
    Point[] pos = new Point[size];
    for (int i = 0; i < size; i++)
      pos[i] = ((Room)it.next()).getPos();
    MapMath.partialSort(new DirComparer(dir), pos);
    CompositeCommand cmd = new CompositeCommand();
    for (int i = size - 1; i >= 0; i--)
      cmd.add(new CmdSwapRooms(pos[i], new Point(pos[i].x + dir.x,
						 pos[i].y + dir.y)));
    map.execute(cmd);
  } // moveRooms

  /** marks all rooms who's descriptions contain a given string
   * @param map the rooms of this map will be searched and marked
   * @param searchString the String that must be contained in
   * a room's description
   * @param nameOnly if true, only marks rooms with the exact name
   * as the searchString
   */
  public static void markRoomsWithDesc(AreaMap map, 
				       String searchString,
				       boolean nameOnly) {
    CompositeCommand markCmd = new CompositeCommand();
    Room[] rooms = map.getRooms();
    for (int i = 0; i < rooms.length; i++)
      if (rooms[i] instanceof DescObj) {
	Text desc = ((DescObj)rooms[i]).getDesc();
	// search even if desc null: searchString could be ""
	if (desc == null)
	  desc = new Text();
	if (!nameOnly && desc.contains(searchString) ||
	    nameOnly && MapDescParser.get().getName(desc).equals(searchString))
	  markCmd.add(new RCmdSetMarked(rooms[i].getPos(), true));
      }
    map.execute(markCmd);
  } // markRoomsWithDesc

  public static Room[] getMarkedRooms(AreaMap map) {
    Room[] rooms = map.getRooms();
    Vector markedVector = new Vector();
    for (int i = 0; i < rooms.length; i++)
      if (rooms[i].getMarked())
	markedVector.addElement(rooms[i]);
    Room[] markedRooms = new Room[markedVector.size()];
    markedVector.copyInto(markedRooms);
    return markedRooms;
  } // getMarkedRooms

  /** sets all rooms of the given map as not marked
   */
  public static void unmarkRooms(AreaMap map) {
    CompositeCommand unmarkCmd = new CompositeCommand();
    Room[] rooms = getMarkedRooms(map);
    for (int i = 0; i < rooms.length; i++)
      unmarkCmd.add(new RCmdSetMarked(rooms[i].getPos(), false));
    map.execute(unmarkCmd);
  } // unmarkRooms

  /** sets all marked sooms on the given map to the given color
   */
  public static void colorMarkedRooms(AreaMap map, NamedColor color) {
    CompositeCommand colorCmd = new CompositeCommand();
    Room[] rooms = getMarkedRooms(map);
    for (int i = 0; i < rooms.length; i++)
      colorCmd.add(new RCmdSetColor(rooms[i].getPos(), color));
    map.execute(colorCmd);
  } // unmarkRooms

  /** adds cmd to the room description of all marked rooms
   * or replaces it if it already exists
   */
  public static void replaceCommandOnMarkedRooms(AreaMap map, String cmd) {
    Room[] rooms = getMarkedRooms(map);
    for (int i = 0; i < rooms.length; i++)
      if (rooms[i] instanceof DescObj) {
	DescObj descObj = (DescObj)rooms[i];
	descObj.setDesc(StringUtil.replaceMatchingLine(
	  descObj.getDesc().trim(), cmd));
      }
    map.notifyOfChange(MapEvent.ChangeDesc);
  } // replaceCommandOnMarkedRooms

  /** removes the first occurence of the given command from all
   * room descriptions of the marked rooms
   * @param map the map to search
   * @param cmd the command to remove
   */
  public static void removeCommandOnMarkedRooms(AreaMap map, String cmd) {
    Room[] rooms = getMarkedRooms(map);
    for (int i = 0; i < rooms.length; i++)
      if (rooms[i] instanceof DescObj) {
	DescObj descObj = (DescObj)rooms[i];
	Text desc = descObj.getDesc();
	int index = StringUtil.firstMatch(desc, cmd);
	if (index != -1)
	  descObj.setDesc(desc.removeLine(index));
      }
    map.notifyOfChange(MapEvent.ChangeDesc);
  } // removeCommandOnMarkedRooms

  /** returns an array containing the positions of all existing
   * rooms in map between startPos and endPos
   */
  public static Point[] meanRooms(AreaMap map, Point startPos, Point endPos) {
    Point[] meanPoints = MapMath.meanPoints(startPos, endPos);
    // check if rooms exist at mean positions
    Vector meanRoomVector = new Vector();
    for (int i = 0; i < meanPoints.length; i++)
      if (map.getRoom(meanPoints[i]) != null)
	meanRoomVector.add(meanPoints[i]);
    Point[] meanRoomArray = new Point[meanRoomVector.size()];
    meanRoomVector.copyInto(meanRoomArray);
    return meanRoomArray;
  } // meanRooms

} // MapUtil

