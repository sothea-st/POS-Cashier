package main.main_export;

import Components.Fonts.WindowFonts;
import Constant.JavaConstant;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static main.main_export.MainExport.downloadFolderPath;
import static main.main_export.MainExport.folderPath;
import org.apache.commons.io.IOUtils;

public abstract class MainExportPDF extends MainExport {

     protected List<Object[]> dataList = new ArrayList<>();
     protected String[] columnHeader;
     private PdfFont timesNewRoman;

     private PdfWriter writer;
     private PdfDocument pdfDocument;
     private Document document;
     private String titleEn;
     private String titleKh;
     private String sourcePDFPath;

     public MainExportPDF(String[] columnHeader, String titleEn, String titleKh) {
          this.titleEn = titleEn;
          this.titleKh = titleKh;
          this.columnHeader = columnHeader;

          try {
               sourcePDFPath = downloadFolderPath + "\\Downloads\\PDF_Downloads\\" + getFileName(titleEn) + ".pdf";
               timesNewRoman = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
               writer = new PdfWriter(sourcePDFPath);
               pdfDocument = new PdfDocument(writer);
               //  Document document = new Document(pdfDocument, PageSize.A4.rotate());// for rotate
               document = new Document(pdfDocument, PageSize.A4);
          } catch (Exception e) {
               System.err.println("error : " + e);
          }
     }

     protected abstract void setData();

     public void export() {
          try {

               document.add(new PDFConstant().getLogoImage());
               int marginBottom = 5;

               // ======= export khmer font to pdf =====
               //PdfFont khmerFont = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);
               // Create a bold, centered paragraph
//                    Paragraph titleKhmer = new Paragraph(
//                         new Text(JavaConstant.containsKhmer(khmerText) ? khmerText : "")
//                              .setFont(khmerFont)
//                              .setFontSize(WindowFonts.fontSizeTitleExport)
//                    ).setTextAlignment(TextAlignment.CENTER).setMargin(0);
               //document.add(titleKhmer);
               // ======= end export khmer font to pdf =====
               // old code working
               Paragraph titleParagraph = new Paragraph(titleEn)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(WindowFonts.fontSizeTitleExport)
                    //.setBold()
                    .setFont(timesNewRoman)
                    .setMargin(0);
               document.add(titleParagraph); // Add title paragraph

               String date = "Date: " + JavaConstant.getCurrentDate();
               Paragraph pDate = new Paragraph(date)
                    .setTextAlignment(TextAlignment.CENTER)
                    //.setBold()
                    .setFont(timesNewRoman)
                    .setFontSize(WindowFonts.fontSizeTitleExport)
                    .setMargin(marginBottom);
               document.add(pDate); // Add date paragraph

               // Sample data
               setData();

               // style
               style(document);

          } catch (IOException ex) {
               System.err.println("error : " + ex);
          }

     }

     private void style(Document document) {
          try {
               // Convert ArrayList to Object[][]
               Object[][] data = dataList.toArray(new Object[dataList.size()][]);

               // Create a table model
               DefaultTableModel model = new DefaultTableModel(data, columnHeader);

               // Create a JTable with the model
               JTable table = new JTable(model);

               addJTableToPDF(document, table);
               // Close the document
               document.close();
               Files.createDirectories(Paths.get(folderPath));
               System.out.println("Folder created: " + folderPath);

               // show dialog path file was save
               msgPrint(sourcePDFPath);

          } catch (IOException e) {
               System.err.println("Failed to create folder: " + e.getMessage());
          }
     }

     private void addJTableToPDF(Document document, JTable table) throws IOException {
          // Create a PDF Table with full width
          Table pdfTable = new Table(table.getColumnCount()).useAllAvailableWidth();

          // Set table alignment and width
          pdfTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
          pdfTable.setWidth(UnitValue.createPercentValue(100)); // Full-width table

          // Add headers with bold font
          for (int i = 0; i < table.getColumnCount(); i++) {
               pdfTable.addCell(
                    new Cell().add(
                         new Paragraph(table.getColumnName(i))
                              .setFont(timesNewRoman)
                              .setFontSize(9)
                              .setTextAlignment(TextAlignment.CENTER)
                              .setFontColor(new DeviceRgb(255, 255, 255))
                    ).setBackgroundColor(new DeviceRgb(0, 103, 184))
               );
          }

          // Add data rows
          for (int i = 0; i < table.getRowCount(); i++) {
               for (int j = 0; j < table.getColumnCount(); j++) {
                    Object value = table.getValueAt(i, j);
                    if (value instanceof BufferedImage) {
                         // Handle image data in the table
                         BufferedImage image = (BufferedImage) value;
                         ByteArrayOutputStream baos = new ByteArrayOutputStream();
                         ImageIO.write(image, "png", baos);
                         baos.flush();
                         byte[] imageBytes = baos.toByteArray();
                         baos.close();
                         ImageData imageData = ImageDataFactory.create(imageBytes);
                         Image pdfImage = new Image(imageData);
                         pdfTable.addCell(new Cell().add(pdfImage).setTextAlignment(TextAlignment.CENTER));
                    } else {
                         // Handle text data in the table
                         pdfTable.addCell(
                              new Cell().add(
                                   new Paragraph(value != null ? value.toString() : "")
                                        .setFont(timesNewRoman)
                                        .setFontSize(10)
                                        .setTextAlignment(TextAlignment.CENTER)
                              )
                         );
                    }
               }
          }

          // Add the table to the PDF document
          document.add(pdfTable);
     }

     private static BufferedImage resizeImage(String url, int targetWidth, int targetHeight) throws IOException {
          InputStream inputStream = new URL(url).openStream();
          byte[] imageBytes = IOUtils.toByteArray(inputStream);
          // Convert byte array to BufferedImage
          ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
          BufferedImage originalImage = ImageIO.read(bis);
          bis.close();

          // Resize the image using Bicubic interpolation
          BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
          Graphics2D g2d = resizedImage.createGraphics();
          g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
          g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
          g2d.dispose();

          return resizedImage;
     }

     private static BufferedImage defaultImage(int targetWidth, int targetHeight) throws IOException {
          // Load the image from resources
          InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("productImage/default.jpg");
          if (inputStream == null) {
               throw new FileNotFoundException("File not found: ");
          }

          try (ByteArrayInputStream bis = new ByteArrayInputStream(IOUtils.toByteArray(inputStream))) {
               // Convert byte array to BufferedImage
               BufferedImage originalImage = ImageIO.read(bis);

               // Resize the image using Bicubic interpolation
               BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
               Graphics2D g2d = resizedImage.createGraphics();
               g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
               g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
               g2d.dispose();

               return resizedImage;
          }

     }
}
