package mapmaker;

import java.util.*;
import javax.swing.*;
import java.io.*;

import util.*;

/** used to convert a map into a ROM file
 */
public class RomConverter 
  implements MapConverter {
  
  protected static final String STRING_TERM = "~";

  protected static final String[] ROM_FLAG_NAMES =
  { "A", "C", "D", "J", "K", "L", "M", "N" };

  protected String getRoomName(Room room) {
    return RomConverterTool.getRoomName(room) + STRING_TERM;
  } // getRoomName
    
  protected Text getRoomDesc(Room room) {
    Text roomDesc = RomConverterTool.getRoomDesc(room);
    roomDesc.append(STRING_TERM);
    return roomDesc;
  } // getRoomDesc

  /** returns a String representing the proper roomflags for
   * the given room as needed in ROM area file
   */
  protected String getRomRoomFlags(Room room) {
    String flagString = "";
    boolean[] flags = RomConverterTool.getRoomFlags(room);
    for (int i = 0; i < ROM_FLAG_NAMES.length; i++)
      if (flags[i])
	flagString += ROM_FLAG_NAMES[i];
    if (flagString.length() == 0)
      return "0";
    else
      return flagString;
  } // getRomRoomFlags

  /** converts the given map into an array of strings that
   * represents the map in Rom format
   */
  public Text mapToText(AreaMap map, int startVnum, String fileName) {
    Room[] rooms = map.getRooms();
    NoEqualValuesMap roomVnums = 
      RomConverterTool.assignVnums(rooms, startVnum);
    Text out = new Text();
    
    // the #AREA section
    out.append("#AREA");
    // file name
    out.append(fileName + STRING_TERM);
    // area name
    String areaName = "noname";
    if (map instanceof DescObj) {
      String mapName = MapDescParser.get().getName(((DescObj)map).getDesc());
      if (mapName.length() > 0)
	areaName = mapName;
    }
    out.append(areaName + STRING_TERM);
    // area command line
    out.append("{?? ??} MapMaker " + areaName + STRING_TERM);
    // get maximum vnum -> area vnums
    int maxVnum = 0;
    Iterator it = roomVnums.values().iterator();
    while (it.hasNext()) {
      int vnum = ((Integer)it.next()).intValue();
      if (vnum > maxVnum)
	maxVnum = vnum;
    }
    // range of vnums must be from __00 - __99
    int lastVnum = startVnum + 99;
    while (lastVnum < maxVnum)
      lastVnum += 100;
    out.append(startVnum + " " + lastVnum);
    out.append("");

    // the #ROOMS section
    out.append("#ROOMS");
    for (int i = 0; i < rooms.length; i++) {
      if (!(rooms[i] instanceof VirtualRoom)) {
	// append non-link data
	out.append("#" + roomVnums.get(rooms[i]));
	out.append(getRoomName(rooms[i]));
	out.append(getRoomDesc(rooms[i]));
	out.append("0 " + 
		   getRomRoomFlags(rooms[i]) + " " +
		   RomConverterTool.getRoomSector(rooms[i]));
	// append links
	for (int dir = 0; dir < Dir.DIRNR; dir++)
	  if (rooms[i].exitLinked(dir) && !rooms[i].exitBlocked(dir)) {
	    Room exitRoom = 
	      RomConverterTool.getExitRoom(rooms[i], rooms[i].getLink(dir));
	    // exitRoom can be null -> no exit
	    if (exitRoom != null) {
	      out.append("D" + Dir.mercID(dir));
	      out.append(STRING_TERM);
	      out.append(STRING_TERM);
	      out.append("0 0 " + roomVnums.get(exitRoom));
	    }
	  }
	// finish room
	out.append("S");
      }
    } // for (rooms)
    out.append("#0");
    out.append("");

    // the #$ section
    out.append("#$");

    return out;
  } // mapToText

} // RomConverter


