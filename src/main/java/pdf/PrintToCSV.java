/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdf;

import Model.PackageProduct.ProductModel;
import View.MainPage.CSVExporter;
import com.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class PrintToCSV {
     // create folder in specific path

     public static String downloadFolderPath = System.getProperty("user.home");
     public static String folderPath = downloadFolderPath + "\\Downloads\\CSV_Downloads";

     public static void exportToCSV(ArrayList<ProductModel> listProduct) {

          LocalDate currentDate = LocalDate.now();
          // Define a custom date format
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
          // Format the current date using the defined format
          String formattedDate = currentDate.format(formatter);
          String fileName = formattedDate;

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

          try (CSVWriter  writer = new CSVWriter(new FileWriter(filePath + "\\" + fileName + ".csv"))) {
               // Write headers
               writer.writeNext(new String[]{"Product Name", "Barcode", "Price", "Quantity", "Status"});

               // Write data
               for (ProductModel p : listProduct) {
                    String[] data = new String[]{
                         p.getProductNameEn(),
                         "\"" + p.getBarcode() + "\"",
                         String.valueOf(p.getPrice()),
                         String.valueOf(p.getQty()),
                         p.getProductStatus()
                    };
                    writer.writeNext(data);
               }
               System.out.println("CSV file exported successfully to: " + filePath);
          } catch (IOException e) {
               e.printStackTrace();
          }

 
     }

}
