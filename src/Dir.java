package mapmaker;

import java.awt.*;

public class Dir {

  public static final int

    north = 0, 
    east = 1, 
    south = 2, 
    west = 3,
    ne = 4, 
    se = 5, 
    sw = 6, 
    nw = 7,
    up = 8,
    down = 9,

    DIRNR = 10,
    PLANEDIRNR = 8; // no up/down position handled  

  // contains the directions of 'north' etc. in screen coordinates.
  static final int[][] screenDirs= 
  { { 0, -1}, { 1, 0}, { 0, 1}, {-1, 0},
    { 1, -1}, { 1, 1}, {-1, 1}, {-1,-1},
    { 0,  0}, { 0, 0}};

  // contains the directions in clockwise order
  static final int[] clockwise = { north, ne, east, se, south, sw, west, nw };

  /** returns the screen direction associated with the given direction
   */
  public static Point screenDir(int dir) {
    return new Point(screenDirs[dir][0], screenDirs[dir][1]);
  } // screenDir

  /** returns the direction opposite to dir
   * e.g. opposite(north) == south
   */
  public static int opposite(int dir) {
    // returns the opposing direction
    int[] opposite = { south, west, north, east, sw, nw, ne, se, down, up };
    return opposite[dir];
  } // opposite

  private static int clockwiseIndex(int dir) {
    for (int i = 0; i < clockwise.length; i++)
      if (clockwise[i] == dir)
	return i;
    throw new IllegalArgumentException();
  } // clockWiseIndex

  /** returns the direction following dir in clockwise movement
   * e.g. turnClockwise(north) == ne
   */
  public static int turnClockwise(int dir) {
    // returns the next dir in clockwise movement
    int index = clockwiseIndex(dir) + 1;
    // simulate modulo
    if (index == clockwise.length)
      index = 0;
    return clockwise[index];
  } // turnClockWise

  /** returns the direction following dir in counter-clockwise movement
   * e.g. turnClockwise(north) == nw
   */
  public static int turnCounterClockwise(int dir) {
    // returns the next dir in clockwise movement
    int index = clockwiseIndex(dir) - 1;
    // simulate modulo
    if (index == -1)
      index = clockwise.length - 1;
    return clockwise[index];
  } // turnCounterClockWise

  static final String[] dirNames =
  { "n", "e", "s", "w", "ne", "se", "sw", "nw", "u", "d"};

  /** returns a string associated with the given direction
   */
  public static String dirToString(int dir) {
    return dirNames[dir];
  } // dirToString

  /** returns the direction associated with the given string
   * parseString(dirToString(dir)) == dir
   */
  public static int parseString(String s) {
    // returns -1 if failed
    for (int i = 0; i < DIRNR; i++)
      if (dirNames[i].equals(s))
	return i;
    return -1;
  } // parseString

  /** returns the integer associated with the given direction
   * in the Merc-standard
   */
  public static int mercID(int dir) {
    int[] mercIDs = {0, 1, 2, 3, 6, 7, 8, 9, 4, 5};
    return mercIDs[dir];
  } // mercID

} // Dir


