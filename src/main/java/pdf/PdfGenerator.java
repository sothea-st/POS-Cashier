/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdf;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JPanel;
 
import java.io.IOException;

/**
 *
 * @author MOBILE-APP.02
 */
public class PdfGenerator {
      public static void panelToPdf(JPanel panel, String outputPath) throws IOException {
        int width = panel.getWidth();
        int height = panel.getHeight();
//
//        // Create a PDF document
//        PDDocument document = new PDDocument();
//        PDPage page = new PDPage(PDRectangle.A6);
//        document.addPage(page);
//
//        // Create a content stream for drawing
//        PDPageContentStream contentStream = new PDPageContentStream(document, page);
//        contentStream.setFont(PDType1Font.HELVETICA, 12);
//
//        // Scale the panel content to fit the PDF page
//        double scaleX = PDRectangle.A6.getWidth() / width;
//        double scaleY = PDRectangle.A6.getHeight() / height;
//        contentStream.concatenate2CTM(scaleX, 0, 0, scaleY, 0, 0);
//
//        // Create a BufferedImage to draw the panel onto
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2d = image.createGraphics();
//        panel.paint(g2d);
//        g2d.dispose();
//
//        // Draw the BufferedImage onto the PDF
//        contentStream.drawImage(image, 0, 0, width, height);
//        contentStream.close();
//
//        // Save the PDF document
//        document.save(outputPath);
//        document.close();
    }
}
