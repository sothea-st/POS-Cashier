package feature.Print.pdf;

import Model.ProductModelV1.ProductResponseDetailV1;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PrintToCSV {
     // create folder in specific path

     public static String downloadFolderPath = System.getProperty("user.home");
     public static String folderPath = downloadFolderPath + "\\Downloads\\CSV_Downloads";

     public static void exportToCSV(ProductResponseDetailV1[] listProduct) {

          String fileName = PrintToExcel.getFileName("Product");

          // Create the directory if it doesn't exist
          Path directoryPath = Paths.get(folderPath);

          if (!Files.exists(directoryPath)) {
               try {
                    Files.createDirectories(directoryPath);
                    System.out.println("Directory created: ");
               } catch (IOException e) {
                    e.printStackTrace();
                    return;
               }
          }

          // Specify PDF file path
          String filePath = downloadFolderPath + "\\Downloads\\CSV_Downloads\\";

          try (CSVWriter writer = new CSVWriter(new FileWriter(filePath + "\\" + fileName + ".csv"))) {
               // Write headers
               writer.writeNext(new String[]{"Barcode", "Item Code", "Sub Category", "Vendor Code", "Vendor Name",
                    "Product Name", "Product Name Kh", "Total Qty", "Sale Price", "Cost"});

               // Write data
               for (ProductResponseDetailV1 p : listProduct) {
                    String proNameKh = p.getProNameKh();
                    if (proNameKh == null || proNameKh.isEmpty()) {
                         proNameKh = "";
                    } else {
                         proNameKh = p.getProNameKh() + " " + p.getChoices();
                    }
                    String[] data = new String[]{
                         "\"" + p.getBarcode() + "\"",
                         String.valueOf(p.getItemCode()),
                         String.valueOf(p.getSubCatNameEn()),
                         String.valueOf(p.getVendorCode()),
                         String.valueOf(p.getVendorName()),
                         String.valueOf(p.getProNameEn()) + " " + p.getChoices(),
                         proNameKh,
                         String.valueOf(p.getQty()),
                         "$".concat(String.valueOf(p.getPrice())),
                         "$".concat(String.valueOf(p.getCost())),};
                    writer.writeNext(data);
               }
               System.out.println("CSV file exported successfully to: " + filePath);
          } catch (IOException e) {
               e.printStackTrace();
          }

     }

}
