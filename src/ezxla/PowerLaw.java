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
public class PowerLaw {

    public static void powerLawTransFormation(double c, double lamda, BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color color = new Color(img.getRGB(col, row));
                double value = color.getRed();
                double r = value / 255;
                double newValue = c * Math.pow(r, lamda)*255.0;
                //int gray = (int) c * (int) (255 * (Math.pow((double) c.getRed() / (double) 255, value2)));
              
                Color newColor = new Color((int)newValue, (int)newValue, (int)newValue);
                img.setRGB(col, row, newColor.getRGB());
            }
        }
    }
}
