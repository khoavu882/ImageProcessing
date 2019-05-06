/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezxla;

/**
 *
 * @author maxiu
 */
import static ezxla.LineDetection.getGrayScale;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Yousra
 */
public class Averaging {

    public void averagingFilter(BufferedImage img) {
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

                int gx = (val00 + val01 + val02 + val10 + val11 + val12 + val20 + val21 + val22) / 9;

                edgeColors[i][j] = gx;
            }
        }
        for (int i = 1; i < w - 1; i++) {
            for (int j = 1; j < h - 1; j++) {

                int rgb = edgeColors[i][j];
                if (rgb > 255) {
                    rgb = 255;
                }
                if (rgb < 0) {
                    rgb = 0;
                }
                img.setRGB(i, j, new Color(rgb, rgb, rgb).getRGB());
            }
        }
    }

    public void averagingWeightFilter(BufferedImage img) {
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

                int gx = (1 * val00 + 2 * val01 + 1 * val02 + 2 * val10 + 4 * val11 + 2 * val12 + 1 * val20 + 2 * val21 + 1 * val22) / 16;

                rgb[i][j] = gx;
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
    }
}
