package mapmaker;

import java.awt.*;
import java.util.*;

import mapmaker.mapcmd.*;
import util.*;

public class MapParser {
  
  // some String constants used by mapToText and restoreMap
  static final char 
    IDCHAR = '#',
    DESCCHAR = '/';
  static final String 
    MAP_ID = IDCHAR + "MAP",
    ROOM_ID = IDCHAR + "ROOM",
    LINK_ID = IDCHAR + "LINK",
    END_ID = IDCHAR + "END";
  static final String
    BLOCKED_SIGN = "X",
    VIRTUAL_SIGN = "V";
  static final String
    COLOR_LINE = "C";
  
  /** stores the iterator for the text to parse
   */
  protected TextIterator tit;

  /** stores the dimension of the parsed map
   */
  protected Dimension mapDim;

  /** stores the command for creating the new map
   */
  protected CompositeCommand createCmd;

  // the stuff used to turn a map into a parsable text

  /** used by mapToText;
   * adds lines to output with DESCCHAR in front of each line
   */
  private void addDesc(Text desc, Text output) {
    if (desc == null)
      return;
    for (int i = 0; i < desc.getLineCount(); i++)
      output.append(DESCCHAR + desc.getLine(i));
  } // addDesc

  /** returns an array of strings that describe the given map
   * useful for saving a map as an ascii file
   */
  public Text mapToText(AreaMap map) {
    Room[] rooms = map.getRooms();
    Link[] links = map.getLinks();
    //int minSize = 4 + rooms.length * 2 + links.length * 3;
    Text output = new Text();
    output.append(MAP_ID);
    output.append(map.getSize().width + " " +
		  map.getSize().height);
    addDesc(map.getDesc(), output);
    // store rooms
    for (int i = 0; i < rooms.length; i++) {
      output.append(ROOM_ID);
      String outString = 
	rooms[i].getPos().x + " " + rooms[i].getPos().y;
      if (rooms[i] instanceof VirtualRoom)
	outString += " " + VIRTUAL_SIGN;
      output.append(outString);
      // store color
      Color color = rooms[i].getColor();
      if (color != null)
	output.append(COLOR_LINE + " " + color);
      // store description
      if (rooms[i] instanceof DescObj)
	addDesc(((DescObj)rooms[i]).getDesc(), output);
    }
    // store links
    for (int i = 0; i < links.length; i++) {
      output.append(LINK_ID);
      Room[] linkedRooms = links[i].getRooms();
      for (int nr = 0; nr < 2; nr++) {
	Point roomPos = linkedRooms[nr].getPos();
	String pos = roomPos.x + " " + roomPos.y;
	int exitDir = linkedRooms[nr].getExitDir(links[i]);
	String exit = Dir.dirToString(exitDir);
	if (linkedRooms[nr].exitBlocked(exitDir))
	  exit += " " + BLOCKED_SIGN;
	output.append(pos + " " + exit);
      }
    }
    output.append(END_ID);
    return output;
  } // mapToText

  // now the stuff used to parse a text into a map

  /** parses a String to int and throws an IllegalArgumentException
   * if format is incorrect; used by restoreMap
   * @param s String to be parsed
   * @param msg error message for thrown Exception
   */
  private int parseInt(String s, String msg) {
    try {
      return Integer.parseInt(s);
    }
    catch(NumberFormatException e) {
      throw new IllegalArgumentException(msg);
    }
  } // parseInt

  /** creates a map from the given input
   */
  public AreaMap createMap(Text input) {
    AreaMap map = new CMap(0, 0, new Observable());
    restoreMap(map, input);
    return map;
  } // createMap

