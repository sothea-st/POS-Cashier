/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Constant;

import jpos.JposException;
import jpos.POSPrinter;
import jpos.POSPrinterConst;

/**
 *
 * @author MOBILE-APP.02
 */
public class Test {
       public static void main(String[] args) {
        // Initialize the printer
        try {
            // Create an instance of the printer
            POSPrinter printer = new POSPrinter();
            
            // Open the printer using the JavaPOS library
            printer.open("POSPrinter");
            
            // Claim the printer for exclusive use
            printer.claim(1000);
            
            // Enable the printer for use
            printer.setDeviceEnabled(true);
            
            // Print a sample receipt
            printer.printNormal(POSPrinterConst.PTR_S_RECEIPT, "Hello, World! 3333\n");
             System.err.println("hhhhhhhhhhhhhhhhhh");
            // Cut the paper
            printer.cutPaper(100);
            
            // Close the printer
            printer.close();
        } catch (JposException e) {
            e.printStackTrace();
        }
    }
}
