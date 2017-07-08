package mapmaker;

public class MapDescParser {

  public static final String REM_STRING = "/";
  public static final String CMD_STRING = "#";

  private static DescParser mapDescParser;

  public static DescParser get() {
    if (mapDescParser == null) {
      mapDescParser = new DescParser();
      mapDescParser.addCommentString(REM_STRING);
      mapDescParser.addCommentString(CMD_STRING);
    }
    return mapDescParser;
  } // get

} // MapDescParser
