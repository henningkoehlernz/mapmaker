package mapmaker;

import util.*;

/** Concrete Description Object
 */
public class CDescObj 
  implements DescObj {

  private Text desc = new Text(); // ensure desc is never null

  public Text getDesc() {
    return (Text)desc.clone();
  } // getDesc

  public void setDesc(Text desc) {
    if (desc == null)
      this.desc = new Text();
    else
      this.desc = (Text)desc.clone();
  } // setDesc

} // CDescObj
