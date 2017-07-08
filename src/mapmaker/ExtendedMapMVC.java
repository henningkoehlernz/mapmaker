package mapmaker;

import javax.swing.*;

public class ExtendedMapMVC extends MapMVC {

  public MapGraphics mapGraphics;
  
  public ExtendedMapMVC(AreaMap model, 
			JComponent view, 
			MapEventHandler controller,
			MapGraphics mapGraphics) {
    super(model, view, controller);
    this.mapGraphics = mapGraphics;
  } // ExtendedMapMVC

} // ExtendedMapMVC
