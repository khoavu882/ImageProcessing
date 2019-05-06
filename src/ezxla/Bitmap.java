package ezxla;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Bitmap {

    public static void bitPlaneSlicing(int value, BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage tempimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int soKhoang = (int) (256 / Math.pow(2, value));
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color c = new Color(img.getRGB(col, row));
                int gray = (int) (c.getRed() * 0.299 + c.getGreen() * 0.587 + c.getBlue() * 0.114);
                int khoang1 = 0;
                int khoang2 = soKhoang;
                for (int i = 0; i < (256 / soKhoang) / 2; i++) {
                    if (gray >= khoang1 && gray < khoang2) {
                        gray = 0;
                    } else if (gray >= khoang2 && gray < khoang2 + soKhoang) {
                        gray = 255;
                    }
                    khoang1 = khoang1 + soKhoang * 2;
                    khoang2 = khoang2 + soKhoang * 2;
                    Color newc = new Color(gray, gray, gray);
                    tempimg.setRGB(col, row, newc.getRGB());
                }
                Color newc1 = new Color(tempimg.getRGB(col, row));
                img.setRGB(col, row, newc1.getRGB());
            }
        }
    }
}
