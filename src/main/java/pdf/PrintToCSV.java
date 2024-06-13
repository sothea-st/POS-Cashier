/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdf;

import Model.PackageProduct.ProductModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
 
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
 
          try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + "\\" + fileName + ".csv"))) {

               ArrayList<Object[]> data = new ArrayList<>();
               data.add(new Object[]{"Product Name", "Barcode", "Price", "Quantity", "Status"});
               for (ProductModel p : listProduct) {
                    data.add(new Object[]{
                         p.getProductNameEn(),
                         "" + p.getBarcode(),
                         p.getPrice(),
                         p.getQty(),
                         p.getProductStatus()
                    });
               }

               for (Object[] row : data) {
                    StringBuilder rowString = new StringBuilder();
                    for (int i = 0; i < row.length; i++) {
                         rowString.append(row[i]);
                         if (i < row.length - 1) {
                              rowString.append(",");
                         }
                    }
                    writer.write(rowString.toString());
                    writer.newLine();
               }

 

               System.out.println("CSV file exported successfully to: " + filePath);
          } catch (IOException e) {
               e.printStackTrace();
          }
     }

//     public static void main(String[] arg) {
//
//          // Sample data
//          ArrayList<Object[]> data = new ArrayList<>();
//          data.add(new Object[]{"Name", "Age", "Image Path"});
//          data.add(new Object[]{"John Doe", 30, "path/to/john.jpg"});
//          data.add(new Object[]{"Jane Smith", 25, "path/to/jane.jpg"});
//          data.add(new Object[]{"Michael Johnson", 35, "path/to/michael.jpg"});
//
//          exportToCSV(data);
//     }
}
