package mapmaker;

import javax.swing.*;

public class MapMVC {

  public AreaMap model;
  public JComponent view;
  public MapEventHandler controller;

  public MapMVC(AreaMap model, JComponent view, MapEventHandler controller) {
    this.model = model;
    this.view = view;
    this.controller = controller;
  } // MapMVC

} // MapMVC
