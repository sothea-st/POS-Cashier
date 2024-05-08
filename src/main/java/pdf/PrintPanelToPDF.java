/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdf;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.Document;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.itextpdf.layout.property.UnitValue;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author MOBILE-APP.02
 */
public class PrintPanelToPDF {

     private JPanel panel;

     public PrintPanelToPDF() {
     }

     public PrintPanelToPDF(JPanel panel) {
          this.panel = panel;
     }

     public void printPdf() {
          // Render the JPanel to an image
          BufferedImage image = new BufferedImage(panel.getPreferredSize().width, panel.getPreferredSize().height, BufferedImage.TYPE_INT_RGB);
          Graphics2D g2d = image.createGraphics();
          panel.paint(g2d);
          g2d.dispose();

          // Create a PDF document
          try (PDDocument document = new PDDocument()) {
               PDPage page = new PDPage(PDRectangle.A4); // Create an A4 page
               document.addPage(page);

               // Scale the image to fit the A4 page
               float imageWidth = image.getWidth();
               float imageHeight = image.getHeight() * 2;
               float pageWidth = page.getMediaBox().getWidth();
               float pageHeight = page.getMediaBox().getHeight();

               float scale = Math.min(pageWidth / imageWidth, pageHeight / imageHeight);
               float x = (pageWidth - imageWidth * scale) / 2;
               float y = (pageHeight - imageHeight * scale) / 2;
               // Create a PDImageXObject from the rendered image
               PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, toByteArray(image), "image");
               // Add the scaled image to the PDF document
               try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.drawImage(pdImage, 0, 0, image.getWidth(), image.getHeight());
               }

               // Save the PDF document to a file
               document.save("panels.pdf");
               System.out.println("PDF created successfully!");
          } catch (IOException e) {
               e.printStackTrace();
          }
     }

     private static byte[] toByteArray(BufferedImage image) throws IOException {
          javax.imageio.ImageIO.setUseCache(false);
          javax.imageio.ImageIO.write(image, "png", new File("temp.png"));
          return java.nio.file.Files.readAllBytes(new File("temp.png").toPath());
     }

//     public static void main(String[] args) throws IOException {
//          // Create a JPanel and add components to it
//          JPanel panel = new JPanel();
//          JLabel label = new JLabel("Hello, World!");
//          panel.add(label);
//
//          // Render JPanel to BufferedImage
//          BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
//          Graphics2D g = bi.createGraphics();
//          panel.print(g);
//          g.dispose();
//
//          // Calculate the position to center the JPanel content on A4 paper
//          float panelWidth = bi.getWidth();
//          float panelHeight = bi.getHeight();
//          float a4Width = PageSize.A4.getWidth();
//          float a4Height = PageSize.A4.getHeight();
//          float x = (a4Width - panelWidth) / 2;
//          float y = (a4Height - panelHeight) / 2;
//
//          // Create a PDF document
//          String outputFilePath = "panel.pdf";
//          FileOutputStream fos = new FileOutputStream(outputFilePath);
//          PdfWriter writer = new PdfWriter(fos);
//          PdfDocument pdfDoc = new PdfDocument(writer);
//          Document document = new Document(pdfDoc);
//
//          // Convert BufferedImage to byte array
//          ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//          ImageIO.write(bi, "png", byteArrayOutputStream);
//          byte[] imageBytes = byteArrayOutputStream.toByteArray();
//
//          // Create an Image instance from the byte array
//          Image image = new Image(ImageDataFactory.create(imageBytes));
//
//          // Set the size and position of the image to center it on the page
//          UnitValue width = UnitValue.createPointValue(panelWidth);
//          UnitValue height = UnitValue.createPointValue(panelHeight);
//          image.setWidth(width);
//          image.setHeight(height);
//          image.setFixedPosition(x, y);
//
//          // Add the image to the document
//          document.add(image);
//
//          // Close the document
//          document.close();
//     }

}
