package unittests;

import org.junit.Test;
import renderer.ImageWriter;

import java.awt.*;

/**
 * tests ImageWriter class checks basic images
 *
 * @author Yedidya Korn & Eliezer Horowitz
 */
public class ImageWriterTest {

    /**
     * builds a jpeg picture to test the ImageWriter class
     */
    @Test
    public void ImageTest() {
        double width = 1600;
        double height = 1000;
        int x = 800;
        int y = 500;
        ImageWriter img = new ImageWriter("first", width, height, x, y);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if ((i % 50 == 0) && (i > 0) || (j % 50 == 0) && (j > 0))
                    img.writePixel(i, j, new Color(250, 27, 43));
                else img.writePixel(i, j, new Color(10, 5, 145));
            }
        }
        img.writeToImage();
    }

}
