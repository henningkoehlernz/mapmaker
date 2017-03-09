package mapmaker.mapcmd;

import java.awt.*;

public class CmdSetSize implements MapCommand {

  public Dimension dim;

  public CmdSetSize(Dimension dim) {
    this.dim = dim;
  } // CmdSetSize

} // CmdSetSize
