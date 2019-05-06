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
public class Prewit {

    public void PrewittFilter(BufferedImage img, int boderColor, int numOfMatrix) {
        int a[] = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
        int b[] = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

        int dem = 0;
        BufferedImage img1 = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        BufferedImage img2 = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        BufferedImage img3 = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        int width = img.getWidth();
        int height = img.getHeight();
        Color color;
        for (int row = numOfMatrix / 2; row < height - (numOfMatrix / 2); row++) {
            for (int col = numOfMatrix / 2; col < width - (numOfMatrix / 2); col++) {
                int sum = 0;
                dem = 0;
                for (int row1 = row - (numOfMatrix / 2); row1 <= row + (numOfMatrix / 2); row1++) {
                    for (int col1 = col - (numOfMatrix / 2); col1 <= col + (numOfMatrix / 2); col1++) {
                        color = new Color(img.getRGB(col1, row1));
                        sum = sum + color.getRed() * a[dem];
                        dem++;

                    }
                }
                if (sum > 255) {
                    sum = 255;
                } else if (sum < 0) {
                    sum = 0;
                }

                Color newColor = new Color(sum, sum, sum);

                img.setRGB(col, row, newColor.getRGB());

            }
        }
        for (int row = numOfMatrix / 2; row < height - (numOfMatrix / 2); row++) {
            for (int col = numOfMatrix / 2; col < width - (numOfMatrix / 2); col++) {
                int sum = 0;
                dem = 0;
                for (int row1 = row - (numOfMatrix / 2); row1 <= row + (numOfMatrix / 2); row1++) {
                    for (int col1 = col - (numOfMatrix / 2); col1 <= col + (numOfMatrix / 2); col1++) {
                        color = new Color(img.getRGB(col1, row1));
                        sum = sum + color.getRed() * b[dem];
                        dem++;

                    }
                }
                if (sum > 255) {
                    sum = 255;
                } else if (sum < 0) {
                    sum = 0;
                }

                Color newColor = new Color(sum, sum, sum);

                img2.setRGB(col, row, newColor.getRGB());

            }
        }
        for (int row = numOfMatrix / 2; row < height - (numOfMatrix / 2); row++) {
            for (int col = numOfMatrix / 2; col < width - (numOfMatrix / 2); col++) {
                color = new Color(img.getRGB(col, row));
                int gray = color.getRed();
                color = new Color(img2.getRGB(col, row));
                int gray1 = color.getRed();
                int gray3 = (int) Math.sqrt(gray * gray + gray1 * gray1);
                if (gray3 > 255) {
                    gray3 = 255;
                }
                if (gray3 < 0) {
                    gray3 = 0;
                }
                Color newColor = new Color(gray3, gray3, gray3);
                img3.setRGB(col, row, newColor.getRGB());
            }
        }
        Color newColor = new Color(boderColor, boderColor, boderColor);
        for (int i = 0; i < numOfMatrix / 2; i++) {

            for (int row = 0; row < height; row++) {
                if (row == 0 + i || row == width - 1 - i) {
                    for (int col = 0; col < width; col++) {

                        img.setRGB(col, row, newColor.getRGB());
                    }
                } else {
                    img3.setRGB(i, row, newColor.getRGB());
                    img3.setRGB(width - 1 - i, row, newColor.getRGB());
                }

            }
        }

//        c = new Color(img.getRGB(0, 0));
//        c = new Color(img.getRGB(0,0));
//        System.out.println(c.getRed());
//        c = new Color(img.getRGB(1,1));
//        System.out.println(c.getRed());
//        c = new Color(img.getRGB(2,2));
//        System.out.println(c.getRed());
//        c = new Color(img.getRGB(img.getWidth() - 4, img.getHeight() - 4));
//        System.out.println(c.getRed());
//        System.out.println(img.getWidth() + ";" + img.getHeight());
//        System.out.println(img1.getWidth() + ";" + img1.getHeight());
    }
}
