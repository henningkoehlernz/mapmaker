package util;

import java.awt.*;

public class NamedColor
  extends Color {
  
  protected String name;

  public NamedColor(String name, Color color) {
    super(color.getRGB());
    this.name = name;
  }

  /* returns the color's name
   */
  public String toString() {
    return name;
  }

  public static final NamedColor[] colors = {
    new NamedColor("black", Color.black),
    new NamedColor("blue", Color.blue),
    new NamedColor("cyan", Color.cyan),
    new NamedColor("darkGray", Color.darkGray),
    new NamedColor("gray", Color.gray),
    new NamedColor("green", Color.green),
    new NamedColor("lightGray", Color.lightGray),
    new NamedColor("magenta", Color.magenta),
    new NamedColor("orange", Color.orange),
    new NamedColor("pink", Color.pink),
    new NamedColor("red", Color.red),
    new NamedColor("white", Color.white),
    new NamedColor("yellow", Color.yellow)
      };

  /* returns the color from colors with the given name
   */
  public static NamedColor parseName(String name) {
    // search for color
    for (int i = 0; i < colors.length; i++)
      if (colors[i].name.equals(name))
	return colors[i];
    // not found ..
    return null;
  } // parseName

} // NamedColor


