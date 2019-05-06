package ezxla;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Histogram {
    public BufferedImage evaluateHistogram(BufferedImage img, BufferedImage img1, BufferedImage img2) {
        double[] arr = new double[256];
        double[] arr1 = new double[256];
        double[] arr2 = new double[256];
        grayScale(img);
        grayScale(img1);
        grayScale(img2);
        int num, num1, num2;
        num = 0;
        num1 = 0;
        num2 = 0;
        int width = img.getWidth();
        int height = img.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(img.getRGB(j, i));
                int grayLevel = c.getRed();
                for (int k = 0; k < 256; k++) {
                    if (grayLevel == k) {
                        arr[k]++;
                    }
                }
            }
        }
        width = img1.getWidth();
        height = img1.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(img1.getRGB(j, i));
                int grayLevel = c.getRed();
                for (int k = 0; k < 256; k++) {
                    if (grayLevel == k) {
                        arr1[k]++;
                    }
                }
            }
        }
        width = img2.getWidth();
        height = img2.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(img2.getRGB(j, i));
                int grayLevel = c.getRed();
                for (int k = 0; k < 256; k++) {
                    if (grayLevel == k) {
                        arr2[k]++;
                    }
                }
            }
        }
        for (double x : arr) {
            if (x != 0) num++;
        }
        for (double x : arr1) {
            if (x != 0) num1++;
        }
        for (double x : arr2) {
            if (x != 0) num2++;
        }
        System.out.println("1-" + num);
        System.out.println("2-" + num1);
        System.out.println("3-" + num2);
        if (num > num1 && num > num2)
            return img;
        if (num1 > num && num1 > num2)
            return img1;
        if (num2 > num && num2 > num1)
            return img2;
        return null;
    }

    public void drawHistogram(String add) {
        String a = add;
        int width;
        int height;
        BufferedImage img = null;
        int dem = 0;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            img = ImageIO.read(new File(a));
        } catch (IOException ex) {
            System.out.println("Loading unsucessed!");
        }
        grayScale(img);
        width = img.getWidth();
        height = img.getHeight();
        BufferedImage tempimg = new BufferedImage(width, height, img.getType());
        int totalPixel = width * height;
        double[] arr = new double[256];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(img.getRGB(j, i));
                int grayLevel = c.getRed();
                for (int k = 0; k < 256; k++) {
                    if (grayLevel == k) {
                        arr[k]++;
                    }
                }
            }
        }
        ArrayList<Integer> num = new ArrayList<>();
        for (int k = 0; k < 256; k++)
            num.add(k);
        for (int k = 0; k < 256; k++) {
            dataset.setValue(arr[k], "Histogram", num.get(k));
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Histogram", "Value", "Value", dataset,
                PlotOrientation.VERTICAL, false, true, false);
        //Render the frame
        ChartFrame chartFrame = new ChartFrame("Vertical Bar Chart Original", chart);
        chartFrame.setVisible(true);
        chartFrame.setSize(560, 350);
    }

    public static void grayScale(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Color c = new Color(img.getRGB(col, row));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                Color newColor = new Color(red + green + blue, red + green + blue, red + green + blue);
                img.setRGB(col, row, newColor.getRGB());
            }
        }
    }

    public void histogramEqualization(BufferedImage img) {
        grayScale(img);
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage tempimg = new BufferedImage(width, height, img.getType());
        int totalPixel = width * height;
        int[] arr = new int[256];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(img.getRGB(j, i));
                int grayLevel = c.getRed();
                for (int k = 0; k < 256; k++) {
                    if (grayLevel == k) {
                        arr[k]++;
                    }
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(img.getRGB(j, i));
                int r = c.getRed();
                double grayLevel = 0;
                for (int k = 0; k <= r; k++) {
                    grayLevel = grayLevel + arr[k];
                }
                grayLevel = (grayLevel / totalPixel) * 255;
                Color newColor = new Color((int) grayLevel, (int) grayLevel, (int) grayLevel);
                tempimg.setRGB(j, i, newColor.getRGB());
            }
        }
        for (int c = 0; c < height; c++) {
            for (int d = 0; d < width; d++) {
                img.setRGB(d, c, tempimg.getRGB(d, c));
            }
        }
    }

    public void drawHistogram1(BufferedImage img) {

        int width;
        int height;
        int dem = 0;
        histogramEqualization(img);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        width = img.getWidth();
        height = img.getHeight();
        BufferedImage tempimg = new BufferedImage(width, height, img.getType());
        int totalPixel = width * height;
        double[] arr = new double[256];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(img.getRGB(j, i));
                int grayLevel = c.getRed();
                for (int k = 0; k < 256; k++) {
                    if (grayLevel == k) {
                        arr[k]++;
                    }
                }
            }
        }
    }
}