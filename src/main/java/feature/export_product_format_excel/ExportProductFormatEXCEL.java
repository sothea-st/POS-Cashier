package feature.export_product_format_excel;

import Constant.JavaConnection;
import Constant.JavaRoute;
import Model.Brand.BrandModel;
import Model.Brand.BrandSuccessModel;
import Model.Category.CategoryGetdataModel;
import Model.Category.CategorySuccessModel;
import Model.Vendor.DataVendorModel;
import Model.Vendor.ListVendorModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.xssf.usermodel.*;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import okhttp3.Response;
import org.apache.poi.ss.util.CellRangeAddressList;

public class ExportProductFormatEXCEL {

     public static String downloadFolderPaths = System.getProperty("user.home");
     public static String folderPaths = downloadFolderPaths + "\\Downloads\\EXCEL_Downloads";
     public static String sourcePDFPath = "";

     public static void exportProductFormatEXCEL() {

          // Sample data
          List<String[]> data = Arrays.asList(
               new String[]{
                    "Barcode",
                    "Vendor Name",
                    "Brand",
                    "Sub Category",
                    "Product Name",
                    "Product Name Kh",
                    "Cost",
                    "Price",
                    "Margin",
                    "Attribute",
                    "Choice Value",
                    "UOM",
                    "Status",
                    "Country",
                    "Tax",
                    "Warehouse",
                    "Range",
                    "Slot"
               },
               new String[]{
                    "8.85051E+12",
                    "3 => TT-FRONT-END-06",
                    "5 => BEER",
                    "45 => COOKING CONDIMENTS",
                    "Myungga Kimchi  11",
                    "នំស្រោបសូកូឡា ៣៦០ក្រាម 1",
                    "5.5",
                    "12.55",
                    "20%",
                    "1 => Size",
                    "1L",
                    "1 => Weigh",
                    "1 => Active",
                    "9 => USA",
                    "3 => VAT",
                    "3 => Location 1",
                    "1 => Range 1",
                    "1 => Slot 1"
               }
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
               sourcePDFPath = downloadFolderPaths + "/Downloads/EXCEL_Downloads/Product Format.xlsx";

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
     }

}
