/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Print;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JPanel;

/**
 *
 * @author MOBILE-APP.02
 */
public class ReceiptPrinter implements Printable {

     private JPanel panel;

     public ReceiptPrinter(JPanel panel) {
          this.panel = panel;
     }

     @Override
     public int print(Graphics g, PageFormat pf, int pageIndex) {
          if (pageIndex > 0) {
               return Printable.NO_SUCH_PAGE;
          }

          Graphics2D g2d = (Graphics2D) g;
          g2d.translate(pf.getImageableX(), pf.getImageableY());

          double scale = calculateScale(pf);
          g2d.scale(scale, scale);

          panel.paint(g2d);
          return Printable.PAGE_EXISTS;
     }

     private double calculateScale(PageFormat pf) {
          double paperWidth = calculatePaperWidth();
          double paperHeight = pf.getImageableHeight();

          double panelWidth = panel.getPreferredSize().getWidth();
          double panelHeight = panel.getPreferredSize().getHeight();

          // Calculate the scaling factor needed to fit the panel width onto the paper
          return paperWidth / panelWidth;
     }

     private double calculatePaperWidth() {
          // Calculate the maximum width required to accommodate the widest item in the JPanel
          double maxWidth = 0;
          for (Component component : panel.getComponents()) {
               double width = component.getPreferredSize().getWidth();
               if (width > maxWidth) {
                    maxWidth = width;
               }
          }
          return maxWidth;
     }

     public void printReceipt() {
          PrinterJob job = PrinterJob.getPrinterJob();
          job.setPrintable(this);

          if (job.printDialog()) {
               try {
                    PageFormat pf = job.defaultPage();
                    Paper paper = new Paper();
                    paper.setSize(calculatePaperWidth(), 5.83 * 72); // A6 paper size with dynamic width
                    pf.setPaper(paper);
                    job.print();
               } catch (PrinterException e) {
                    e.printStackTrace();
               }
          }
     }

}
