/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezxla;

import static ezxla.PointDetection.getGrayScale;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

/**
 *
 * @author maxiu
 */
public class Morphological {
        
    public void hittingFilter(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();

        int[][] rgb = new int[w][h];

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

                int gx = ((-1 * val00) + (-1 * val01) + (-1 * val02))
                        + ((-1 * val10) + (8 * val11) + (-1 * val12))
                        + ((-1 * val20) + (-1 * val21) + (-1 * val22));

                
                rgb[i][j] = gx;
            }
        }

        for (int i = 1; i < w - 1; i++) {
            for (int j = 1; j < h - 1; j++) {
        
            int newRGB = rgb[i][j];
            if(newRGB > 255) newRGB = 255;
            if(newRGB < 0) newRGB = 0;
            img.setRGB(i, j, new Color(newRGB, newRGB, newRGB).getRGB());
            
            }
        }
    }
}
