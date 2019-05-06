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
public class Erosion {

    public void erosionFilter(BufferedImage img, int boderColor, int numOfMatrix) {
        int width = img.getWidth();
        int height = img.getHeight();

        Color c, c1, c2, c3, newColor = null;

        for (int row = numOfMatrix / 2; row < height - (numOfMatrix / 2); row++) {
            for (int col = numOfMatrix / 2; col < width - (numOfMatrix / 2); col++) {
                c = new Color(img.getRGB(col, row - 1));
                c1 = new Color(img.getRGB(col - 1, row));
                c2 = new Color(img.getRGB(col + 1, row));
                c3 = new Color(img.getRGB(col, row + 1));
                if (c.getRed() == 0 && c1.getRed() == 0 && c2.getRed() == 0 && c3.getRed() == 0) {
                    newColor = new Color(0, 0, 0);
                } else {
                    newColor = new Color(255, 255, 255);
                }
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
    }
}
