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
public class LogTrans {

    public static void logTransformations(double value, BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color c = new Color(img.getRGB(col, row));
                int gray = c.getRed();
                gray = (int) Math.round((int) value * Math.log10((double) gray) + 1);// ham log
                if (gray > 255) {
                    gray = 255;
                }
                //if(gray>1) gray = 1;
                Color newc = new Color(gray, gray, gray);
                img.setRGB(col, row, newc.getRGB());
            }
        }
    }
}
