package Reporting.export;

import Constant.JavaConstant;
 
import Reporting.model.ReportingDetailResponse;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
 
import java.util.ArrayList;
import static pdf.PrintToCSV.downloadFolderPath;
import static pdf.PrintToCSV.folderPath;

public class ExportReportPurchaseOrderToCSV {

     public static void toCSV(ArrayList<ReportingDetailResponse> list) {

          String fileName = JavaConstant.getFileName();

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
                    "Purchase Order No",
                    "Transaction No",
                    "Transaction Date",
                    "Order Date",
                    "Reference No",
                    "Vendor Name",
                    "Total Qty",
                    "Total Cost"});

               int index = 0;
               // Write data
               for (ReportingDetailResponse detail : list) {
                    index++;
                    String[] data = new String[]{
                         String.valueOf(index),
                         String.valueOf(detail.getPurchaseOrderNo()),
                         String.valueOf(detail.getTransactionNo()),
                         String.valueOf(detail.getTransactionDate()),
                         String.valueOf(detail.getOrderDate()),
                         String.valueOf(detail.getReferenceNo()),
                         String.valueOf(detail.getVendorName()),
                         String.valueOf(detail.getTotalQty()),
                         String.valueOf(detail.getTotalCost())};
                    writer.writeNext(data);
               }

               System.out.println("CSV file exported successfully to: " + filePath);
          } catch (IOException e) {
               e.printStackTrace();
          }

     }
}
