/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdf;

import com.itextpdf.io.image.ImageDataFactory;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.text.PageSize;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PrintPanelToPDF {

     private JPanel panel;

     public PrintPanelToPDF() {
     }

     public PrintPanelToPDF(JPanel panel) {
          this.panel = panel;
     }

     public void printPdf(String pdfName, int numberOfItem) throws FileNotFoundException, IOException {
          panel.setLayout(new BorderLayout());
          // Render JPanel to BufferedImage
          BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
          Graphics2D g = bi.createGraphics();
          panel.print(g);
          g.dispose();

          // Calculate the position to center the JPanel content on A4 paper
          float panelWidth = bi.getWidth();
          float panelHeight = bi.getHeight();
          float a4Width = PageSize.A4.getWidth();
          float a4Height = PageSize.A4.getHeight();
          float x = (a4Width - panelWidth) / 2;

          float y = -10;
 

          // Create a PDF document
          String outputFilePath = "C:\\Users\\mobile-app.02\\Pictures\\" + pdfName + ".pdf";
          FileOutputStream fos = new FileOutputStream(outputFilePath);
          PdfWriter writer = new PdfWriter(fos);
          PdfDocument pdfDoc = new PdfDocument(writer);
          Document document = new Document(pdfDoc);

          // Convert BufferedImage to byte array
          ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
          ImageIO.write(bi, "png", byteArrayOutputStream);
          byte[] imageBytes = byteArrayOutputStream.toByteArray();

          // Create an Image instance from the byte array
          Image image = new Image(ImageDataFactory.create(imageBytes));

          // Add the image to the document
          float pageHeight = PageSize.A4.getHeight();
          float currentPageHeight = 0;
          float contentHeight = image.getImageScaledHeight();

          while (contentHeight > 0) {
               UnitValue width = UnitValue.createPointValue(panelWidth);
               UnitValue height = UnitValue.createPointValue(contentHeight);

               // Check if there's enough space on the current page
               float remainingSpace = pageHeight - currentPageHeight;
//               if (contentHeight <= remainingSpace) {
//                    // Add the entire content to the current page
//                    image.setWidth(width);
//                    image.setHeight(height);
//                    document.add(image);
//                    currentPageHeight += contentHeight;
//                    contentHeight = 0; // No more content to add
//               } else {
               // Add the part of the content that fits on the current page
               float partHeight = remainingSpace;
               image.setWidth(width);
               image.setHeight(UnitValue.createPointValue(partHeight));
               image.setFixedPosition(x, y);
               document.add(image);

               // Add a new page
//               pdfDoc.addNewPage();
//               currentPageHeight = 0;

               // Adjust the remaining content height
               contentHeight -= remainingSpace;

               // If the remaining content height is above a specific threshold,
               // add it to the new page as well
               if (contentHeight > 400) {
                    // Add the remaining content to the new page
                    image.setHeight(UnitValue.createPointValue(contentHeight));
                    document.add(image);

                    // Reset content height and mark as processed
                    contentHeight = 0;
               }
//               }
          }

          // Add the image to the document
//          while (currentPageHeight < panelHeight) {
//               // Set the size and position of the image to center it on the page
//               UnitValue width = UnitValue.createPointValue(panelWidth);
//               UnitValue height = UnitValue.createPointValue(panelHeight);
//               image.setWidth(width);
//               image.setHeight(height);
//               image.setFixedPosition(x, y);
//
//               // Add the image to the document
//               document.add(image);
//
//               currentPageHeight += pageHeight;
//
//               // If there's content remaining, add a new page
//               if (currentPageHeight < panelHeight) {
//                    pdfDoc.addNewPage();
//               }
//          }
          // Close the document
          document.close();
          System.out.println("success");
     }

     private static byte[] toByteArray(BufferedImage image) throws IOException {
          javax.imageio.ImageIO.setUseCache(false);
          javax.imageio.ImageIO.write(image, "png", new File("temp.png"));
          return java.nio.file.Files.readAllBytes(new File("temp.png").toPath());
     }

     public static void main(String[] args) throws IOException {
          // Create a JPanel and add components to it
          JPanel panel = new JPanel();
          JLabel label = new JLabel("Hello, World!");
          panel.add(label);

          // Render JPanel to BufferedImage
          BufferedImage bi = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
          Graphics2D g = bi.createGraphics();
          panel.print(g);
          g.dispose();

          // Calculate the position to center the JPanel content on A4 paper
          float panelWidth = bi.getWidth();
          float panelHeight = bi.getHeight();
          float a4Width = PageSize.A4.getWidth();
          float a4Height = PageSize.A4.getHeight();
          float x = (a4Width - panelWidth) / 2;
          float y = (a4Height - panelHeight) / 2;

          // Create a PDF document
          String outputFilePath = "panel.pdf";
          FileOutputStream fos = new FileOutputStream(outputFilePath);
          PdfWriter writer = new PdfWriter(fos);
          PdfDocument pdfDoc = new PdfDocument(writer);
          Document document = new Document(pdfDoc);

          // Convert BufferedImage to byte array
          ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
          ImageIO.write(bi, "png", byteArrayOutputStream);
          byte[] imageBytes = byteArrayOutputStream.toByteArray();

          // Create an Image instance from the byte array
          Image image = new Image(ImageDataFactory.create(imageBytes));

          // Set the size and position of the image to center it on the page
          UnitValue width = UnitValue.createPointValue(panelWidth);
          UnitValue height = UnitValue.createPointValue(panelHeight);
          image.setWidth(width);
          image.setHeight(height);
          image.setFixedPosition(x, y);

          // Add the image to the document
          document.add(image);

          // Close the document
          document.close();
          System.out.println("success");
     }

}
