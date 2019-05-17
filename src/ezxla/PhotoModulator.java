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
public class PhotoModulator {

    public static int maxValue(int[] array) {
        int max = array[0];
        for (int i = 1; i < 8; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static int minValue(int[] array) {     //find min number
        int min = array[0];
        for (int i = 1; i < 8; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static void setupTempMatrixNew(BufferedImage image, int[][] tempMatrix, int heightTemp, int widthTemp) { // Correctly

        int width = image.getWidth();
        int height = image.getHeight();
        int tempVal = 0;

        for (int y = 0; y < heightTemp; y++) {
            for (int x = 0; x < widthTemp; x++) {
                tempMatrix[y][x] = 0;
            }
        }

        // TODO : Setup for corner < Correctly >
        for (int y = 0; y < heightTemp; y++) {
            for (int x = 0; x < widthTemp; x++) {

                if (x == 0 && y == 0) {
                    tempVal = new Color(image.getRGB(0, 0)).getRed();
                    tempMatrix[y][x] = tempVal;
                }
                if (y == 0 && x == widthTemp - 1) {
                    tempVal = new Color(image.getRGB(width - 1, 0)).getRed();
                    tempMatrix[y][x] = tempVal;
                }
                if (y == heightTemp - 1 && x == widthTemp - 1) {
                    tempVal = new Color(image.getRGB(width - 1, height - 1)).getRed();
                    tempMatrix[y][x] = tempVal;
                }
                if (y == heightTemp - 1 && x == 0) {
                    tempVal = new Color(image.getRGB(0, height - 1)).getRed();
                    tempMatrix[y][x] = tempVal;
                }
            }
        }
        // TODO : Setup for border
        for (int y = 0; y < heightTemp; y++) {
            for (int x = 0; x < widthTemp; x++) {

                if (y == 0) {
                    if (x > 0 && x < widthTemp - 1) {
                        tempVal = new Color(image.getRGB(x - 1, 0)).getRed();
                        tempMatrix[y][x] = tempVal;
                    }
                }
                if (x == 0) {
                    if (y > 0 && y < heightTemp - 1) {
                        tempVal = new Color(image.getRGB(0, y - 1)).getRed();
                        tempMatrix[y][x] = tempVal;
                    }
                }
                if (y == (heightTemp - 1)) {
                    if (x > 0 && x < widthTemp - 1) {
                        tempVal = new Color(image.getRGB(x - 1, height - 1)).getRed();
                        tempMatrix[y][x] = tempVal;
                    }
                }
                if (x == (widthTemp - 1)) {
                    if (y > 0 && y < heightTemp - 1) {
                        tempVal = new Color(image.getRGB(width - 1, y - 1)).getRed();
                        tempMatrix[y][x] = tempVal;
                    }
                }
            }
        }

        // FOR INSIDE MATRIX NOT IS BORDER
        for (int y = 1; y < heightTemp - 1; y++) {
            for (int x = 1; x < widthTemp - 1; x++) {
                tempMatrix[y][x] = new Color(image.getRGB(x - 1, y - 1)).getRed();
            }
        }

        // Show Test TempMatrix <Correctly>
//        for (int y = 0 ; y < heightTemp ; y++) {
//        	for (int x = 0 ; x < widthTemp ; x++) {
//        		System.out.print(tempMatrix[y][x] + " ");
//        	}System.out.print("\n");
//        }
    }

    public static boolean checkFitSpeacial(int a, int b, int c, int d, int e, int f, int g, int h, int i) {

        boolean check = false;
        if (a == 255 && b == 255 && c == 255 && d == 255 && e == 255 && f == 255 && g == 255 && h == 255 && i == 255) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }

    public static boolean checkFit(int a, int b, int c, int d, int e, int f, int g, int h, int i) {

        boolean check = false;
        if (b == 255 && d == 255 && f == 255 && h == 255 && i == 255) {
            check = true;
        }

        if (a == 0 && b == 0 && c == 0 && d == 0 && e == 0 && f == 0 && g == 0 && h == 0 && i == 0) {
            check = false;
        }

        if (b == 0 || d == 0 || f == 0 || h == 0 || i == 0) {
            check = false;
        }
        return check;
    }

    /**
     * **********************************************************************************
     */
    public static boolean checkHit(int a, int b, int c, int d, int e, int f, int g, int h, int i) {

        boolean check = false;
        if (b == 255 || d == 255 || f == 255 || h == 255 || i == 255) {
            check = true;
        }

        if (a == 0 && b == 0 && c == 0 && d == 0 && e == 0 && f == 0 && g == 0 && h == 0 && i == 0) {
            check = false;
        }
        return check;
    }

    /**
     * **********************************************************************************
     */
    public static boolean checkHitSpeacial(int a, int b, int c, int d, int e, int f, int g, int h, int i) {

        boolean check = false;
        if (a == 255 || b == 255 || c == 255 || d == 255 || e == 255 || f == 255 || g == 255 || h == 255 || i == 255) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }

    public static int calculatorPrewittHorizontal(int a, int b, int c, int d, int e, int f, int g, int h, int i) {
        int ret = 0;
        ret = (-1) * a + (-1) * b + (-1) * c + 0 * d + 1 * e + 1 * f + 1 * g + 0 * h + 0 * i;
        if (ret < 0) {
            ret = 0;
        }
        if (ret > 255) {
            ret = 255;
        }
        return ret;
    }

    public static int calculatorPrewittVertival(int a, int b, int c, int d, int e, int f, int g, int h, int i) {
        
        int ret = 0;
        ret = (-1) * a + 0 * b + 1 * c + 1 * d + 1 * e + 0 * f + (-1) * g + (-1) * h + 0 * i;
        if (ret < 0) {
            ret = 0;
        }
        if (ret > 255) {
            ret = 255;
        }
        return ret;
    }
}
