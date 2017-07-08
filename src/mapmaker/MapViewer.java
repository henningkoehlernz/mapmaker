package mapmaker;

import java.awt.*;

public interface MapViewer {

  public void paint(Graphics g);
  public void addListeners(Component speaker);
  public Dimension getSize();

} // MapViewer 
