package mapmaker;

/** links two rooms in a map
 */
public class Link {

  Room[] rooms;
  AreaMap map;

  /** automatically links room1 and room2 at the given exits
   * @param map the map containing the link
   */
  public Link(AreaMap map, Room room1, int exit1, Room room2, int exit2) {
    rooms = new Room[2];
    rooms[0] = room1;
    rooms[1] = room2;
    this.map = map;
    notifyRooms(exit1, exit2);
  } // Link

  /** initializes as a deep copy of orgLink
   */
  public Link(AreaMap map, Link orgLink) {
    this.map = map;
    rooms = new Room[2];
    Room[] orgRooms = orgLink.getRooms();
    int[] exits = new int[2];
    for (int i = 0; i < 2; i++) {
      rooms[i] = map.getRoom(orgRooms[i].getPos());
      exits[i] = orgRooms[i].getExitDir(orgLink);
    }
    notifyRooms(exits[0], exits[1]);
  } // Link

  /** returns the rooms linked
   */
  public Room[] getRooms() {
    return rooms;
  } // getRoom

  void notifyRooms(int exit1, int exit2) {
    rooms[0].setLink(this, exit1);
    rooms[1].setLink(this, exit2);
  } // notifyRooms

  /** should only be called by Room or Map object
   */
  public void suicide() {
    unlinkRooms();
    map.removeLink(this);
  } // suicide

  void unlinkRooms() {
    for (int i = 0; i < 2; i++)
      rooms[i].unlink(this);
  } // unlinkRooms

  /** from the two rooms linked, returns the one unequal to room
   * @param room must be one of the rooms linked
   */
  public Room opposite(Room room) {
    if (rooms[0] == room)
      return rooms[1];
    if (rooms[1] == room)
      return rooms[0];
    throw new IllegalArgumentException();
  } // opposite

} // Link




