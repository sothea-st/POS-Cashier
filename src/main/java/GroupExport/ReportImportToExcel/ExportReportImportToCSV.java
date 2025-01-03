package GroupExport.ReportImportToExcel;

import Model.PackageProduct.ProductModel;
import Model.Report.ReportImportDetail;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static feature.Print.pdf.PrintToCSV.downloadFolderPath;
import static feature.Print.pdf.PrintToCSV.folderPath;

public class ExportReportImportToCSV {

     public static void toCSV(ReportImportDetail[] list) {

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

          try (CSVWriter writer = new CSVWriter(new FileWriter(filePath + "\\" + fileName + ".csv"))) {

               // Write headers
               writer.writeNext(new String[]{"#", "Product Name", "Product Barcode", "Cost", "Quantity", "Amount", "Total", "Discount", "Import Date"});

               int index = 0;
               // Write data
               for (ReportImportDetail p : list) {
                    index++;
                    String[] data = new String[]{
                         String.valueOf(index),
                         String.valueOf(p.getProNameEn()),
                            "\""+p.getBarcode()+"\"",
                         String.valueOf(p.getCost()),
                         String.valueOf(p.getQtyOld()),
                         String.valueOf(p.getAmount()),
                         String.valueOf(p.getTotal()),
                         String.valueOf(p.getDiscount()),
                         String.valueOf(p.getImpDate())};
                    writer.writeNext(data);
               }

               System.out.println("CSV file exported successfully to: " + filePath);
          } catch (IOException e) {
               e.printStackTrace();
          }

     }
}
