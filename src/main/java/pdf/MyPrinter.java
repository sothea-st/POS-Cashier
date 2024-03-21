/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdf;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.swing.JPanel;


public class MyPrinter implements Printable {

     private JPanel panel;

     public MyPrinter(JPanel panel) {
          this.panel = panel;
     }

     @Override
     public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
          if (pageIndex > 0) {
               return Printable.NO_SUCH_PAGE;
          }

          Graphics2D g2d = (Graphics2D) graphics;
          g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

          double scaleX = pageFormat.getImageableWidth() / panel.getWidth();
          double scaleY = pageFormat.getImageableHeight() / panel.getHeight();
          double scale = Math.min(scaleX, scaleY);

          g2d.scale(scale, scale);

          panel.paint(g2d);

          return Printable.PAGE_EXISTS;
     }

     
}
