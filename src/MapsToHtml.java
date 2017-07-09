import java.io.*;
import java.util.*;
import java.text.*;
import java.awt.image.*;

import util.*;
import mapmaker.*;

/** creates an html file from the given maps
 */
public class MapsToHtml {

  static final String imageExtension = "png";
  static final String mapExtension = "map";

  static Text htmlText = new Text();

  public static void main(String[] args) {
    // get file names
    String syntax = "parameters: map1 {map2 ..} htmlfile";
    if (args.length < 2) {
      System.out.println(syntax);
      return;
    }
    String[] mapFileNames = new String[args.length - 1];
    for (int i = 0; i < args.length - 1; i++)
      mapFileNames[i] = args[i];
    String htmlFileName = args[args.length - 1];
    File htmlFile = new File(htmlFileName);
    if (htmlFile.exists() && 
	FileUtil.getExtension(htmlFile.getName()).equals(mapExtension)) {
      System.out.println(htmlFileName + 
			 " probably a map file, not overwriting");
      System.out.println(syntax);
      return;
    }

    // create map data
    Vector<MapData> mapDataVector = new Vector<MapData>();
    for (int i = 0; i < mapFileNames.length; i++)
      mapDataVector.add(createImageAndData(mapFileNames[i]));
    // sort it
    Collections.sort(mapDataVector, new MapDataComparator());
    MapData[] mapDataArray = new MapData[mapFileNames.length];
    mapDataVector.copyInto(mapDataArray);

    // create html text
    System.out.println("creating " + htmlFileName);
    addHeader();
    addReferenceTable(mapDataArray);
    addMaps(mapDataArray);
    addHtmlEnd();
    // save text to file
    TextFileHandler.saveText(htmlText, htmlFile);
  } // main

  static MapData createImageAndData(String mapFileName) {
    MapData mapData = new MapData();
    mapData.imageFileName = 
      FileUtil.substituteExtension(mapFileName, imageExtension);
    String buf = new File(mapFileName).getName();
    mapData.refName = StringUtil.splitString(buf, '.')[0];
    // read in map, extract needed data and create image file
    Text input = TextFileHandler.loadText(new File(mapFileName));
    System.out.println("parsing " + mapFileName + " ...");
    AreaMap map = new MapParser().createMap(input);
    // parse map description
    mapData.mapDesc = MapDescParser.get().filterDesc(map.getDesc());
    if (mapData.mapDesc.getLineCount() == 0)
      mapData.mapDesc.append(mapData.refName);
    mapData.mapName = MapDescParser.get().getName(mapData.mapDesc);

    // create image file if doesn't exist already
    File imageFile = new File(mapData.imageFileName);
    if (!imageFile.exists()) {
      MapImagePainter mip = new MapImagePainter(map);
      mip.layout(MapImagePainter.LEGEND_RIGHT);
      BufferedImage mapImage = mip.paintMapImage();
      if (mapImage != null) {
        System.out.println("creating " + mapData.imageFileName + " ...");
        ImageUtil.saveImageAsPNG(mapImage, imageFile);
      }
    }

    return mapData;
  } // createImageAndData

  static void addHeader() {
    String title = "Maps";
    htmlText.append("<HTML>");
    htmlText.append("<HEAD><TITLE> " + title + " </TITLE></HEAD>");
    htmlText.append("<BODY>");
    htmlText.append("<H1><CENTER> " + title + " </CENTER></H1>");
    htmlText.append("<HR>");
  } // addHeader

  static void addReferenceTable(MapData[] mapData) {
    int collumnNr = 8;
    htmlText.append("<TABLE col=" + collumnNr + "> <TR>");
    for (int i = 0; i < mapData.length; i++) {
      htmlText.append("<TD>");
      htmlText.append("<A HREF=\"#" + mapData[i].refName + "\"> " +
		      mapData[i].mapName + " </A>");
      htmlText.append("</TD>");
      // start a new line in table if necessary
      if ((i + 1) % collumnNr == 0)
	htmlText.append("</TR> <TR>");
    }
    htmlText.append("</TABLE>");
    htmlText.append("<HR>");
  } // addReferenceTable

  static void addMaps(MapData[] mapData) {
    htmlText.append("<H2>");
    for (int i = 0; i < mapData.length; i++) {
      htmlText.append("<A NAME=\"" + mapData[i].refName + "\">" +
		      "<CENTER> " + mapData[i].mapName + "</CENTER></A>");
      for (int line = 1; line < mapData[i].mapDesc.getLineCount(); line++)
	htmlText.append("<CENTER> " + mapData[i].mapDesc.getLine(line) + 
			"</CENTER>");
      String imageFileName = new File(mapData[i].imageFileName).getName();
      htmlText.append("<CENTER><IMG SRC=\"" + imageFileName + 
		      "\"></CENTER>");
      htmlText.append("<HR>");
    }
    htmlText.append("</H2>");
  } // addMaps

  static void addHtmlEnd() {
    htmlText.append("</BODY>");
    htmlText.append("</HTML>");
  } // addHtmlEnd

} // MapsToHtml


class MapData {
  String
    mapName, // is the first line of mapDesc
    imageFileName,
    refName;
  Text
    mapDesc;
} // MapData


class MapDataComparator implements Comparator<MapData> {
  
  public int compare(MapData o1, MapData o2) {
    return Collator.getInstance().compare(o1.mapName, o2.mapName);
  } // compare
  
} // MapDataComparator



