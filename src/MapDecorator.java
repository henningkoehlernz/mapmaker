package mapmaker;

import java.awt.*;

public abstract class MapDecorator implements Map {

  Map map;

  MapDecorator(Map map) {
    this.map = map;
  } // MapDecorator

  public void newRoom(Point pos) {
    map.newRoom(pos);
  } // newRoom
  
  public void killRoom(Point pos) {
    map.killRoom(pos);
  } // killRoom
  
  public void selectRoom(Point pos) {
    map.selectRoom(pos);
  } // selectRoom
  
  public void unselectRoom() {
    map.unselectRoom();
  } // unselectRoom
  
  public void swapRooms(Point pos1, Point pos2) {
    map.swapRooms(pos1, pos2);
  } // swapRooms
  
  public void linkRooms(Point pos1, Point pos2) {
    map.linkRooms(pos1, pos2);
  } // linkRooms

  public Room getRoom(Point pos) {
    return map.getRoom(pos);
  } // getRoom

  public Point getRoomPos(Room room) {
    return map.getRoomPos(room);
  } // getRoomPos

  public Room getSelected() {
    return map.getSelected();
  } // getSelected

  public Room[] getRooms() {
    return map.getRooms();
  } // getRooms

  public Link[] getLinks() {
    return map.getLinks();
  } // getLinks

  public Dimension getSize() {
    return map.getSize();
  } // getSize

  public void removeLink(Link link) {
    map.removeLink(link);
  } // removeLink
 
  public void removeRoom(Room room) {
    map.removeRoom(room);
  } // removeRoom

  public void notifyOfChange() {
    map.notifyOfChange();
  } // notifyOfChange

} // MapDecorator


