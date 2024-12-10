
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;

 
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

public class ExcelComboBoxExample {

     public static void main(String[] args) {
          // Sample data
          List<String[]> data = Arrays.asList(
               new String[]{"Barcode", "Vendor Name", "Brand", "Sub Category", "Product Name", "Product Name Kh", "Cost", "Price", "Margin", "Attribute", "Choice Value", "UOM", "Status", "Country", "Tax"},
               new String[]{"8.85051E+12", "3 => TT-FRONT-END-06", "5 => BEER", "45 => COOKING CONDIMENTS", "Myungga Kimchi  11", "នំស្រោបសូកូឡា ៣៦០ក្រាម 1", "5.5", "12.55", "20%", "1 => Size", "1L", "1 => Weigh", "1 => Active", "9 => USA", "3 => VAT"}
          );

          try (XSSFWorkbook workbook = new XSSFWorkbook()) {
               XSSFSheet sheet = workbook.createSheet("Product Data");

               // Create Header Row
               XSSFRow headerRow = sheet.createRow(0);
               for (int i = 0; i < data.get(0).length; i++) {
                    XSSFCell cell = headerRow.createCell(i);
                    cell.setCellValue(data.get(0)[i]);

                    // Apply bold style to header
                    CellStyle style = workbook.createCellStyle();
                    Font font = workbook.createFont();
                    font.setBold(true);
                    style.setFont(font);
                    cell.setCellStyle(style);
               }

               // Add Data Rows
               for (int i = 1; i < data.size(); i++) {
                    XSSFRow row = sheet.createRow(i);
                    String[] rowData = data.get(i);
                    for (int j = 0; j < rowData.length; j++) {
                         XSSFCell cell = row.createCell(j);
                         cell.setCellValue(rowData[j]);
                    }
               }

               // Auto-size Columns
               for (int i = 0; i < data.get(0).length; i++) {
                    sheet.autoSizeColumn(i);
               }

               // Create Dropdowns for Combo Box Columns
               String[] vendorNames = {"3 => TT-FRONT-END-06", "4 => Vendor 2"};
               String[] brands = {"5 => BEER", "6 => WINE"};
               String[] subCategories = {"45 => COOKING CONDIMENTS", "46 => SPICES"};
               String[] attributes = {"1 => Size", "2 => Color"};
               String[] choiceValues = {"1L", "2L"};
               String[] uoms = {"1 => Weigh", "2 => Volume"};
               String[] statuses = {"1 => Active", "2 => Inactive"};
               String[] countries = {"9 => USA", "9 => JAPAN"};
               String[] taxes = {"3 => VAT", "2 => Non-VAT"};

               addDropdownList(sheet, vendorNames, 1, data.size(), 1);
               addDropdownList(sheet, brands, 1, data.size(), 2);
               addDropdownList(sheet, subCategories, 1, data.size(), 3);
               addDropdownList(sheet, attributes, 1, data.size(), 9);
               addDropdownList(sheet, choiceValues, 1, data.size(), 10);
               addDropdownList(sheet, uoms, 1, data.size(), 11);
               addDropdownList(sheet, statuses, 1, data.size(), 12);
               addDropdownList(sheet, countries, 1, data.size(), 13);
               addDropdownList(sheet, taxes, 1, data.size(), 14);

               // Write to File
               try (FileOutputStream fileOut = new FileOutputStream("ProductData.xlsx")) {
                    workbook.write(fileOut);
                    System.out.println("Excel file created successfully!");
               }
          } catch (Exception e) {
               e.printStackTrace();
          }
     }

     // Method to Add Dropdown List
     private static void addDropdownList(XSSFSheet sheet, String[] options, int startRow, int endRow, int col) {
          XSSFDataValidationHelper helper = new XSSFDataValidationHelper(sheet);
          XSSFDataValidationConstraint constraint = (XSSFDataValidationConstraint) helper.createExplicitListConstraint(options);
          CellRangeAddressList addressList = new CellRangeAddressList(startRow, endRow, col, col);
          XSSFDataValidation validation = (XSSFDataValidation) helper.createValidation(constraint, addressList);
          validation.setSuppressDropDownArrow(true);
          validation.setShowErrorBox(true);
          sheet.addValidationData(validation);
     }
}
