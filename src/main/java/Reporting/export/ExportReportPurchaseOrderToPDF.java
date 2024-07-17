/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reporting.export;

import Constant.JavaConstant;
import Model.Report.ReportSaleDetail;
import Reporting.model.ReportingDetailResponse;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
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
import org.apache.commons.io.IOUtils;
import static pdf.PrintListPDF.downloadFolderPath;
import static pdf.PrintListPDF.folderPath;

/**
 *
 * @author MOBILE-APP.02
 */
public class ExportReportPurchaseOrderToPDF {
      public static void printListPdf(ArrayList<ReportingDetailResponse> list) throws IOException {
          try {
               String fileName = JavaConstant.getFileName();
               // Output PDF file path

               // Specify PDF file path
               String sourcePDFPath = downloadFolderPath + "/Downloads/PDF_Downloads/" + fileName + ".pdf";

               // Create a PdfWriter instance with the specified file path
               PdfWriter writer = new PdfWriter(sourcePDFPath);

               // Create a PdfDocument instance with the PdfWriter
               PdfDocument pdfDocument = new PdfDocument(writer);

               // Create a Document instance with A4 page size
               Document document = new Document(pdfDocument);

               // Sample data
               List<Object[]> dataList = new ArrayList<>();

               for (int i = 0; i < list.size(); i++) {
                    var detail = list.get(i);
                    String url = null;
//                    if (data.getProImageName().contains("media/file/crm/uploadfile/")) {
//                         url = "http://103.101.80.108:8082//" + data.getProImageName();
//                    } else {
//                         url = "http://localhost:8090/api/public/addImageForBackground/" + data.getProImageName();
//                    }

 
                    dataList.add(new Object[]{
                         String.valueOf(i + 1),
                         String.valueOf(detail.getPurchaseOrderNo()),
                         String.valueOf(detail.getTransactionNo()),
                         String.valueOf(detail.getTransactionDate()),
                         String.valueOf(detail.getOrderDate()),
                         String.valueOf(detail.getReferenceNo()),
                         String.valueOf(detail.getVendorName()),
                         String.valueOf(detail.getTotalQty()),
                         String.valueOf(detail.getTotalCost())
                    });
               }

               // Convert ArrayList to Object[][]
               Object[][] data = dataList.toArray(new Object[dataList.size()][]);

               Object[] columnHead = {
                    "#",
                    "Purchase Order No",
                    "Transaction No",
                    "Transaction Date",
                    "Order Date",
                    "Reference No",
                    "Vendor Name",
                    "Total Qty",
                    "Total Cost"};
               // Create a table model
               DefaultTableModel model = new DefaultTableModel(data, columnHead);

               // Create a JTable with the model
               JTable table = new JTable(model);

               addJTableToPDF(document, table);

               // Close the document
               document.close();

               // Create folder
               try {
                    Files.createDirectories(Paths.get(folderPath));
                    System.out.println("Folder created: " + folderPath);
               } catch (IOException e) {
                    System.err.println("Failed to create folder: " + e.getMessage());
                    return;
               }

               System.out.println("PDF created successfully!");
          } catch (FileNotFoundException e) {
               System.out.println("File not found: " + e.getMessage());
          }
     }

     private static void addJTableToPDF(Document document, JTable table) throws IOException {
          // Create a PDF Table
          Table pdfTable = new Table(table.getColumnCount());

          for (int i = 0; i < table.getColumnCount(); i++) {
               pdfTable.addCell(table.getColumnName(i));
          }

          // Add data rows
          for (int i = 0; i < table.getRowCount(); i++) {
               for (int j = 0; j < table.getColumnCount(); j++) {
                    Object value = table.getValueAt(i, j);
                    if (value instanceof BufferedImage) {
                         // If the cell contains an image, add the image to the PDF
                         BufferedImage image = (BufferedImage) value;
                         ByteArrayOutputStream baos = new ByteArrayOutputStream();
                         ImageIO.write(image, "png", baos);
                         baos.flush();
                         byte[] imageBytes = baos.toByteArray();
                         baos.close();
                         ImageData imageData = ImageDataFactory.create(imageBytes);
                         Image pdfImage = new Image(imageData);
                         pdfTable.addCell(new Cell().add(pdfImage));
                    } else {
                         // If the cell contains text, add the text to the PDF
                         pdfTable.addCell(new Cell().add(new Paragraph(value != null ? value.toString() : "")));
                    }
               }
          }

          // Add the table to the PDF document
          document.add(pdfTable);
     }

     public static BufferedImage resizeImage(String url, int targetWidth, int targetHeight) throws IOException {
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
}
