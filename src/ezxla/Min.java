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
public class Min {
    public void minFilter(BufferedImage img, int boderColor, int numOfMatrix) {
        int width = img.getWidth();
        int height = img.getHeight();
        Color color;

        for (int row = numOfMatrix / 2; row < height - (numOfMatrix / 2); row++) {
            for (int col = numOfMatrix / 2; col < width - (numOfMatrix / 2); col++) {
                int min = 1000;
                for (int row1 = row - (numOfMatrix / 2); row1 <= row + (numOfMatrix / 2); row1++) {
                    for (int col1 = col - (numOfMatrix / 2); col1 <= col + (numOfMatrix / 2); col1++) {
                        color = new Color(img.getRGB(col1, row1));
                        if (color.getRed() < min) {
                            min = color.getRed();
                        }
                    }
                }

                Color newColor = new Color(min, min, min);
                img.setRGB(col, row, newColor.getRGB());

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
                    img.setRGB(i, row, newColor.getRGB());
                    img.setRGB(width - 1 - i, row, newColor.getRGB());
                }
            }
        }
    }
}
