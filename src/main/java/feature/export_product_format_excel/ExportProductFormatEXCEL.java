package feature.export_product_format_excel;

 
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.ss.util.CellRangeAddressList;

public class ExportProductFormatEXCEL {

     public static String downloadFolderPaths = System.getProperty("user.home");
     public static String folderPaths = downloadFolderPaths + "\\Downloads\\EXCEL_Downloads";
     public static String sourcePDFPath = "";

     public static void exportProductFormatEXCEL() {

          // Sample data
          List<String[]> data = Arrays.asList(
               new String[]{
                    "Barcode *",
                    "Vendor Name *",
                    "Brand *",
                    "Sub Category *",
                    "Product Name *",
                    "Product Name Kh",
                    "Cost *",
                    "Price *",
                    "Margin",
                    "Attribute *",
                    "Choice Value *",
                    "UOM *",
                    "Status *",
                    "Country *",
                    "Tax *",
                    "Warehouse",
                    "Range",
                    "Slot"
               },
               new String[]{
                    "",
                    "---Select Vendor---",
                    "---Select Brand---",
                    "---Select Sub Category---",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "---Select Attribute---",
                    "",
                    "---Select Uom---",
                    "---Select Status---",
                    "---Select Country---",
                    "---Select Tax---",
                    "---Select Warehouse---",
                    "---Select Range---",
                    "---Select Slot---"
               }
          );

          try (XSSFWorkbook workbook = new XSSFWorkbook()) {
               XSSFSheet sheet = workbook.createSheet("Product Data");

               // Create Header Row
               XSSFRow headerRow = sheet.createRow(0);
               headerRow.setHeight((short) (30 * 20)); // Set header row height to 30px

               for (int i = 0; i < data.get(0).length; i++) {
                    XSSFCell cell = headerRow.createCell(i);
                    cell.setCellValue(data.get(0)[i]);

                    // Create a common style for header and data cells
                    CellStyle style = workbook.createCellStyle();
                    Font font = workbook.createFont();
                    font.setFontName("Times New Roman"); // Set font family to Times New Roman
                    font.setColor(IndexedColors.BLACK.getIndex()); // Set font color to black
                    font.setBold(true); // Set font bold
                    font.setFontHeightInPoints((short) 14); // Set font size to 14
                    style.setFont(font);

                    // Set background color for the header
                    style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex()); // Set background color
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Use solid fill pattern
                    // Set alignment for header cells
                    style.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
                    style.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically
                    cell.setCellStyle(style); // Apply the style to header cells
               }

               // Add Data Rows
               for (int i = 1; i < data.size(); i++) {
                    XSSFRow row = sheet.createRow(i);
                    row.setHeight((short) (30 * 20)); // Set each data row height to 30px

                    String[] rowData = data.get(i);
                    for (int j = 0; j < rowData.length; j++) {
                         XSSFCell cell = row.createCell(j);
                         cell.setCellValue(rowData[j]);

                         // Apply alignment style for data rows
                         CellStyle dataStyle = workbook.createCellStyle();
                         Font font1 = workbook.createFont();

                         font1.setFontHeightInPoints((short) 12); // Set font size to 14
                         dataStyle.setFont(font1);
                         dataStyle.setAlignment(HorizontalAlignment.CENTER); // Center horizontally
                         dataStyle.setVerticalAlignment(VerticalAlignment.CENTER); // Center vertically
                         cell.setCellStyle(dataStyle);
                    }
               }

               // Create Dropdowns for Combo Box Columns
               //======== vendor ===========
               List<String> vendorNamesList = JavaExportHelper.getVendorNames().stream()
                    .map(vendor -> vendor.getId() + " => " + vendor.getVendorName()).toList();
               String[] vendorNames = vendorNamesList.toArray(new String[0]);// Convert the list to an array
               //======== end vendor ===========

               //======== brand ===========
               List<String> listBrands = JavaExportHelper.getBrands().stream()
                    .map(brand -> brand.getId() + " => " + brand.getBrandNameEn()).toList();
               String[] brands = listBrands.toArray(new String[0]); // convert list to an array
               //======== end brand ===========

               //======== subcategory ===========
               List<String> listSubCategories = JavaExportHelper.getSubCategories().stream()
                    .map(subCategory -> subCategory.getId() + " => " + subCategory.getCatNameEn()).toList();
               String[] subCategories = listSubCategories.toArray(new String[0]);// convert list to an array
               //======== end subcategory ===========

               //======== attribute ===========
               List<String> listAtrributes = JavaExportHelper.getAttributes().stream()
                    .map(attr -> attr.getId() + " => " + attr.getAttrNameEn()).toList();
               String[] attributes = listAtrributes.toArray(new String[0]); // convert list to an array
               //======== end attribute ===========

               //======== uom ===========
               List<String> listUoms = JavaExportHelper.getUoms().stream()
                    .map(uom -> uom.getId() + " => " + uom.getUomNameEn()).toList();
               String[] uoms = listUoms.toArray(new String[0]); // convert list to an array
               //======== end uom ===========

               //======== status ===========
               List<String> listStatuses = JavaExportHelper.getStatus().stream()
                    .map(status -> status.getId() + " => " + status.getStatusName()).toList();
               String[] statuses = listStatuses.toArray(new String[0]); // convert list to an array
               //======== end status ===========

               //======== country ===========
               List<String> listCuntries = JavaExportHelper.getCountries().stream()
                    .map(c -> c.getId() + " => " + c.getCountryName()).toList();
               String[] countries = listCuntries.toArray(new String[0]); // convert list to an array
               //======== end country ===========

               //======== tax ===========
               List<String> listTaxs = JavaExportHelper.getTaxs().stream()
                    .map(c -> c.getId() + " => " + c.getTax_name()).toList();
               String[] taxes = listTaxs.toArray(new String[0]); // convert list to an array
               //======== end tax ===========

               //======== warehouse ===========
               List<String> listWarehouse = JavaExportHelper.getWarehouses().stream()
                    .map(c -> c.getId() + " => " + c.getWarehouseNameEn()).toList();
               String[] warehouses = listWarehouse.toArray(new String[0]); // convert list to an array
               //======== end warehouse ===========

               //======== range ===========
               List<String> listRanges = JavaExportHelper.getRanges().stream()
                    .map(c -> c.getId() + " => " + c.getRangeNameEn()).toList();
               String[] ranges = listRanges.toArray(new String[0]); // convert list to an array
               //======== end range ===========

               //======== range ===========
               List<String> listSlots = JavaExportHelper.getslots().stream()
                    .map(c -> c.getId() + " => " + c.getSlotNameEn()).toList();
               String[] slots = listSlots.toArray(new String[0]); // convert list to an array
               //======== end range ===========

               addDropdownList(sheet, vendorNames, 1, data.size(), 1);
               addDropdownList(sheet, brands, 1, data.size(), 2);
               addDropdownList(sheet, subCategories, 1, data.size(), 3);
               addDropdownList(sheet, attributes, 1, data.size(), 9);
               addDropdownList(sheet, uoms, 1, data.size(), 11);
               addDropdownList(sheet, statuses, 1, data.size(), 12);
               addDropdownList(sheet, countries, 1, data.size(), 13);
               addDropdownList(sheet, taxes, 1, data.size(), 14);
               addDropdownList(sheet, warehouses, 1, data.size(), 15);
               addDropdownList(sheet, ranges, 1, data.size(), 16);
               addDropdownList(sheet, slots, 1, data.size(), 17);

               // Create folder
               try {
                    Files.createDirectories(Paths.get(folderPaths));
                    System.out.println("Folder created: " + folderPaths);
               } catch (IOException e) {
                    System.err.println("Failed to create folder: " + e.getMessage());
                    return;
               }

               // Specify PDF file path
               sourcePDFPath = downloadFolderPaths + "\\Downloads\\EXCEL_Downloads\\Product_Sample_For_Import.xlsx";

               // Write the workbook content to a file
               try (FileOutputStream outputStream = new FileOutputStream(sourcePDFPath)) {
                    workbook.write(outputStream);
               }

               System.out.println("Excel file created successfully!");

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

          sheet.setColumnWidth(0, 5000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(1, 7000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(2, 5000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(3, 7000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(4, 8000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(5, 8000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(6, 5000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(7, 5000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(8, 5000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(9, 5000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(10, 5000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(11, 5000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(12, 5000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(13, 5000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(14, 5000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(15, 5000); // Set the width of the first column to 5000 units
          sheet.setColumnWidth(16, 5000); // Set the width of the first column to 5000 
          sheet.setColumnWidth(17, 5000); // Set the width of the first column to 5000 units
     }

}
