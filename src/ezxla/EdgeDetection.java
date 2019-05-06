/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezxla;

import java.awt.Color;
import java.awt.List;
import java.awt.image.BufferedImage;

/**
 *
 * @author maxiu
 */
//
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class EdgeDetection {

    public static int getGrayScale(Color c) {
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        int gray = (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
        return gray;
    }

    public void edgeDetectionFilter(BufferedImage img) {

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

                int gx = ((-1 * val00) + (0 * val01) + (1 * val02))
                        + ((-2 * val10) + (0 * val11) + (2 * val12))
                        + ((-1 * val20) + (0 * val21) + (1 * val22));

                int gy = ((-1 * val00) + (-2 * val01) + (-1 * val02))
                        + ((0 * val10) + (0 * val11) + (0 * val12))
                        + ((1 * val20) + (2 * val21) + (1 * val22));

                double gradientValue = Math.sqrt((gx * gx) + (gy * gy));
                int g = (int) gradientValue;
                rgb[i][j] = g;
            }
        }

        for (int i = 1; i < w - 1; i++) {
            for (int j = 1; j < h - 1; j++) {
                
                int newRGB = rgb[i][j];
                if (newRGB > 255) {
                    newRGB = 255;
                }
                if (newRGB < 0) {
                    newRGB = 0;
                }
                img.setRGB(i, j, new Color(newRGB, newRGB, newRGB).getRGB());

            }
        }
        int t = calculateThresholding(img);
        for (int i = 1; i < w - 1; i++) {
            for (int j = 1; j < h - 1; j++) {
                
                int newRGB = rgb[i][j];
                if (newRGB > t) {
                    newRGB = 255;
                } else {
                    newRGB = 0;
                }
                img.setRGB(i, j, new Color(newRGB, newRGB, newRGB).getRGB());
            }
        }
    }
    
    // black and white processing
    public static int calculateThresholding(BufferedImage img) {
        int t2;
        int t1 = calcAveraferGraylevel(img);
        while (true) {
            ArrayList<Integer>[] g = generateGroupPixel(img, t1);
            ArrayList<Integer> g1 = g[0];
            ArrayList<Integer> g2 = g[1];
            int sum1 = 0;
            for (int i : g1) {
                sum1 += i;
            }
            int u1 = (int) sum1 / g1.size();
            int sum2 = 0;
            for (int i : g2) {
                sum2 += i;
            }
            int u2 = (int) sum1 / g2.size();
            double temp = (u1 + u2) / 2;
            t2 = (int) Math.round(temp);
            if (t1 == t2) {
                break;
            }
            t1 = t2;
        }
        return t1;
    }

    // trung binh color image
    public static int calcAveraferGraylevel(BufferedImage img) {
        int sum = 0;
        int w = img.getWidth();
        int h = img.getHeight();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int val11 = getGrayScale(new Color(img.getRGB(i, j)));
                sum += val11;
            }
        }
        return sum / (img.getWidth() * img.getHeight());
    }

    public static ArrayList<Integer>[] generateGroupPixel(BufferedImage img, int T) {
        ArrayList<Integer> g1 = new ArrayList<Integer>();
        ArrayList<Integer> g2 = new ArrayList<Integer>();
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                int val11 = getGrayScale(new Color(img.getRGB(i, j)));
                if (val11 > T) {
                    g1.add(val11);
                } else if (val11 <= T) {
                    g2.add(val11);
                }
            }
        }
        ArrayList<Integer>[] temp = new ArrayList[2];
        temp[0] = new ArrayList<>();
        temp[1] = new ArrayList<>();
        temp[0] = g1;
        temp[1] = g2;
        return temp;
    }
}
