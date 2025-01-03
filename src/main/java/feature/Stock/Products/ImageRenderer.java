/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package feature.Stock.Products;

import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author MOBILE-APP.02
 */
public class ImageRenderer extends DefaultTableCellRenderer {

     private JLabel label;
     private String path;

     public ImageRenderer(String path) {
          this.path = path;
          label = new JLabel();
          label.setOpaque(true);
          label.setHorizontalAlignment(SwingConstants.CENTER); // Center align the image
     }

     @Override
     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

          try {
                    File file = new File(path);
                    
                    
                    Image imgs = ImageIO.read(file);
                    ImageIcon icons = new ImageIcon(imgs);
                    label.setIcon(icons);
                    
                    
                    

               // Read the selected image file
               Image img = ImageIO.read(file);

               // Resize the image if needed
               int width = 100; // Example width
               int height = 100; // Example height
               img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

               // Create ImageIcon from the image
               ImageIcon icon = new ImageIcon(img);

               // Update the model with the new ImageIcon
               table.setValueAt(icon, row, 0);

          } catch (IOException ex) {
               Logger.getLogger(ImageRenderer.class.getName()).log(Level.SEVERE, null, ex);
          }

          return label;
     }
}
