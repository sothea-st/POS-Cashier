package pdf;

import Constant.JavaBaseUrl;
import Constant.JavaConstant;
import Model.ProductModelV1.ProductResponseDetailV1;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class PrintToExcel {

     // create folder in specific path
     public static String downloadFolderPath = System.getProperty("user.home");
     public static String folderPath = downloadFolderPath + "\\Downloads\\EXCEL_Downloads";

     public static String getFileName(String name) {
          LocalDateTime currentDateTime = LocalDateTime.now();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH-mm-ss a");
          String formattedDateTime = currentDateTime.format(formatter);
          String fileName = name + " " + formattedDateTime;
          return fileName;
     }

     public static void toExcel(ProductResponseDetailV1[] listProduct) {
          try {
               Workbook workbook = new XSSFWorkbook();
               Sheet sheet = workbook.createSheet("Sheet1");
               ArrayList<Object[]> dataList = new ArrayList<>();
               dataList.add(new Object[]{"Barcode", "Item Code", "Sub Category", "Vendor Code", "Vendor Name",
                    "Product Name", "Product Name Kh", "Total Qty", "Sale Price", "Cost", "Image"});
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
                    byte[] imageBytes = null;

                    if (p.getProImageName().equals("default.jpg")) {
                         
                         imageBytes = getDefaultImage();

                    } else {
                         imageBytes = getImageBytes(url);
                    }

                    dataList.add(
                         new Object[]{
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
                              imageBytes
                         });
               }

               // Create a cell style with centered alignment, font color, and font size
               CellStyle style = workbook.createCellStyle();
               CellStyle style1 = workbook.createCellStyle();

               Font font = workbook.createFont();
               font.setColor(IndexedColors.BLACK.getIndex()); // Set font color to red
               font.setBold(true); // Set font bold
               font.setFontHeightInPoints((short) 12); // Set font size to 14

               style.setFont(font);

               style.setAlignment(HorizontalAlignment.CENTER); // Set text alignment to center
               style.setVerticalAlignment(VerticalAlignment.CENTER); // Set vertical alignment to center

               style1.setAlignment(HorizontalAlignment.CENTER); // Set text alignment to center
               style1.setVerticalAlignment(VerticalAlignment.CENTER); // Set vertical alignment to center

               for (int i = 0; i < dataList.size(); i++) {
                    Row row = sheet.createRow(i);
                    row.setHeightInPoints(50); // Adjust the height as needed
                    for (int j = 0; j < dataList.get(i).length; j++) {
                         Cell cell = row.createCell(j);
                         if (dataList.get(i)[j] instanceof String) {
                              if (String.valueOf(dataList.get(i)[j]).equals("Barcode")
                                   || String.valueOf(dataList.get(i)[j]).equals("Item Code")
                                   || String.valueOf(dataList.get(i)[j]).equals("Sub Category")
                                   || String.valueOf(dataList.get(i)[j]).equals("Vendor Code")
                                   || String.valueOf(dataList.get(i)[j]).equals("Vendor Name")
                                   || String.valueOf(dataList.get(i)[j]).equals("Product Name")
                                   || String.valueOf(dataList.get(i)[j]).equals("Product Name Kh")
                                   || String.valueOf(dataList.get(i)[j]).equals("Total Qty")
                                   || String.valueOf(dataList.get(i)[j]).equals("Sale Price")
                                   || String.valueOf(dataList.get(i)[j]).equals("Cost")
                                   || String.valueOf(dataList.get(i)[j]).equals("Image")) {
                                   cell.setCellStyle(style);
                                   cell.setCellValue((String) dataList.get(i)[j]);
                              } else {
                                   cell.setCellStyle(style1);
                                   cell.setCellValue((String) dataList.get(i)[j]);
                              }

                         } else if (dataList.get(i)[j] instanceof Integer) {
                              cell.setCellStyle(style1);
                              cell.setCellValue((Integer) dataList.get(i)[j]);
                         } else if (dataList.get(i)[j] instanceof byte[]) {
                              cell.setCellStyle(style1);
                              byte[] imageBytes = (byte[]) dataList.get(i)[j];
                              if (imageBytes != null) {
                                   int pictureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_JPEG);
                                   CreationHelper helper = workbook.getCreationHelper();
                                   Drawing<?> drawing = sheet.createDrawingPatriarch();
                                   ClientAnchor anchor = helper.createClientAnchor();
                                   anchor.setCol1(j);
                                   anchor.setRow1(i);
                                   anchor.setCol2(j + 1); // Set the end column
                                   anchor.setRow2(i + 1); // Set the end row

                                   Picture pict = drawing.createPicture(anchor, pictureIdx);
                                   // pict.resize(); // Automatically resize the image to fit in the cellcell

                              }
                         } else if (dataList.get(i)[j] instanceof Double) {
                              cell.setCellStyle(style1);
                              cell.setCellValue((Double) dataList.get(i)[j]);
                         }
                    }
               }

               sheet.setColumnWidth(0, 5000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(1, 5000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(2, 5000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(3, 5000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(4, 5000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(5, 20000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(6, 20000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(7, 5000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(8, 5000); // Set the width of the first column to 5000 units

               // Create folder
               try {
                    Files.createDirectories(Paths.get(folderPath));
                    System.out.println("Folder created: " + folderPath);
               } catch (IOException e) {
                    System.err.println("Failed to create folder: " + e.getMessage());
                    return;
               }

               // Specify PDF file path
               String sourcePDFPath = downloadFolderPath + "/Downloads/EXCEL_Downloads/" + getFileName("product") + ".xlsx";

               // Write the workbook content to a file
               try (FileOutputStream outputStream = new FileOutputStream(sourcePDFPath)) {
                    workbook.write(outputStream);
               }

               System.out.println("Excel file created successfully!");

          } catch (Exception e) {
               System.out.println("An error occurred: " + e.getMessage());
               e.printStackTrace();
          }
     }

     private static byte[] getImageBytes(String imageUrl) throws IOException {
          InputStream inputStream = new URL(imageUrl).openStream();
          byte[] imageBytes = IOUtils.toByteArray(inputStream);
          inputStream.close();
          return imageBytes;
     }

     public static byte[] getDefaultImage() throws IOException {
          try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("productImage/default.jpg")) {
               if (inputStream == null) {
                    throw new FileNotFoundException("File not found: productImage/default.jpg");
               }
               return IOUtils.toByteArray(inputStream);
          }
     }

}
