/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GroupExport.ReportSale;

import Constant.JavaConstant;
import Model.Report.ReportImportDetail;
import Model.Report.ReportSaleDetail;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static pdf.PrintToCSV.downloadFolderPath;
import static pdf.PrintToCSV.folderPath;

/**
 *
 * @author MOBILE-APP.02
 */
public class ExportReportSaleToCSV {

     public static void toCSV(ReportSaleDetail[] list) {

        String fileName = JavaConstant.getFileName(ExportReportSaleToExcel.reportSale);

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
                    "Amount (Include Tax)",
                    "Tax Type",
                    "Total Sale Exclude VAT",
                    "VAT Amt",
                    "PLT",
                    "Net Sale",
                    "Cost",
                    "Margin",
                    "Staff"});

               int index = 0;
               // Write data
               for (ReportSaleDetail detail : list) {
                    index++;
                    String[] data = new String[]{
                         String.valueOf(index),
                         String.valueOf(detail.getInvoiceNumber()),
                         String.valueOf(detail.getSaleDate()),
                         String.valueOf(detail.getProNameEn()),
                         String.valueOf(detail.getQty()),
                         "$".concat(String.valueOf(detail.getPrice())),
                         "$".concat(String.valueOf(detail.getDiscount())),
                         "$".concat(String.valueOf(detail.getAmountWithTax())),
                         String.valueOf(detail.getTaxType()),
                         "$".concat(String.valueOf(detail.getTotalSaledExcludeVAT())),
                         "$".concat(String.valueOf(detail.getVatAmt())),
                         "$".concat(String.valueOf(detail.getPLT())),
                         "$".concat(String.valueOf(detail.getNetSale())),
                         "$".concat(String.valueOf(detail.getCost())),
                         "$".concat(String.valueOf(detail.getMargin())),
                         String.valueOf(detail.getUserName())};
                    writer.writeNext(data);
               }

               System.out.println("CSV file exported successfully to: " + filePath);
          } catch (IOException e) {
               e.printStackTrace();
          }

     }
}
