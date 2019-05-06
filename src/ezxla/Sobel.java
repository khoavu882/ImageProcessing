/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezxla;

import static ezxla.LineDetection.getGrayScale;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author maxiu
 */
public class Sobel {

    public static int getGrayScale(Color c) {
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        int gray = (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
        return gray;
    }

    public void sobelFilter(BufferedImage img) {

        int w = img.getWidth();
        int h = img.getHeight();
        
        int[][] edgeColors = new int[w][h];

        for (int i = 1; i < w - 1; i++) {
            for (int j = 1; j < h - 1; j++) {

                int val00 = getGrayScale(new Color(img.getRGB(i - 1, j - 1)));
                int val01 = getGrayScale(new Color(img.getRGB(i - 1, j)));
                int val02 = getGrayScale(new Color(img.getRGB(i - 1, j + 1)));

                int val10 = getGrayScale(new Color(img.getRGB(i, j - 1)));
                int val11 = getGrayScale(new Color(img.getRGB(i, j)));
                int val12 = getGrayScale(new Color(img.getRGB(i, j + 1)));

                int val20 = getGrayScale(new Color(img.getRGB(i + 1, j - 1)));
                int val21 = getGrayScale(new Color(img.getRGB(i + 1, j)));
                int val22 = getGrayScale(new Color(img.getRGB(i + 1, j + 1)));

                int gx = ((-1 * val00) + (0 * val01) + (1 * val02))
                        + ((-2 * val10) + (0 * val11) + (2 * val12))
                        + ((-1 * val20) + (0 * val21) + (1 * val22));

                int gy = ((-1 * val00) + (-2 * val01) + (-1 * val02))
                        + ((0 * val10) + (0 * val11) + (0 * val12))
                        + ((1 * val20) + (2 * val21) + (1 * val22));

                double gradientValue = Math.sqrt((gx * gx) + (gy * gy));
                int g = (int) gradientValue;
                edgeColors[i][j] = g;
                
            }
        }
        for (int i = 1; i < w - 1; i++) {
            for (int j = 1; j < h - 1; j++) {
        
            int rgb = edgeColors[i][j];
            if(rgb > 255) rgb = 255;
            if(rgb < 0) rgb = 0;
            img.setRGB(i, j, new Color(rgb, rgb, rgb).getRGB());
            
            }
        }
    }
}
