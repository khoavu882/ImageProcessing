/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezxla;

import static ezxla.Duplicate.colorToRGB;
import static ezxla.PhotoModulator.minValue;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author maxiu
 */
public class Min {

    public void minFilter(BufferedImage img) { // image is < grayImageConverted >

        int width = img.getWidth();
        int height = img.getHeight();
        int alpha;
        int newPixel = 0;
     
        for (int y = 1; y < (height - 1); y++) {
            for (int x = 1; x < (width - 1); x++) {
                alpha = new Color(img.getRGB(x, y)).getAlpha();
                /*-------------------------------------------------*/
                // TODO : Now get value of Color (take RED) neighbourhood of (x,y)
                int A = new Color(img.getRGB(x - 1, y - 1)).getRed();
                int B = new Color(img.getRGB(x, y - 1)).getRed();
                int C = new Color(img.getRGB(x + 1, y - 1)).getRed();
                int D = new Color(img.getRGB(x + 1, y)).getRed();
                int E = new Color(img.getRGB(x + 1, y + 1)).getRed();
                int F = new Color(img.getRGB(x, y + 1)).getRed();
                int G = new Color(img.getRGB(x - 1, y - 1)).getRed();
                int H = new Color(img.getRGB(x - 1, y)).getRed();
                int center = new Color(img.getRGB(x, y)).getRed();

                int[] array = {center, A, B, C, D, E, F, G, H};
                int min = minValue(array);
                newPixel = colorToRGB(alpha, min, min, min);
                img.setRGB(x, y, newPixel);
            }
        }
    }
}
