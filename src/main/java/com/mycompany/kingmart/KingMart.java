package com.mycompany.kingmart;

import View.MainPage.MainPage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;

/**
 *
 * @author FRONT-END.06
 */
public class KingMart {

     public static void main(String[] args) throws FileNotFoundException {
          MainPage mainPage = new MainPage();
          mainPage.setVisible(true);

          // ================ test 1 for print ===============
//          PrinterService printerService = new PrinterService();
//
//          System.out.println("data java print "  + printerService.getPrinters());
//          
//          //print some stuff. Change the printer name to your thermal printer name.
//          printerService.printString("EPSON s", "\n\n testing testing 1 2 3eeeee \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
//
//          // cut that paper!
//          byte[] cutP = new byte[]{0x1d, 'V', 1};
//
//          printerService.printBytes("EPSON s", cutP);
          // ================ test 2 for print ===============
//          try {
//               // Specify the printer name
//               String printerName = "Your_Epson_Printer_Name_Here";
//
//               // Set the number of copies to print
//               int numCopies = 1;
//
//               // Set the input file (file to be printed)
//               String filePath = "path_to_your_file_here";
//
//               // Create a FileInputStream for the file
//               FileInputStream fileInputStream = new FileInputStream(filePath);
//
//               // Create a DocFlavor for PDF files
//               DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
//
//               // Create a PrintRequestAttributeSet
//               PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
//               attributeSet.add(new Copies(numCopies));
//               attributeSet.add(new PrinterName(printerName, null));
//
//               // Get the default print service
//               PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
//
//               // Create a print job
//               DocPrintJob printJob = defaultPrintService.createPrintJob();
//
//               // Create a Doc object
//               Doc doc = new SimpleDoc(fileInputStream, flavor, null);
//
//               // Print the document
//               printJob.print(doc, attributeSet);
//          } catch (Exception e) {
//          }

 // ================ test 3 for print ===============
//          try {
//               // Specify the path to the PDF file
//               String filePath = "path_to_your_pdf_file_here.pdf";
//               Path pdfPath = Paths.get(filePath);
//
//               // Specify the printer name
//               String printerName = "Your_Epson_Printer_Name_Here";
//
//               // Specify the paper size (A6)
//               MediaSizeName mediaSize = MediaSizeName.ISO_A6;
//
//               // Set the number of copies to print
//               int numCopies = 1;
//
//               // Set up the print request attributes
//               PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
//               attributes.add(new Copies(numCopies));
//               attributes.add(mediaSize);
//
//               // Locate the default print service
//               PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
//
//               if (printService != null) {
//                    // Create a print job
//                    DocPrintJob printJob = printService.createPrintJob();
//
//                    // Read the PDF file
//                    FileInputStream inputStream = new FileInputStream(pdfPath.toFile());
//                    Doc doc = new SimpleDoc(inputStream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
//
//                    // Print the document
//                    printJob.print(doc, attributes);
//               } else {
//                    System.err.println("No suitable print service found.");
//               }
//          } catch (Exception e) {
//          }

     }

}
