package pdf;

import Model.PackageProduct.ProductModel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

 
public class PrintToExcel {

     public static void main(String[] args) {
          toExcel(null);
     }
     // create folder in specific path
     public static String downloadFolderPath = System.getProperty("user.home");
     public static String folderPath = downloadFolderPath + "\\Downloads\\EXCEL_Downloads";

     public static void toExcel(ArrayList<ProductModel> listProduct) {

          LocalDate currentDate = LocalDate.now();
          // Define a custom date format
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
          // Format the current date using the defined format
          String formattedDate = currentDate.format(formatter);
          String fileName = formattedDate;

          try {
               // Create a new Excel workbook
               Workbook workbook = new XSSFWorkbook();

               // Create a new Excel sheet
               Sheet sheet = workbook.createSheet("Sheet1");

               // Create an ArrayList to hold the rows
               ArrayList<Object[]> dataList = new ArrayList<>();
               dataList.add(new Object[]{"Product Name", "Barcode", "Price", "Quantity", "Status", "Picture"});

          

               // Add data rows to the ArrayList
               for (int i = 0; i < listProduct.size(); i++) {
                    var data = listProduct.get(i);
                    String url = null;
                    if( data.getProImageName().contains("media/file/crm/uploadfile/") ) {
                         url = "http://103.101.80.108:8082//" + data.getProImageName();
                    } else {
                         url = "http://localhost:8090/api/public/addImageForBackground/"+data.getProImageName();
                    }
                    
                    dataList.add(new Object[]{
                         data.getProductNameEn(),
                         data.getBarcode(),
                         data.getPrice(),
                         data.getQty(),
                         data.getProductStatus(),
                         getImageBytes(url)
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
                              if (String.valueOf(dataList.get(i)[j]).equals("Product Name")
                                   || String.valueOf(dataList.get(i)[j]).equals("Barcode")
                                   || String.valueOf(dataList.get(i)[j]).equals("Price")
                                   || String.valueOf(dataList.get(i)[j]).equals("Quantity")
                                   || String.valueOf(dataList.get(i)[j]).equals("Status")
                                   || String.valueOf(dataList.get(i)[j]).equals("Picture")) {
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
//                                   pict.resize(); // Automatically resize the image to fit in the cellcell

                              }
                         } else if (dataList.get(i)[j] instanceof Double) {
                              cell.setCellStyle(style1);
                              cell.setCellValue((Double) dataList.get(i)[j]);
                         }
                    }
               }

               sheet.setColumnWidth(0, 20000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(1, 5000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(2, 3000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(3, 3000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(4, 3000); // Set the width of the first column to 5000 units

               // Create folder
               try {
                    Files.createDirectories(Paths.get(folderPath));
                    System.out.println("Folder created: " + folderPath);
               } catch (IOException e) {
                    System.err.println("Failed to create folder: " + e.getMessage());
                    return;
               }

               // Specify PDF file path
               String sourcePDFPath = downloadFolderPath + "/Downloads/EXCEL_Downloads/" + fileName + ".xlsx";

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
 
}
