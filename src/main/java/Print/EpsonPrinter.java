/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Print;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JDialog;
import javax.swing.JPanel;
import pdf.MyPrinter;

/**
 *
 * @author MOBILE-APP.02
 */
public class EpsonPrinter {

     public static void printReceipt(JPanel panel) {
          // ============= print with device

          PrintRequestAttributeSet printAttributes = new HashPrintRequestAttributeSet();

          PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, printAttributes);
          if (printServices.length > 0) {
               PrinterJob printerJob = PrinterJob.getPrinterJob();
               try {
                    // Set the print service
                    printerJob.setPrintService(printServices[0]);

//                 PrinterJob printerJob = PrinterJob.getPrinterJob();
                    PageFormat pageFormat = printerJob.defaultPage();
                    Paper paper = new Paper();
                    paper.setSize(4.13 * 72, 5.83 * 72); // A6 size in points (1 inch = 72 points)
                    paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
                    pageFormat.setPaper(paper);

                    printerJob.setPrintable(new MyPrinter(panel), pageFormat);
                    // Print without showing the print dialog
                    printerJob.print();

               } catch (PrinterException ex) {
                    ex.printStackTrace();
               }
          } else {
               System.out.println("No printer found.");
          }
     }
     
     
    
}
