package main.main_export;

import Components.Fonts.WindowFonts;
import Constant.JavaConstant;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class MainExportExcel extends MainExport {
     // create folder in specific path

     protected Workbook workbook = new XSSFWorkbook();
     protected Sheet sheet = workbook.createSheet("Sheet1");
     protected ArrayList<Object[]> dataList = new ArrayList<>();

     protected CellStyle styleKh = workbook.createCellStyle();
     protected CellStyle styleTitleEn = workbook.createCellStyle();

     protected CellStyle styleNormal = workbook.createCellStyle();
     protected CellStyle styleHorizontalLeft = workbook.createCellStyle();
     protected CellStyle styleHeader = workbook.createCellStyle();
     protected CellStyle styleNormalBorder = workbook.createCellStyle();
     protected Font font3;
     protected String[] columnHeader;
     private CellStyle styleTitle = workbook.createCellStyle();
     protected String titleKh;
     protected String titleEn;

     public MainExportExcel(String[] columnHeader, String titleEn, String titleKh) {

          this.columnHeader = columnHeader;
          this.titleEn = titleEn;
          this.titleKh = titleKh;

          // add image
          InputStream inputStream = getClass().getClassLoader().getResourceAsStream("company/logoTT.png");
          if (inputStream == null) {
               System.err.println("Image not found!");
               return;
          }

          try {
               // Read the image into a BufferedImage
               BufferedImage bufferedImage = ImageIO.read(inputStream);

               // Convert BufferedImage to byte array
               ByteArrayOutputStream baos = new ByteArrayOutputStream();
               ImageIO.write(bufferedImage, "png", baos); // Ensure to match the image type

               byte[] imageB = baos.toByteArray();

               // Adding data to the list, centering based on columnHeader length
               addCenteredRow(imageB, columnHeader.length);
               addCenteredRow(titleKh, columnHeader.length);
               addCenteredRow(titleEn, columnHeader.length);
               addCenteredRow("Date: " + JavaConstant.getCurrentDate(), columnHeader.length);
          } catch (Exception e) {
               System.err.println("error ===== " + e);
          }

          // Empty row for spacing
          dataList.add(new Object[]{"", "", "", "", ""});

          dataList.add(columnHeader); // add header to dataList

          // call customStyle
          customStyle();
     }
 
     protected abstract void setData();

     public void export() {
          setData();
          styleExcel();
     }

     protected void styleExcel() {
          try {

               for (int i = 0; i < dataList.size(); i++) {
                    Row row = sheet.createRow(i);
                    row.setHeightInPoints(30); // Adjust the height as needed
                    for (int j = 0; j < dataList.get(i).length; j++) {
                         Cell cell = row.createCell(j);
                         String value = String.valueOf(dataList.get(i)[j]);

                         if (dataList.get(i)[j] instanceof String) {

                              if (i < 5) {
                                   if (JavaConstant.containsKhmer(value)) { // style khmer text
                                        cell.setCellStyle(styleKh);
                                   } else {
                                        cell.setCellStyle(styleTitle); //  style english text
                                   }
                                   cell.setCellValue(value);
                                   continue;
                              }

                              if (columnHeader[j].equals(dataList.get(i)[j])) { // check header style
                                   cell.setCellStyle(styleHeader);
                                   cell.setCellValue(value);
                                   addBordersToStyle(styleHeader);
                              } else {
                                   CellStyle tempStyle = workbook.createCellStyle();

                                   if (!value.isEmpty()) {
                                        tempStyle.cloneStyleFrom(styleNormalBorder); // Clone base style
                                        addBordersToStyle(tempStyle); // Ensure borders are added
                                        cell.setCellStyle(tempStyle); // Apply the modified style
                                        cell.setCellValue(value);
                                   } else {
                                        removeBordersFromStyle(tempStyle); // Modify tempStyle without borders
                                        cell.setCellStyle(tempStyle); // Apply the modified style
                                   }

                              }

                         } else if (dataList.get(i)[j] instanceof Integer) {
                              cell.setCellStyle(styleNormal);
                              cell.setCellValue(value);
                              addBordersToStyle(styleNormal);
                         } else if (dataList.get(i)[j] instanceof byte[]) {

                              // No border for images
                              //cell.setCellStyle(styleNormal);
                              byte[] imageBytes = (byte[]) dataList.get(i)[j];

                              if (imageBytes != null) {
                                   int pictureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_JPEG);
                                   CreationHelper helper = workbook.getCreationHelper();
                                   Drawing<?> drawing = sheet.createDrawingPatriarch();
                                   ClientAnchor anchor = helper.createClientAnchor();

                                   // Set image to start at row 0 and column 0
                                   anchor.setCol1(0); // Column 0
                                   anchor.setRow1(0); // Row 0
                                   anchor.setCol2(1); // Column 1 (image width)
                                   anchor.setRow2(3); // Row 3 (image height)

                                   Picture pict = drawing.createPicture(anchor, pictureIdx);

                                   // Resize the image to fit the dimensions (0-0 for position, 3 for height)
                                   pict.resize(1.0); // Adjust this factor if needed

                              }
                         } else if (dataList.get(i)[j] instanceof Double) {
                              cell.setCellStyle(styleNormal);
                              cell.setCellValue(value);
                              addBordersToStyle(styleNormal);
                         }
                    }

               }

               // auto resize width cell
               for (int col = 0; col < dataList.get(0).length; col++) {

                    if (col != 0) {
                         int maxLength = 0;
                         for (Object[] row : dataList) {
                              if (col < row.length && row[col] != null) {  // Ensure index exists
                                   int cellLength = row[col].toString().length();
                                   if (cellLength > maxLength) {
                                        maxLength = cellLength;
                                   }
                              }
                         }
                         int adjustedWidth = (maxLength + 2) * 356; // Adding padding for better appearance
                         sheet.setColumnWidth(col, Math.min(adjustedWidth, 356 * 50));
                    } else {
                         sheet.setColumnWidth(0, 5000);
                    }

               }

               // Create folder
               try {
                    Files.createDirectories(Paths.get(folderPath));
                    System.out.println("Folder created: " + folderPath);
               } catch (IOException e) {
                    System.err.println("Failed to create folder: " + e.getMessage());
                    return;
               }

               // Specify PDF file path
               String sourcePath = downloadFolderPath + "\\Downloads\\EXCEL_Downloads\\" + getFileName(titleEn) + ".xlsx";

               // Write the workbook content to a file
               try (FileOutputStream outputStream = new FileOutputStream(sourcePath)) {
                    workbook.write(outputStream);
               }

               System.out.println("Excel file created successfully!");

               // show dialog path file was save
               msgPrint(sourcePath);

          } catch (Exception e) {
               System.out.println("An error occurred: " + e.getMessage());
               e.printStackTrace();
          }

     }


     private void addCenteredRow(Object content, int totalColumns) {

          Object[] row = new Object[totalColumns];
          int centerCol = totalColumns / 2;
          row[centerCol] = content;
          dataList.add(row);

     }

     private void addBordersToStyle(CellStyle style) {
          style.setBorderTop(BorderStyle.THIN);
          style.setBorderBottom(BorderStyle.THIN);
          style.setBorderLeft(BorderStyle.THIN);
          style.setBorderRight(BorderStyle.THIN);
          style.setTopBorderColor(IndexedColors.BLACK.getIndex());
          style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
          style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
          style.setRightBorderColor(IndexedColors.BLACK.getIndex());
     }

     private void removeBordersFromStyle(CellStyle style) {
          style.setBorderTop(BorderStyle.NONE);
          style.setBorderBottom(BorderStyle.NONE);
          style.setBorderLeft(BorderStyle.NONE);
          style.setBorderRight(BorderStyle.NONE);
     }

     private void customStyle() {
          // header style
          styleHeader.setAlignment(HorizontalAlignment.CENTER);
          styleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
          styleHeader.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); // Choose a gray shade
          styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Apply the fill pattern
          Font fontHeader = workbook.createFont();
          //  font1.setColor(IndexedColors.BLACK.getIndex()); // Set font color to red
          fontHeader.setBold(true); // Set font bold
          fontHeader.setFontName(WindowFonts.timeNewRomance); // Set the font name to Khmer OS Muol Light
          fontHeader.setFontHeightInPoints((short) WindowFonts.fontSize11ExportToExcel); // Set the font size to 11
          styleHeader.setFont(fontHeader);

          //   style kh
          styleKh.setAlignment(HorizontalAlignment.CENTER);
          styleKh.setVerticalAlignment(VerticalAlignment.CENTER);
          Font font = workbook.createFont();
          font.setColor(IndexedColors.BLACK.getIndex()); // Set font color to red
          // font.setBold(true); // Set font bold
          font.setFontName(WindowFonts.khmerOSMoulLight); // Set the font name to Khmer OS Muol Light
          font.setFontHeightInPoints((short) WindowFonts.fontSize11ExportToExcel); // Set the font size to 11
          styleKh.setFont(font);

          // nomal style
          styleNormal.setAlignment(HorizontalAlignment.CENTER);
          styleNormal.setVerticalAlignment(VerticalAlignment.CENTER);
          Font font1 = workbook.createFont();
          // font1.setColor(IndexedColors.BLACK.getIndex()); // Set font color to red
          //  font.setBold(true); // Set font bold
          font1.setFontName(WindowFonts.timeNewRomance); // Set the font name to Khmer OS Muol Light
          font1.setFontHeightInPoints((short) WindowFonts.fontSize11ExportToExcel); // Set the font size to 11
          styleNormal.setFont(font1);

          // nomal style with border
          styleNormalBorder.setAlignment(HorizontalAlignment.CENTER);
          styleNormalBorder.setVerticalAlignment(VerticalAlignment.CENTER);
          Font font2 = workbook.createFont();
          font2.setFontName(WindowFonts.timeNewRomance); // Set the font name to Khmer OS Muol Light
          font2.setFontHeightInPoints((short) WindowFonts.fontSize11ExportToExcel); // Set the font size to 11
          styleNormalBorder.setFont(font2);

          // styleHorizontalLeft
          styleHorizontalLeft.setAlignment(HorizontalAlignment.LEFT);
          styleHorizontalLeft.setVerticalAlignment(VerticalAlignment.CENTER);
          font3 = workbook.createFont();
          font3.setFontName(WindowFonts.timeNewRomance);
          font3.setFontHeightInPoints((short) WindowFonts.fontSize11ExportToExcel); // Set the font size to 11
          styleHorizontalLeft.setFont(font3);

          // style title
          Font fontTitle = workbook.createFont();
          fontTitle.setColor(IndexedColors.BLACK.getIndex()); // Set font color to red
          fontTitle.setBold(true); // Set font bold
          fontTitle.setFontName(WindowFonts.timeNewRomance);
          fontTitle.setFontHeightInPoints((short) WindowFonts.fontSize11ExportToExcel); // Set the font size to 11
          styleTitle.setFont(fontTitle);
          styleTitle.setAlignment(HorizontalAlignment.CENTER); // Set text alignment to center
          styleTitle.setVerticalAlignment(VerticalAlignment.CENTER); // Set vertical alignment to center
     }
}
