/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.server.StreamResource.StreamSource;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author https://vaadin.com/docs/-/part/framework/application/application-resources.html
 */
public class MyImageSource implements StreamSource {
    ByteArrayOutputStream imagebuffer = null;
    int reloads = 0;

    // This method generates the stream contents
    public InputStream getStream () {
        // Create an image
        BufferedImage image = new BufferedImage (400, 400,
                                  BufferedImage.TYPE_INT_RGB);
        Graphics2D drawable = image.createGraphics();

        // Draw something static
        drawable.setStroke(new BasicStroke(5));
        drawable.setColor(Color.WHITE);
        drawable.fillRect(0, 0, 400, 400);
        drawable.setColor(Color.BLACK);
        drawable.drawOval(50, 50, 300, 300);

        // Draw something dynamic
        drawable.setFont(new Font("Montserrat",
                                  Font.PLAIN, 48));
        drawable.drawString("Reloads=" + reloads, 75, 216);
        reloads++;
        drawable.setColor(new Color(0, 165, 235));
        int x= (int) (200-10 + 150*Math.sin(reloads * 0.3));
        int y= (int) (200-10 + 150*Math.cos(reloads * 0.3));
        drawable.fillOval(x, y, 20, 20);

        try {
            // Write the image to a buffer
            imagebuffer = new ByteArrayOutputStream();
            ImageIO.write(image, "png", imagebuffer);

            // Return a stream from the buffer
            return new ByteArrayInputStream(
                imagebuffer.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }
}