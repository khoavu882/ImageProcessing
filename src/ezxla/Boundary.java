/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezxla;

import static ezxla.Duplicate.colorToRGB;
import static ezxla.EdgeDetection.calculateThresholding;
import static ezxla.Histogram.grayScale;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author maxiu
 */
public class Boundary {

    public void boudaryFilter(BufferedImage img) {

        BufferedImage originImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        BufferedImage erosionImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        int backgroundColor = 0;
        int ojectColor = 255;

        // To Morphology need Segmentation by Thresholding before
        grayScale(img);
        int T = calculateThresholding(img);

        int width = img.getWidth();
        int height = img.getHeight();

        int alpha = 0;
        int colorOrigin = 0;
        int colorErosioned = 0;
        int newPixel = 0;

        /**
         * **************** Image Origin *****************
         */
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                alpha = new Color(img.getRGB(x, y)).getAlpha();
                colorOrigin = new Color(img.getRGB(x, y)).getRed();

                if (colorOrigin > T && colorOrigin <= 255) {
                    newPixel = colorToRGB(alpha, 255, 255, 255);
                } else {
                    newPixel = colorToRGB(alpha, 0, 0, 0);
                }
                originImage.setRGB(x, y, newPixel);
            }
        }
        // Done segmentation image
        /**
         * **************** Image Origin *****************
         */

        /**
         * **************** Erosioned Origin *****************
         */
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                alpha = new Color(img.getRGB(x, y)).getAlpha();
                colorErosioned = new Color(img.getRGB(x, y)).getRed();

                if (colorErosioned > T && colorErosioned <= 255) {
                    newPixel = colorToRGB(alpha, 255, 255, 255);
                } else {
                    newPixel = colorToRGB(alpha, 0, 0, 0);
                }
                erosionImage.setRGB(x, y, newPixel);
            }
        }
        // Done segmentation image
        handleErosion(erosionImage);
        /**
         * **************** Erosioned Origin *****************
         */

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                alpha = new Color(originImage.getRGB(x, y)).getAlpha();

                colorOrigin = new Color(originImage.getRGB(x, y)).getRed();
                colorErosioned = new Color(erosionImage.getRGB(x, y)).getRed();

                if (colorOrigin == colorErosioned) {
                    newPixel = colorToRGB(alpha, backgroundColor, backgroundColor, backgroundColor);
                } else {
                    newPixel = colorToRGB(alpha, ojectColor, ojectColor, ojectColor);
                }
                img.setRGB(x, y, newPixel);
            }
        }
    }

    public void handleErosion(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int alpha = 0;
        int newPixel = 0;

        int[][] tempMatrix;
        int heightTemp = height + 2;
        int widthTemp = width + 2;
        tempMatrix = new int[heightTemp][widthTemp];
        PhotoModulator photoModulator = new PhotoModulator();
        photoModulator.setupTempMatrixNew(image, tempMatrix, heightTemp, widthTemp);

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

                boolean check = photoModulator.checkFitSpeacial(A, B, C, D, E, F, G, H, center);
                if (check == true) {
                    newPixel = colorToRGB(alpha, 255, 255, 255);
                    image.setRGB(x - 1, y - 1, newPixel);
                } else {
                    newPixel = colorToRGB(alpha, 0, 0, 0);
                    image.setRGB(x - 1, y - 1, newPixel);
                }
            }
        }
    }
}
