/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezxla;

import static ezxla.Histogram.grayScale;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author maxiu
 */
public class EqualizedImage {

    public DefaultCategoryDataset getDataset(BufferedImage img) {
        DefaultCategoryDataset datasetTemp = new DefaultCategoryDataset();
        int width;
        int height;
        int dem = 0;
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
        for (int k = 0; k < 256; k++) {
            num.add(k);
        }
        for (int k = 0; k < 256; k++) {
            datasetTemp.setValue(arr[k], "Histogram", num.get(k));
        }
        return datasetTemp;
    }

    public JFreeChart getChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Histogram", "Value", "Value", dataset,
                PlotOrientation.VERTICAL, true, true, true);
        return chart;
    }
}