  /** restores a map's data from an array of strings
   * in a format as created by mapToText;
   * throws IllegalArgumentException if format is incorrect
   * but leaves map unchanged
   */
  public void restoreMap(AreaMap map, Text input) {
    createCmd = new CompositeCommand();
    tit = new TextIterator(input);

    // read in map size & description
    if (input.getLineCount() < 3)
      throw new IllegalArgumentException("input too small, check aborted");
    // check if input starts with MAP_ID
    if (!tit.next().equals(MAP_ID))
      throw new IllegalArgumentException(MAP_ID + " missing");
    // read size
    String[] sizeStrings = StringUtil.splitString(tit.next());
    if (sizeStrings.length != 2)
      throw new IllegalArgumentException("wrong number of size parameters");
    int sizeX = parseInt(sizeStrings[0], "size format error in x");
    int sizeY = parseInt(sizeStrings[1], "size format error in y");
    if (sizeX < 0 || sizeY < 0)
      throw new IllegalArgumentException("negative size value");
    mapDim = new Dimension(sizeX, sizeY);
    // read map description
    createCmd.add(new CmdSetDesc(parseDesc()));

    // read in rooms & links
    while (tit.hasNext() && !tit.peek().equals(END_ID)) {
      // check wether room or link input
      if (tit.peek().equals(ROOM_ID)) {
	tit.jump();
	parseRoom();
      } // if room
      else
	if (tit.peek().equals(LINK_ID)) {
	  tit.jump();
	  parseLink();
	  } // if link
	else
	  throw new IllegalArgumentException("unexpected String");
    }
    if (!tit.hasNext())
      throw new IllegalArgumentException(END_ID + " missing");

    // now finally execute the whole stuff :)
    AreaMap bufferMap = new CMap(mapDim.width, mapDim.height,
			     new MyObservable());
    bufferMap.execute(createCmd);
    map.deepCopyFrom(bufferMap);
  } // restoreMap

  /** parses a single room from the text given and adds a command
   * that will create it to createCmd;
   * assumes that the ROOM_ID has come before
   */
  protected void parseRoom() {
    String[] posStrings = StringUtil.splitString(tit.next());
    if (!(posStrings.length == 2 || posStrings.length == 3)) {
      String msg = "wrong number of room position parameters";
      throw new IllegalArgumentException(msg);
    }
    String msg = "pos format error";
    int posX = parseInt(posStrings[0], msg);
    int posY = parseInt(posStrings[1], msg);
    Point pos = new Point(posX, posY);
    if (!AwtUtil.contains(mapDim, pos))
      throw new IllegalArgumentException("room position out of range ");

    // create room
    if (posStrings.length == 2)
      createCmd.add(new CmdNewRoom(pos, MapFactory.type_DescRoom));
    else if (posStrings[2].equals(VIRTUAL_SIGN))
      createCmd.add(new CmdNewRoom(pos, MapFactory.type_VirtualRoom));
    else
      throw new IllegalArgumentException("illegal room type");

    // parse color
    String[] colorStrings = StringUtil.splitString(tit.peek());
    if (colorStrings.length > 0 && colorStrings[0].equals(COLOR_LINE)) {
      tit.jump(); // color line found => advance to next
      if (colorStrings.length != 2)
	throw new IllegalArgumentException("wrong number of color parameters");
      NamedColor color = NamedColor.parseName(colorStrings[1]);
      createCmd.add(new RCmdSetColor(pos, color));
    }

    // parse room description
    Text desc = parseDesc();
    createCmd.add(new RCmdSetRoomDesc(pos, desc));
      
  } // parseRoom
  
  /** parses a single link from the text given and adds a command
   * that will create it to createCmd;
   * assumes that the LINK_ID has come before
   */
  protected void parseLink() {
    Point[] pos = new Point[2];
    int[] exits = new int[2];
    boolean[] blocked = new boolean[2];
    for (int i = 0; i < 2; i++) {
      String[] linkStrings = StringUtil.splitString(tit.next());
      // check if blocked
      if (linkStrings.length == 4 && 
	  linkStrings[3].equals(BLOCKED_SIGN))
	blocked[i] = true;
      else {
	blocked[i] = false;
	if (linkStrings.length != 3)
	  throw new IllegalArgumentException("link error");
      }
      // get position of room linked
      String msg = "pos format error in line ";
      int posX = parseInt(linkStrings[0], msg);
      int posY = parseInt(linkStrings[1], msg);
      pos[i] = new Point(posX, posY);
      // get exit direction
      exits[i] = Dir.parseString(linkStrings[2]);
    } // for
    createCmd.add(new CmdLinkRooms(pos[0], exits[0], pos[1], exits[1]));
    for (int i = 0; i < 2; i++)
      if (blocked[i])
	createCmd.add(new RCmdSetExitBlocked(pos[i], exits[i], true));
  } // parseLink
  
  protected Text parseDesc() {
    Text desc = new Text();
    while (tit.peek().charAt(0) == DESCCHAR)
      desc.append(tit.next().substring(1));
    return desc;
  } // parseDesc

} // MapParser
