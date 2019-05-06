/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezxla;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 *
 * @author maxiu
 */
public class Laplacian {

    BufferedImage copyImage(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    private BufferedImage createGrayscaleImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage copy = copyImage(image);

        int p = 0, a = 0, r = 0, g = 0, b = 0, avg = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                p = image.getRGB(x, y);
                a = (p >> 24) & 0xff;
                r = (p >> 16) & 0xff;
                g = (p >> 8) & 0xff;
                b = p & 0xff;
                avg = (r + g + b) / 3;
                p = (a << 24) | (avg << 16) | (avg << 8) | avg;
                copy.setRGB(x, y, p);
            }
        }
        return copy;
    }

    public void laplacianFilter(BufferedImage img) {
        BufferedImage grayScale = createGrayscaleImage(img);
//        BufferedImage copy = copyImage(grayScale);

        int w = img.getWidth();
        int h = img.getHeight();
        int sum = 0;
        int a;
        //3*3 Laplacian filter (-1,-1,-1), (-1,8,-1), (-1,-1,-1)
        for (int y = 1; y < h - 1; y++) {
            for (int x = 1; x < w - 1; x++) {
                sum = (-1 * (grayScale.getRGB(x - 1, y - 1) & 0xff)) + (-1 * (grayScale.getRGB(x, y - 1) & 0xff)) + (-1 * (grayScale.getRGB(x + 1, y - 1) & 0xff))
                        + (-1 * (grayScale.getRGB(x - 1, y) & 0xff)) + (8 * (grayScale.getRGB(x, y) & 0xff)) + (-1 * (grayScale.getRGB(x + 1, y) & 0xff))
                        + (-1 * (grayScale.getRGB(x - 1, y + 1) & 0xff)) + (-1 * (grayScale.getRGB(x, y + 1) & 0xff)) + (-1 * (grayScale.getRGB(x + 1, y + 1) & 0xff));
                a = ((grayScale.getRGB(x, y) >> 24) & 0xff);
                img.setRGB(x, y, ((a << 24) | (sum << 16) | (sum << 8) | (sum)));
            }
        }
//        return copy;
    }
}
