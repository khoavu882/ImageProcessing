/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezxla;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author maxiu
 */
public class GrayLevel {

    protected static void grayScale(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color c = new Color(img.getRGB(col, row));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                Color newColor = new Color(red + green + blue, red + green + blue, red + green + blue);
                img.setRGB(col, row, newColor.getRGB());
            }
        }

    }

    public static void grayLevelSlicing(int value1, int value2, BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color c = new Color(img.getRGB(col, row));
                int gray = c.getRed();
                if (gray >= value1 && gray <= value2) {
                    gray = 255;
                } else {
                    gray = 0;
                }
                Color newc = new Color(gray, gray, gray);
                img.setRGB(col, row, newc.getRGB());
            }
        }
    }
}
