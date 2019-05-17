/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezxla;

import static ezxla.Duplicate.colorToRGB;
import static ezxla.Histogram.grayScale;
import static ezxla.PhotoModulator.checkFit;
import static ezxla.PhotoModulator.setupTempMatrixNew;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author maxiu
 */
public class Erosion {

    public void erosionFilter(BufferedImage img) {


        // To Morphology need Segmentation by Thresholding before
        grayScale(img);
//		int T = calculatorThresholding(image);
        int T = 110;

        int width = img.getWidth();
        int height = img.getHeight();

        int alpha = 0;
        int color = 0;
        int newPixel = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                alpha = new Color(img.getRGB(x, y)).getAlpha();
                color = new Color(img.getRGB(x, y)).getRed();

                if (color > T && color <= 255) {
                    newPixel = colorToRGB(alpha, 0, 0, 0);//
                } else {
                    newPixel = colorToRGB(alpha, 255, 255, 255);
                }
                img.setRGB(x, y, newPixel);
            }
        }
        // Done segmentation image

        int[][] tempMatrix;
        int heightTemp = height + 2;
        int widthTemp = width + 2;
        tempMatrix = new int[heightTemp][widthTemp];
        
        setupTempMatrixNew(img, tempMatrix, heightTemp, widthTemp);

        for (int y = 1; y < (heightTemp - 1); y++) {
            for (int x = 1; x < (widthTemp - 1); x++) {

                alpha = tempMatrix[y][x];
                // TODO : Now get value of Color (take RED) neighbourhood of (x,y)

                int A = tempMatrix[y - 1][x - 1];
                int B = tempMatrix[y - 1][x];
                int C = tempMatrix[y - 1][x + 1];
                int D = tempMatrix[y][x + 1];
                int E = tempMatrix[y + 1][x + 1];
                int F = tempMatrix[y + 1][x];
                int G = tempMatrix[y + 1][x - 1];
                int H = tempMatrix[y][x - 1];
                int center = tempMatrix[y][x];

                boolean check = checkFit(A, B, C, D, E, F, G, H, center);
                if (check == true) {
                    newPixel = colorToRGB(alpha, 255, 255, 255);
                    img.setRGB(x - 1, y - 1, newPixel);
                } else {
                    newPixel = colorToRGB(alpha, 0, 0, 0);
                    img.setRGB(x - 1, y - 1, newPixel);
                }
            }
        }
    }
}
