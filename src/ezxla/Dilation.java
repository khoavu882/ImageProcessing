/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezxla;

import static ezxla.Duplicate.colorToRGB;
import static ezxla.Histogram.grayScale;
import static ezxla.LineDetection.getGrayScale;
import static ezxla.PhotoModulator.checkHit;
import static ezxla.PhotoModulator.setupTempMatrixNew;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Dilation filter is used for making lines on the image little bit wider. It
 * convolves through whole image and every black pixel replaces with 9 pixels.
 *
 * @author Mihailo Stupar
 */
public class Dilation {

    public void drawDilation(BufferedImage img) {

        BufferedImage resultImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

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
                resultImage.setRGB(x, y, newPixel);
            }
        }
        // Done segmentation image

        int[][] tempMatrix;
        int heightTemp = height + 2;
        int widthTemp = width + 2;
        tempMatrix = new int[heightTemp][widthTemp];
        
        setupTempMatrixNew(resultImage, tempMatrix, heightTemp, widthTemp);

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
                int pointCenter = tempMatrix[y][x];

                boolean check = checkHit(A, B, C, D, E, F, G, H, pointCenter);
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
//    public void drawDilation(BufferedImage img) {
//
//        int w = img.getWidth();
//        int h = img.getHeight();
//
//        int[][] rgb = new int[w][h];
//
//        for (int i = 1; i < w - 1; i++) {
//            for (int j = 1; j < h - 1; j++) {
//
//                int val00 = getGrayScale(new Color(img.getRGB(i - 2, j + 2)));
//                int val01 = getGrayScale(new Color(img.getRGB(i - 1, j + 2)));
//                int val02 = getGrayScale(new Color(img.getRGB(i, j + 2)));
//                int val03 = getGrayScale(new Color(img.getRGB(i + 1, j + 2)));
//                int val04 = getGrayScale(new Color(img.getRGB(i + 2, j + 2)));
//
//                int val10 = getGrayScale(new Color(img.getRGB(i + 2, j + 1)));
//                int val11 = getGrayScale(new Color(img.getRGB(i + 1, j - 1)));
//                int val12 = getGrayScale(new Color(img.getRGB(i, j + 1)));
//                int val13 = getGrayScale(new Color(img.getRGB(i + 1, j + 1)));
//                int val14 = getGrayScale(new Color(img.getRGB(i + 2, j + 1)));
//
//                int val20 = getGrayScale(new Color(img.getRGB(i - 2, j)));
//                int val21 = getGrayScale(new Color(img.getRGB(i - 1, j)));
//                int val22 = getGrayScale(new Color(img.getRGB(i, j)));
//                int val23 = getGrayScale(new Color(img.getRGB(i + 1, j)));
//                int val24 = getGrayScale(new Color(img.getRGB(i + 2, j)));
//
//                int val30 = getGrayScale(new Color(img.getRGB(i - 2, j - 1)));
//                int val31 = getGrayScale(new Color(img.getRGB(i - 1, j - 1)));
//                int val32 = getGrayScale(new Color(img.getRGB(i, j - 1)));
//                int val33 = getGrayScale(new Color(img.getRGB(i + 1, j - 1)));
//                int val34 = getGrayScale(new Color(img.getRGB(i + 2, j - 1)));
//
//                int val40 = getGrayScale(new Color(img.getRGB(i - 2, j + 2)));
//                int val41 = getGrayScale(new Color(img.getRGB(i - 1, j - 2)));
//                int val42 = getGrayScale(new Color(img.getRGB(i, j - 2)));
//                int val43 = getGrayScale(new Color(img.getRGB(i + 1, j - 2)));
//                int val44 = getGrayScale(new Color(img.getRGB(i + 2, j - 2)));
//
//                int gx = ((0 * val00) + (val01) + (val02) + (val03) + (0 * val04))
//                        + ((val10) + (val11) + (val12) + (val13) + (val14))
//                        + ((val20) + (val21) + (val22) + (val23) + (val24))
//                        + ((val00) + (val01) + (val02) + (val33) + (val34))
//                        + ((0 * val00) + (val01) + (val02) + (val43) + (0 * val44));
//
//                rgb[i][j] = gx;
//            }
//        }
//        for (int i = 1; i < w - 1; i++) {
//            for (int j = 1; j < h - 1; j++) {
//
//                int newRGB = rgb[i][j];
//                if (newRGB > 255) {
//                    newRGB = 255;
//                }
//                if (newRGB < 0) {
//                    newRGB = 0;
//                }
//                img.setRGB(i, j, new Color(newRGB, newRGB, newRGB).getRGB());
//
//            }
//        }
//    }

    
}
