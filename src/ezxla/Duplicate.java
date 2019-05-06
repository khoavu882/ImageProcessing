/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezxla;

import java.awt.Color;
import java.awt.image.BufferedImage;
import static javafx.scene.paint.Color.color;
import javax.imageio.ImageIO;

/**
 *
 * @author maxiu
 */
public class Duplicate {

    public void replicateBorder(BufferedImage img) {

        int width = img.getWidth();
        int height = img.getHeight();
        int newPixel;
        int alpha, red, green, blue;

        int point = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                alpha = new Color(img.getRGB(x, y)).getAlpha();
                red = new Color(img.getRGB(x, y)).getRed();
                green = new Color(img.getRGB(x, y)).getGreen();
                blue = new Color(img.getRGB(x, y)).getBlue();
                newPixel = colorToRGB(alpha, red, green, blue);
                img.setRGB(x, y, newPixel);

                if (x == 0) {
                    point = new Color(img.getRGB(x + 1, y)).getRed();
                    newPixel = colorToRGB(255, point, point, point);
                    img.setRGB(x, y, newPixel);
                }
                if (y == 0) {
                    point = new Color(img.getRGB(x, y + 1)).getRed();
                    newPixel = colorToRGB(255, point, point, point);
                    img.setRGB(x, y, newPixel);
                }
                if (x == (width - 1)) {
                    point = new Color(img.getRGB(x - 1, y)).getRed();
                    newPixel = colorToRGB(255, point, point, point);
                    img.setRGB(x, y, newPixel);
                }
                if (y == (height - 1)) {
                    point = new Color(img.getRGB(x, y - 1)).getRed();
                    newPixel = colorToRGB(255, point, point, point);
                    img.setRGB(x, y, newPixel);
                }

                if ((x == 0 && y == 0)) {
                    point = new Color(img.getRGB(x + 1, y + 1)).getRed();
                    newPixel = colorToRGB(255, point, point, point);
                    img.setRGB(x, y, newPixel);
                }
                if (x == width - 1 && y == height - 1) {
                    point = new Color(img.getRGB(x - 1, y - 1)).getRed();
                    newPixel = colorToRGB(255, point, point, point);
                    img.setRGB(x, y, newPixel);
                }
                if (x == 0 && y == height - 1) {
                    point = new Color(img.getRGB(x + 1, y - 1)).getRed();
                    newPixel = colorToRGB(255, point, point, point);
                    img.setRGB(x, y, newPixel);
                }
                if (y == 0 && x == width - 1) {
                    point = new Color(img.getRGB(x - 1, y + 1)).getRed();
                    newPixel = colorToRGB(255, point, point, point);
                    img.setRGB(x, y, newPixel);
                }
            }
        }
    }

    public void paintBorder(BufferedImage img) {
        BufferedImage imageBordered = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        int width = img.getWidth();
        int height = img.getHeight();
        int newPixel = 0;
        int alpha, red, green, blue;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                alpha = new Color(img.getRGB(x, y)).getAlpha();
                red = new Color(img.getRGB(x, y)).getRed();
                green = new Color(img.getRGB(x, y)).getGreen();
                blue = new Color(img.getRGB(x, y)).getBlue();
                newPixel = colorToRGB(alpha, red, green, blue);
                imageBordered.setRGB(x, y, newPixel);

                if (x <= 1 || y <= 1 || x >= (width - 2) || y >= (height - 2)) {
                    newPixel = colorToRGB(255, 255, 200, 0);
                    imageBordered.setRGB(x, y, newPixel);
                }
//                if (i == 0 || j == 0 || i == (width-1) || j == (heihgt-1)){
//                    newPixel = colorToRGB(255,255,0,0);
//                    imageBordered.setRGB(i,j,newPixel);
//                }
            }
        }
    }
    
    public static int colorToRGB(int alpha, int red, int green, int blue) {
		int newPixel = 0;
		newPixel += alpha;
		newPixel = newPixel << 8;
		newPixel += red;
		newPixel = newPixel << 8;
		newPixel += green;
		newPixel = newPixel << 8;
		newPixel += blue;

		return newPixel; // 32 bit
	}
}
