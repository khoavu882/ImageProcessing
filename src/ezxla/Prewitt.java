/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezxla;

import static ezxla.Duplicate.colorToRGB;
import static ezxla.EdgeDetection.calculateThresholding;
import static ezxla.PhotoModulator.calculatorPrewittHorizontal;
import static ezxla.PhotoModulator.calculatorPrewittVertival;
import static ezxla.PhotoModulator.setupTempMatrixNew;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author maxiu
 */
public class Prewitt {

    public void prewittFilter(BufferedImage img) {

        int width = img.getWidth();
        int height = img.getHeight();
        int newPixel = 0;
        int value1;
        int value2;
        int value;
        int alpha;

        int[][] tempMatrix;
        int heightTemp = height + 2;
        int widthTemp = width + 2;
        tempMatrix = new int[heightTemp][widthTemp];
        setupTempMatrixNew(img, tempMatrix, heightTemp, widthTemp);

        for (int y = 1; y < (height - 1); y++) {
            for (int x = 1; x < (width - 1); x++) {

                alpha = new Color(img.getRGB(x, y)).getAlpha();

                // TODO : Now get matrix around pixel
                int A = new Color(img.getRGB(x - 1, y - 1)).getRed();
                int B = new Color(img.getRGB(x, y - 1)).getRed();
                int C = new Color(img.getRGB(x + 1, y - 1)).getRed();
                int D = new Color(img.getRGB(x - 1, y)).getRed();
                int E = new Color(img.getRGB(x + 1, y + 1)).getRed();
                int F = new Color(img.getRGB(x, y + 1)).getRed();
                int G = new Color(img.getRGB(x - 1, y - 1)).getRed();
                int H = new Color(img.getRGB(x - 1, y)).getRed();
                int center = new Color(img.getRGB(x, y)).getRed();

                value1 = calculatorPrewittHorizontal(A, B, C, D, E, F, G, H, center);
                value2 = calculatorPrewittVertival(A, B, C, D, E, F, G, H, center);

                value = (int) Math.sqrt(Math.pow(value1, 2) + Math.pow(value2, 2));
                newPixel = colorToRGB(alpha, value, value, value);
                img.setRGB(x, y, newPixel);
            }
        }
        int T = calculateThresholding(img);  // tinh T
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                alpha = new Color(img.getRGB(x, y)).getAlpha();
                newPixel = new Color(img.getRGB(x, y)).getRed();
                if (newPixel > T) {
                    newPixel = colorToRGB(alpha, 255, 255, 255);
                    img.setRGB(x, y, newPixel);
                } else {
                    newPixel = colorToRGB(alpha, 0, 0, 0);
                    img.setRGB(x, y, newPixel);
                }
            }
        }
    }
}
