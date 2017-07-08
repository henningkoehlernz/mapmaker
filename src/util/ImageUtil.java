package util;

import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

public class ImageUtil {

  /** saves an image as png file
   * @param image the image to be saved
   * @param file the png file
   */
  public static void saveImageAsPNG(BufferedImage image, File file) {
    try {
      ImageIO.write(image, "png", file);
    } catch (IOException e) {
      System.out.println(e);
    }
  }

} // ImageUtil
