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
public class Convolution {
    public void convolutionFilter(BufferedImage img, int boderColor, int numOfMatrix) {
        int[] a = {
            0, 1, 1, 2, 2, 2, 1, 1, 0,
            1, 2, 4, 5, 5, 5, 4, 2, 1,
            1, 4, 5, 3, 0, 3, 5, 4, 1,
            2, 5, 3, -12, -24, -12, 3, 5, 2,
            2, 5, 0, -24, -40, -24, 0, 5, 2,
            2, 5, 3, -12, -24, -12, 3, 5, 2,
            1, 4, 5, 3, 0, 3, 5, 4, 1,
            1, 2, 4, 5, 5, 5, 4, 2, 1,
            0, 1, 1, 2, 2, 2, 1, 1, 0,};

        int dem = 0;
        
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
                        sum += color.getRed() * a[dem];

                        //System.out.println(a[dem]);
                        dem++;

                    }
                }

                if (sum > 255) {
                    sum = 255;
                }
                if (sum < 0) {
                    sum = 0;
                }
                Color newColor = new Color(sum, sum, sum);
                img.setRGB(col, row, newColor.getRGB());

            }
        }
        for (int col = numOfMatrix / 2; col < width - numOfMatrix / 2; col++) {
            int value = img.getRGB(col, numOfMatrix / 2);
            for (int i = 0; i < numOfMatrix / 2; i++) {
                img.setRGB(col, i, value);
            }
        }
        for (int col = numOfMatrix / 2; col < width - numOfMatrix / 2; col++) {
            int value = img.getRGB(col, height - 1 - numOfMatrix / 2);
            for (int i = 0; i < numOfMatrix / 2; i++) {
                img.setRGB(col, height - 1 - i, value);
            }
        }
        for (int row = 0; row < height; row++) {
            int value = img.getRGB(numOfMatrix / 2, row);
            for (int i = 0; i < numOfMatrix / 2; i++) {
                img.setRGB(i, row, value);
            }
        }

        for (int row = 0; row < height; row++) {
            int value = img.getRGB(width - 1 - numOfMatrix / 2, row);
            for (int i = 0; i < numOfMatrix / 2; i++) {
                img.setRGB(width - 1 - i, row, value);
            }
        }
//        color = new Color(img.getRGB(0, 0));
//        color = new Color(img.getRGB(0, 0));
//        System.out.println(color.getRed());
//        color = new Color(img.getRGB(1, 1));
//        System.out.println(color.getRed());
//        color = new Color(img.getRGB(2, 2));
//        System.out.println(color.getRed());
//        color = new Color(img.getRGB(img.getWidth() - 4, img.getHeight() - 4));
//        System.out.println(color.getRed());
//        System.out.println(img.getWidth() + ";" + img.getHeight());
//        System.out.println(img.getWidth() + ";" + img.getHeight());
    }
}
