package mapmaker;

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.text.*;

import util.*;
import mapmaker.mapcmd.*;

public class MapImagePainter {

  public static final int 
    LEGEND_RIGHT = CollumnPrinter.STYLE_MATCH_HEIGHT,
    LEGEND_BELOW = CollumnPrinter.STYLE_MATCH_WIDTH;

  protected AreaMap map;
  protected MapGraphics mg;
  protected CollumnPrinter legendPrinter;
  /** stores all room names in the order as they will appear
   * in the legend
   */
  protected Vector<String> legend;
  protected int layoutStyle;

  protected static Color
    backgroundColor = Color.lightGray,
    mapColor = Color.gray,
    legendColor = Color.black,
    legendNrColor = Color.black;

  public MapImagePainter(AreaMap mapData) {
    // init map
    map = new CMap(0, 0, new MyObservable());
    map.deepCopyFrom(mapData);
    // init mg
    mg = new MapGraphics(map, null);
    mg.setPaintSize(3);
    // init cp
    legendPrinter = new CollumnPrinter(200, 12);
  } // MapImagePainter

  /** returns a legend text for the map, based on the contents of
   * the legend variable
   */
  protected Text createLegendText() {
    Text legendText = new Text();
    for (int i = 0; i < legend.size(); i++)
      legendText.append(i + " " + legend.elementAt(i));
    return legendText;
  } // createLegendText

  protected void createLegend() {
    legend = new Vector<String>();
    Room[] rooms = map.getRooms();
    for (int i = 0; i < rooms.length; i++)
      if (rooms[i] instanceof DescObj) {
	String roomName = 
	  MapDescParser.get().getName(((DescObj)rooms[i]).getDesc());
	if (roomName.length() > 0 && legend.indexOf(roomName) == -1)
	  legend.add(roomName);
      }
    // sort the legend alphabetically
    Collections.sort(legend, Collator.getInstance());
  } // createLegend

  /** returns the size (in pixels) the map image takes
   */
  protected Dimension getSize() {
    Dimension mapSize = mg.getSize();
    Dimension legendSize = legendPrinter.getSize();
    Dimension totalSize = new Dimension(0, 0);

    if (layoutStyle == LEGEND_BELOW) {
      totalSize.width = Math.max(mapSize.width, legendSize.width);
      totalSize.height = mapSize.height + legendSize.height;
    }
    else {
      totalSize.width = mapSize.width + legendSize.width;
      totalSize.height = Math.max(mapSize.height, legendSize.height);
    }

    return totalSize;
  } // getSize

  public void layout(int style) {
    // store style
    layoutStyle = style;
    // prepare map
    map.execute(new CmdSetSize(new Dimension(0, 0)));
    MapUtil.unmarkRooms(map);
    map.execute(new CmdUnselectRoom());
    // create and layout legend
    createLegend();
    legendPrinter.setText(createLegendText());
    int size;
    if (style == CollumnPrinter.STYLE_MATCH_WIDTH)
      size = mg.getSize().width;
    else
      size = mg.getSize().height;
    legendPrinter.layout(style, size);
  } // layout

  /** paints the map into an image and returns it;
   * if nothing there to be painted (no rooms), returns null
   */
  public BufferedImage paintMapImage() {
    Dimension dim = getSize();
    // ensure that map dimension isn't 0
    if (dim.width == 0 || dim.height == 0)
      return null;
    BufferedImage image = new BufferedImage(dim.width, dim.height, 
				    BufferedImage.TYPE_INT_RGB);
    Graphics g = image.createGraphics();
    g.setColor(backgroundColor);
    g.fillRect(0, 0, dim.width, dim.height);
    // paint map
    g.setColor(mapColor);
    mg.paint(g);
    // paint legend
    // move relative graphics position according to style before painting
    int dx = 0;
    int dy = 0;
    if (layoutStyle == LEGEND_BELOW)
      dy = mg.getSize().height;
    else
      dx = mg.getSize().width;
    g.translate(dx, dy);
    g.setColor(legendColor);
    legendPrinter.print(g);
    g.translate(-dx, -dy);
    // paint the numbers on the map
    g.setColor(legendNrColor);
    paintLegendNumbers(g);
    return image;
  } // paintMapImage

  /** paints the numbers that are mapped to the room names on the map
   */
  protected void paintLegendNumbers(Graphics g) {
    Font orgFont = g.getFont();
    g.setFont(new Font("SansSerif", Font.BOLD, 9));
    Room[] rooms = map.getRooms();
    for (int i = 0; i < rooms.length; i++) {
      Room room = rooms[i];
      if (room instanceof DescObj) {
	String roomName = 
	  MapDescParser.get().getName(((DescObj)room).getDesc());
	if (roomName.length() > 0) {
	  int legendNr = legend.indexOf(roomName);
	  if (legendNr == -1)
	    throw new IllegalStateException("room name not in legend");
	  Point paintPos = mg.roomPaintPos(room);
	  paintPos.y += (mg.sizePerRoom() + 1) / 2;
	  g.drawString(legendNr + "", paintPos.x + 3, paintPos.y + 3);
	}
      }
    }
    g.setFont(orgFont);
  } // PaintLegendNumbers

} // MapImagePainter




