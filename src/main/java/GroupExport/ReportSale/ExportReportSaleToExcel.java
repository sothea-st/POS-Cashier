package GroupExport.ReportSale;

import Constant.JavaConstant;
import Model.Report.ReportSaleDetail;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static pdf.PrintToExcel.downloadFolderPath;
import static pdf.PrintToExcel.folderPath;

public class ExportReportSaleToExcel {

     public static void toExcel(ReportSaleDetail[] list) {

          String fileName = JavaConstant.getFileName();

          try {
               // Create a new Excel workbook
               Workbook workbook = new XSSFWorkbook();

               // Create a new Excel sheet
               Sheet sheet = workbook.createSheet("Sheet1");

               // Create an ArrayList to hold the rows
               ArrayList<Object[]> dataList = new ArrayList<>();
               dataList.add(new Object[]{
                    "#",
                    "Transaction",
                    "Date",
                    "Product Name",
                    "Qty",
                    "Price",
                    "Discount",
                    "Amount (Include Tax)",
                    "Tax Type",
                    "Total Sale Exclude VAT",
                    "VAT Amt",
                    "PLT",
                    "Net Sale",
                    "Cost",
                    "Margin",
                    "Staff"});

               // Add data rows to the ArrayList
               for (int i = 0; i < list.length; i++) {
                    var detail = list[i];
//                    String url = null;
//                    if (data.getProImageName().contains("media/file/crm/uploadfile/")) {
//                         url = "http://103.101.80.108:8082//" + data.getProImageName();
//                    } else {
//                         url = "http://localhost:8090/api/public/addImageForBackground/" + data.getProImageName();
//                    }
                    dataList.add(new Object[]{
                         String.valueOf(i + 1),
                         String.valueOf(detail.getInvoiceNumber()),
                         String.valueOf(detail.getSaleDate()),
                         String.valueOf(detail.getProNameEn()),
                         String.valueOf(detail.getQty()),
                         String.valueOf(detail.getPrice()),
                         String.valueOf(detail.getDiscount()),
                         String.valueOf(detail.getAmountWithTax()),
                         String.valueOf(detail.getTaxType()),
                         String.valueOf(detail.getTotalSaledExcludeVAT()),
                         String.valueOf(detail.getVatAmt()),
                         String.valueOf(detail.getPLT()),
                         String.valueOf(detail.getNetSale()),
                         String.valueOf(detail.getCost()),
                         String.valueOf(detail.getMargin()),
                         String.valueOf(detail.getUserName())
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
                    row.setHeightInPoints(25); // Adjust the height as needed

                    for (int j = 0; j < dataList.get(i).length; j++) {
                         Cell cell = row.createCell(j);
                         if (dataList.get(i)[j] instanceof String) {
                              if (String.valueOf(dataList.get(i)[j]).equals("#")
                                   || String.valueOf(dataList.get(i)[j]).equals("Transaction")
                                   || String.valueOf(dataList.get(i)[j]).equals("Date")
                                   || String.valueOf(dataList.get(i)[j]).equals("Product Name")
                                   || String.valueOf(dataList.get(i)[j]).equals("Qty")
                                   || String.valueOf(dataList.get(i)[j]).equals("Price")
                                   || String.valueOf(dataList.get(i)[j]).equals("Discount")
                                   || String.valueOf(dataList.get(i)[j]).equals("Amount (Include Tax)")
                                   || String.valueOf(dataList.get(i)[j]).equals("Tax Type")
                                   || String.valueOf(dataList.get(i)[j]).equals("Total Sale Exclude VAT")
                                   || String.valueOf(dataList.get(i)[j]).equals("VAT Amt")
                                   || String.valueOf(dataList.get(i)[j]).equals("PLT")
                                   || String.valueOf(dataList.get(i)[j]).equals("Net Sale")
                                   || String.valueOf(dataList.get(i)[j]).equals("Cost")
                                   || String.valueOf(dataList.get(i)[j]).equals("Margin")
                                   || String.valueOf(dataList.get(i)[j]).equals("Staff")) {
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

               sheet.setColumnWidth(0, 3000); // Set the width of the first column to 3000 units
               sheet.setColumnWidth(1, 7000); // Set the width of the first column to 7000 units
               sheet.setColumnWidth(2, 5000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(3, 20000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(4, 5000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(5, 5000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(6, 5000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(7, 8000); // Set the width of the first column to 8000 units
               sheet.setColumnWidth(8, 5000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(9, 10000); // Set the width of the first column to 10000 units
               sheet.setColumnWidth(10, 7000); // Set the width of the first column to 5000 units
               sheet.setColumnWidth(12, 5000); // Set the width of the first column to 5000 units

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
