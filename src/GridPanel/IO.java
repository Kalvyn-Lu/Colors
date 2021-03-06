/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GridPanel;

/**
 *
 * @author ninjakl
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import struct.Delta;
import javax.swing.JFileChooser;

public class IO {

    String directory;

    public IO(String in_directory) {
        directory = in_directory;
    }

    public void drawImg(ArrayList<Layer> layers) throws IOException {
        int width = layers.get(0).colorArr[0].length;
        int height = layers.get(0).colorArr.length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.createGraphics();
        for (Layer c : layers) {
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    g.setColor(c.colorArr[i][j]);
                    g.fillRect(i, j, 1, 1);
                }
            }
        }
        File file = new File(directory);
        ImageIO.write(image, "PNG", file);

    }

}
