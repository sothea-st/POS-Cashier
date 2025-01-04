 
package Reporting.ReportInventory.export;

import Constant.JavaConstant;
import Reporting.ReportInventory.ReportInventoryModel;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static feature.Print.pdf.PrintToCSV.downloadFolderPath;
import static feature.Print.pdf.PrintToCSV.folderPath;

 
public class ExportReportInventoryToCSV {
       public static void toCSV(ArrayList<ReportInventoryModel.ReportInventoryDetail> list) {

          String fileName = JavaConstant.getFileName("Stock Balance Report");

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
               writer.writeNext(new String[]{
                     "#",
                    "Date",
                    "Product Name",
                    "Beginning Qty",
                    "Stock In Qty",
                    "Available Qty",
                    "Stock Out Qty",
                    "Return In Qty",
                    "Return Out Qty",
                    "Ending Qty"});

               int index = 0;
               // Write data
               for (ReportInventoryModel.ReportInventoryDetail detail : list) {
                    index++;
                    String[] data = new String[]{
                         String.valueOf(index),
                         String.valueOf(detail.getDate()),
                         String.valueOf(detail.getProductName()),
                         String.valueOf(detail.getBeginningQty()),
                         String.valueOf(detail.getStockInQty()),
                         String.valueOf(detail.getAvailableQty()),
                         String.valueOf(detail.getStockOutQty()),
                         String.valueOf(detail.getReturnInQty()),
                         String.valueOf(detail.getReturnOutQty()),
                         String.valueOf(detail.getEndingQty()) };
                    writer.writeNext(data);
               }

               System.out.println("CSV file exported successfully to: " + filePath);
          } catch (IOException e) {
               e.printStackTrace();
          }

     }
}
