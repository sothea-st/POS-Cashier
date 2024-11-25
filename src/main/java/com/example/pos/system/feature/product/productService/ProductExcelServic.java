package com.example.pos.system.feature.product.productService;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.*;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.pos.system.constant.JavaConstant;
import com.example.pos.system.domain.FileStore;
import com.example.pos.system.domain.Product;
import com.example.pos.system.layer.repository.FileStoreRepository;
import com.example.pos.system.feature.product.ProductRepository;
import com.example.pos.system.feature.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductExcelServic {
     private final FileStoreRepository fileStore;
     private final ProductRepository productRepository;

     public void importFileExcel(MultipartFile multipartFile) throws IOException {
          List<ProductResponse> products = readExcelFile(multipartFile);
          products.forEach(p -> {
               Product product = new Product();
               if (p.getBarcode() != null &&
                         p.getVendorId() != null &&
                         p.getCatId() != null &&
                         p.getProductName() != null) {

                    String uuid = JavaConstant.defaultNameImage;
                    if (p.getPath() != null) {
                         uuid = UUID.randomUUID().toString();
                         try {
                              String contentType = getContentType(
                                        p.getPath());
                              byte[] byteImage = getImageBytes(
                                        p.getPath());

                              FileStore f = new FileStore(uuid, uuid, contentType, byteImage);
                              fileStore.save(f);
                         } catch (IOException e) {
                              e.printStackTrace();
                         }
                    }

                    product.setBarcode(p.getBarcode());

                    // product.setVendorId(p.getVendorId());
                    // product.setBrandId(p.getBrandId());
                    product.setCatId(p.getCatId());
                    product.setProNameEn(p.getProductName());
                    product.setProNameKh(p.getProductNameKh());

                    product.setCost(p.getCost());
                    product.setPrice(p.getPrice());
                    product.setMargin(p.getMargin());
                    // product.setAttributeId(p.getAttributeId());
                    product.setChoices(p.getChoiceValue());

                    // product.setUomId(p.getUomId());
                    // product.setProductActiveId(p.getStatus());
                    // product.setCountryId(p.getCountryId());
                    // product.setTaxId(p.getTaxId());
                    product.setCreateBy(0);

                    product.setProductStatus(null);
                    product.setDiscount(BigDecimal.valueOf(0));
                    // product.setNote(null);
                    product.setProImageName(uuid);

                    productRepository.save(product);
               }
          });

     }

     public List<ProductResponse> readExcelFile(MultipartFile multipartFile) {
          List<ProductResponse> products = new ArrayList<>();

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
                    ProductResponse pro = new ProductResponse();

                    int cellIndex = 0;
                    while (cellIterator.hasNext()) {
                         Cell currentCell = cellIterator.next();

                         switch (currentCell.getCellType()) {
                              case STRING:
                                   String value = currentCell.getStringCellValue();

                                   switch (cellIndex) {
                                        case 1:
                                             pro.setVendorId(returnId(value));
                                             break;
                                        case 2:
                                             pro.setBrandId(returnId(value));
                                             break;
                                        case 3:
                                             pro.setCatId(returnId(value));
                                             break;
                                        case 4:
                                             pro.setProductName(value);
                                             break;
                                        case 5:
                                             pro.setProductNameKh(value);
                                             break;
                                        case 8:
                                             pro.setMargin(value);
                                             break;
                                        case 9:
                                             pro.setAttributeId(returnId(value));
                                             break;
                                        case 10:
                                             pro.setChoiceValue(value);
                                             break;
                                        case 11:
                                             pro.setUomId(returnId(value));
                                             break;
                                        case 13:
                                             pro.setCountryId(returnId(value));
                                             break;
                                        case 14:
                                             pro.setTaxId(returnId(value));
                                             break;
                                        case 15:
                                             pro.setPath(value);
                                             break;
                                        default:
                                             break;
                                   }
                                   break;

                              case NUMERIC:

                                   double numericValue = currentCell.getNumericCellValue();
                                   long intValue = (int) numericValue;
                                   BigDecimal decimalValue = BigDecimal.valueOf(numericValue);

                                   switch (cellIndex) {
                                        case 0:
                                             BigDecimal bigDecimalValue = new BigDecimal(
                                                       currentCell.getNumericCellValue());
                                             String stringValue = bigDecimalValue.toPlainString().trim();
                                             pro.setBarcode(String.valueOf(stringValue));
                                             break;
                                        case 6:
                                             pro.setCost(decimalValue);
                                             break;
                                        case 7:
                                             pro.setPrice(decimalValue);
                                             break;
                                        case 12:
                                             pro.setStatus((int)currentCell.getNumericCellValue());
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

     private Integer returnId(String value) {
          String[] str = value.split("=>");
          int id = Integer.parseInt(str[0].trim());
          return id;
     }

     public byte[] getImageBytes(String imagePath) throws IOException {
          File imageFile = new File(imagePath);

          // Check if file exists and is readable
          if (!imageFile.exists() || !imageFile.isFile() || !imageFile.canRead()) {
               throw new IOException("File cannot be read or does not exist: " + imagePath);
          }

          // Read file into byte array
          byte[] imageBytes;
          try (FileInputStream fis = new FileInputStream(imageFile)) {
               imageBytes = new byte[(int) imageFile.length()];
               fis.read(imageBytes);
          }

          return imageBytes;
     }

     public String getContentType(String imagePath) throws IOException {
          Path path = Paths.get(imagePath);
          return Files.probeContentType(path);
     }
}
