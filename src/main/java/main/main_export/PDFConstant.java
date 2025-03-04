 
package main.main_export;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

 
public class PDFConstant {
        public Image getLogoImage() throws IOException {
          String imagePath = "company/logoTT.png"; // Relative path in the resources folder
          InputStream imgStream = getClass().getClassLoader().getResourceAsStream(imagePath);

          // Check if the image stream is not null
          if (imgStream == null) {
               System.err.println("Image file not found: " + imagePath);
               return null;
          }

          // Read the image data into a byte array
          ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
          byte[] buffer = new byte[1024];
          int bytesRead;

          while ((bytesRead = imgStream.read(buffer)) != -1) {
               byteArrayOutputStream.write(buffer, 0, bytesRead);
          }

          // Create the image from the byte array
          Image img = new Image(ImageDataFactory.create(byteArrayOutputStream.toByteArray()));

          // Adjust the image size if necessary
          img.scaleToFit(80, 80); // Scale the image to fit within 80x80 points

          // Set the image position (top left)
          img.setFixedPosition(36, 755); // Adjust the x and y coordinates as needed

          return img;
     }
}
