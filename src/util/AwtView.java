package util;

import java.awt.*;

public interface AwtView {

  /* paints to the given graphics context
   */
  public void paint(Graphics g);

  /* adds event listeneners to given component
   */
  public void addListeners(Component speaker);

  /* returns the view's preferred size
   */
  public Dimension getSize();

} // AwtView 
