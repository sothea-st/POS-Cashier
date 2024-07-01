package com.example.pos.connection1.service.product_service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.util.IOUtils;

import java.io.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.IOException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.pos.connection1.entity.Product;
import com.example.pos.connection1.entity.models.testexcel.ProductExcel;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductExcelServic {


     public void importFileExcel(MultipartFile multipartFile) throws IOException {
          List<Product> products =  readExcelFile(multipartFile);
          products.forEach(p->System.out.println(p));
     }

     public List<Product> readExcelFile(MultipartFile multipartFile) {
          List<Product> products = new ArrayList<>();

          try (InputStream inputStream = multipartFile.getInputStream();
                    Workbook workbook = new XSSFWorkbook(inputStream)) {

               Sheet sheet = workbook.getSheetAt(0); // assuming first sheet
               Iterator<Row> rowIterator = sheet.iterator();

               while (rowIterator.hasNext()) {
                    Row currentRow = rowIterator.next();

                    // Skip header row
                    if (currentRow.getRowNum() == 0) {
                         continue;
                    }

                    Iterator<Cell> cellIterator = currentRow.iterator();
                    Product pro = new Product();

                    int cellIndex = 0;
                    while (cellIterator.hasNext()) {
                         Cell currentCell = cellIterator.next();

                         switch (currentCell.getCellType()) {
                              case STRING:
                                   String value = currentCell.getStringCellValue();

                                   switch (cellIndex) {
                                        case 0:
                                             if (value.equals("One")) {
                                                  pro.setCatId(11);
                                             } else if (value.equals("Two")) {
                                                  pro.setCatId(4444);
                                             } else if (value.equals("Three")) {
                                                  pro.setCatId(5666);
                                             }
                                             break;
                                        case 1:
                                             pro.setProNameKh(value);
                                             break;
                                        case 2:
                                             pro.setProNameEn(value);
                                             break;
                                        case 5:
                                             pro.setWeight(value);
                                             break;
                                        case 6:
                                             pro.setBarcode(value.replace("\"", ""));
                                             break;
                                        case 8:
                                             pro.setProductStatus(value);
                                             break;
                                        case 9:
                                             pro.setNote(value);
                                             break;
                                        case 14:
                                             pro.setVendorUuid(value);
                                             break;
                                        case 15:
                                             pro.setCountryUuid(value);
                                             break;
                                        case 16:
                                             pro.setProductActive(value);
                                             break;
                                        case 17:
                                             pro.setUomUuid(value);
                                             break;
                                        case 18:
                                             pro.setAttributeUuid(value);
                                             break;
                                        case 19:
                                             pro.setChoices(value);
                                             break;
                                        default:
                                             break;
                                   }
                                   break;

                              case NUMERIC:
                                   if (DateUtil.isCellDateFormatted(currentCell)) {
                                        // Handle date cell if needed
                                        break;
                                   }
                                   double numericValue = currentCell.getNumericCellValue();
                                   int intValue = (int) numericValue;
                                   BigDecimal decimalValue = BigDecimal.valueOf(numericValue);

                                   switch (cellIndex) {
                                        case 3:
                                             pro.setCost(decimalValue);
                                             break;
                                        case 4:
                                             pro.setPrice(decimalValue);
                                             break;
                                        case 10:
                                             pro.setBrandId(intValue);
                                             break;
                                        case 11:
                                             pro.setCreateBy(intValue);
                                             break;
                                        case 12:
                                             pro.setTaxId(intValue);
                                             break;
                                        case 13:
                                             pro.setProQty(intValue);
                                             break;
                                        case 20:
                                             pro.setMargin(decimalValue);
                                             break;
                                        default:
                                             break;
                                   }
                                   break;

                              default:
                                   break;
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

}
