 
package pdf;

import Constant.JavaBaseUrl;
import Constant.JavaConstant;
import Model.ProductModelV1.ProductResponseDetailV1;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JTable;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.IOUtils;

public class PrintListPDF {

     public static String downloadFolderPath = System.getProperty("user.home");
     public static String folderPath = downloadFolderPath + "\\Downloads\\PDF_Downloads";

     public static void printListPdf(ProductResponseDetailV1[] listProduct) throws IOException {
          try {

               String fileName = PrintToExcel.getFileName("Product");;

               // Specify PDF file path
               String sourcePDFPath = downloadFolderPath + "/Downloads/PDF_Downloads/" + fileName + ".pdf";

               // Create a PdfWriter instance with the specified file path
               PdfWriter writer = new PdfWriter(sourcePDFPath);

               // Create a PdfDocument instance with the PdfWriter
               PdfDocument pdfDocument = new PdfDocument(writer);

               // Create a Document instance with A4 page size
               // Document document = new Document(pdfDocument);
//               Document document = new Document(pdfDocument, PageSize.A4.rotate());// for rotate
               Document document = new Document(pdfDocument, PageSize.A4);

               // Sample data
               List<Object[]> dataList = new ArrayList<>();

               for (ProductResponseDetailV1 p : listProduct) {
                    String url = null;
                    if (p.getProImageName().contains("media/file/crm/uploadfile/")) {
                         url = JavaBaseUrl.baseUrlImage + p.getProImageName();
                    } else {
                         url = JavaBaseUrl.baseUrlBgImage + p.getProImageName();
                    }

                    boolean imageExists = JavaConstant.checkImageExists(url);
                    if (!imageExists) {
                         url = JavaBaseUrl.baseUrlDefaultImage;
                    }

                    BufferedImage image = null;

                    if (p.getProImageName().equals("default.jpg")) {
                         image = defaultImage(30, 30);
                    } else {
                         image = resizeImage(url, 30, 30);
                    }

                    dataList.add(new Object[]{
                         String.valueOf(p.getBarcode()),
                         String.valueOf(p.getItemCode()),
                         String.valueOf(p.getSubCatNameEn()),
                         String.valueOf(p.getVendorCode()),
                         String.valueOf(p.getVendorName()),
                         String.valueOf(p.getProNameEn()),
                         String.valueOf(p.getProNameEn()),
                         String.valueOf(p.getQty()),
                         "$".concat(String.valueOf(p.getPrice())),
                         "$".concat(String.valueOf(p.getCost())),
                         image
                    });
               }

               // Convert ArrayList to Object[][]
               Object[][] data = dataList.toArray(new Object[dataList.size()][]);

               Object[] columnHead = {"Barcode", "Item Code", "Sub Category", "Vendor Code", "Vendor Name",
                    "Product Name", "Product Name Kh", "Total Qty", "Sale Price", "Cost", "Image"};
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
          // Set table properties
          pdfTable.setWidth(100);
          pdfTable.setHorizontalAlignment(HorizontalAlignment.CENTER);

          // Add headers with bold font weight
          for (int i = 0; i < table.getColumnCount(); i++) {
               pdfTable.addCell(new Cell().add(new Paragraph(table.getColumnName(i)).setFontSize(10)
                    .setTextAlignment(TextAlignment.CENTER).setBold()));
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
                         pdfTable.addCell(new Cell().add(pdfImage).setTextAlignment(TextAlignment.CENTER));
                    } else {
                         // If the cell contains text, add the text to the PDF
                         pdfTable.addCell(new Cell().add(new Paragraph(value != null ? value.toString() : "")
                              .setFontSize(10).setTextAlignment(TextAlignment.CENTER)));
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

     public static BufferedImage defaultImage(int targetWidth, int targetHeight) throws IOException {
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
