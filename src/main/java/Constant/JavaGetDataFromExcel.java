package Constant;

import Model.PackageProduct.ProductResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class JavaGetDataFromExcel {

     public List<ProductResponse> readExcelFile(String filePath) {
          List<ProductResponse> products = new ArrayList<>();

          try (InputStream inputStream = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(inputStream)) {

               Sheet sheet = workbook.getSheetAt(0); // assuming first sheet
               Iterator<Row> rowIterator = sheet.iterator();

               while (rowIterator.hasNext()) {
                    Row currentRow = rowIterator.next();

                    // Skip header row
                    if (currentRow.getRowNum() == 0) {
                         continue;
                    }
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    ProductResponse pro = new ProductResponse();

                    int cellIndex = 0;
                    while (cellIterator.hasNext()) {
                         Cell currentCell = cellIterator.next();

                         switch (currentCell.getCellType()) {
                              case STRING -> {
                                   String value = currentCell.getStringCellValue();

                                   switch (cellIndex) {
                                        case 1 ->
                                             pro.setVendorId(returnId(value));
                                        case 2 ->
                                             pro.setBrandId(returnId(value));
                                        case 3 ->
                                             pro.setSubCatId(returnId(value));
                                        case 4 ->
                                             pro.setProductName(value);
                                        case 5 ->
                                             pro.setProductNameKh(value);
                                        case 9 ->
                                             pro.setAttributeId(returnId(value));
                                        case 10 ->  
                                             pro.setChoiceValue(value);
                                        case 11 ->
                                             pro.setUomId(returnId(value));
                                        case 12 ->
                                             pro.setStatusId(returnId(value));
                                        case 13 ->
                                             pro.setCountryId(returnId(value));
                                        case 14 ->
                                             pro.setTaxId(returnId(value));
                                        case 15 ->
                                             pro.setWarehouseId(returnId(value));
                                        case 16 ->
                                             pro.setRangeId(returnId(value));
                                        case 17 ->
                                             pro.setSlotId(returnId(value));
                                        default -> {
                                        }
                                   }
                              }
                              //

                              case NUMERIC -> {
                                   double numericValue = currentCell.getNumericCellValue();
                                   BigDecimal decimalValue = BigDecimal.valueOf(numericValue);
                                   Double percenTag = numericValue * 100;
                                   switch (cellIndex) {
                                        case 0 -> {
                                             BigDecimal bigDecimalValue = new BigDecimal(
                                                  currentCell.getNumericCellValue());
                                             String stringValue = bigDecimalValue.toPlainString().trim();
                                             pro.setBarcode(String.valueOf(stringValue));
                                        }
                                        case 6 ->
                                             pro.setCost(decimalValue);
                                        case 7 ->
                                             pro.setPrice(decimalValue);
                                        case 8 ->
                                             pro.setMargin(percenTag + "%");
                                        default -> {
                                        }
                                   }
                              }

                              default -> {
                              }
                         }
                         cellIndex++;
                    }
                    products.add(pro);
               }

          } catch (IOException e) {
               e.printStackTrace();
          }

          return products;
     }

     private Integer returnId(String value) {
          if (value.contains("---")) {
               return null;
          }
          String[] str = value.split("=>");
          int id = Integer.parseInt(str[0].trim());
          return id;
     }
}
