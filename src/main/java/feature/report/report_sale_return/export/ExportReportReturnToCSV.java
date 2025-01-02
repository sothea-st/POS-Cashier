 
package feature.report.report_sale_return.export;
 
import Constant.JavaConstant;
 
import com.opencsv.CSVWriter;
import feature.report.report_sale_return.dto.ReportSaleReturnResponse.ReportSaleReturnDetailResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static pdf.PrintToCSV.downloadFolderPath;
import static pdf.PrintToCSV.folderPath;

public class ExportReportReturnToCSV {
      public static void toCSV(ReportSaleReturnDetailResponse[] list) {

        String fileName = JavaConstant.getFileName(ExportReportReturnToEXCEL.reportSale);

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
                    "Invoice №",
                    "Date",
                    "Product Name",
                    "Qty",
                    "Price",
                    "Discount",
                    "Cost",
                    "Reason",
                    "Staff"});

               int index = 0;
               // Write data
               for (ReportSaleReturnDetailResponse detail : list) {
                    index++;
                    String[] data = new String[]{
                         String.valueOf(index),
                         String.valueOf(detail.getInvoiceNo()),
                         String.valueOf(detail.getDate()),
                         String.valueOf(detail.getProductName()),
                         String.valueOf(detail.getQty()),
                         JavaConstant.setAmount(detail.getPrice()),
                         String.valueOf(detail.getDiscount()),
                         JavaConstant.setAmount(detail.getCost()),
                         String.valueOf(detail.getReason()),
                         String.valueOf(detail.getStaff())
                    };
                    writer.writeNext(data);
               }

               System.out.println("CSV file exported successfully to: " + filePath);
          } catch (IOException e) {
               e.printStackTrace();
          }

     }
}
