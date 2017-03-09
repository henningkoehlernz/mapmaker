package mapmaker;

public class VirtualRoom
  extends DescRoom {

  protected int maxLinkNr = 2;

  public VirtualRoom(AreaMap map) {
    super(map);
  } // VirtualRoom

  /** returns the number of linked exits currently connected
   */
  protected int linkNr() {
    int nr = 0;
    for (int i = 0; i < Dir.DIRNR; i++)
      if (exitLinked(i))
	nr++;
    return nr;
  } // linkNr

  /** throws IllegalStateException if more than two exits
   * are linked after link is set
   */
  public void setLink(Link link, int dir) {
    super.setLink(link, dir);
    if (linkNr() > maxLinkNr)
      throw new IllegalStateException("Too many links in virtual room");
  } // setLink

  /** allows no more than two links;
   * if already two links, returns -1
   */
  public int bestFreeExitTo(Room target) {
    if (linkNr() < maxLinkNr)
      return super.bestFreeExitTo(target);
    else
      return -1;
  } // bestFreeExitTo

  /** if exactly two exits are linked, returns the link that is
   * not equal to the given link;
   * if only one exit is linked, returns null
   * @param link must be one of the links connected to the room
   */
  public Link opposite(Link link) {
    if (linkNr() < maxLinkNr)
      return null;
    for (int i = 0; i < Dir.DIRNR; i++)
      if (exitLinked(i) && getLink(i) != link)
	return getLink(i);
    // this line should only be reached if all links equal link
    throw new IllegalStateException();
  } // opposite
    
  /** makes sure exit is always unblocked
   */
  protected void setExitBlocked(int dir, boolean blocked) {
    super.setExitBlocked(dir, false);
  } // setExitBlocked
    
  public Room cloneRoom(AreaMap map) {
    Room room = new VirtualRoom(map);
    room.deepCopyFrom(this);
    return room;
  } // cloneRoom

} // VirtualRoom
