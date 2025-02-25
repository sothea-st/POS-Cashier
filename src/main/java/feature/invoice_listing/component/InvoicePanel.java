 
package feature.invoice_listing.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

 
public class InvoicePanel extends javax.swing.JPanel {

  
     public InvoicePanel() {
          initComponents();
//          setData();
     }

     public void setData(int length) {
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          panelItem.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;
          int index = 0;

          for (int i = 0; i < length; i++) {
               GridBagConstraints gbc = new GridBagConstraints();
               gbc.gridx = x;
               gbc.gridy = y;
               gbc.gridwidth = 1;
               gbc.anchor = GridBagConstraints.NORTH;
               x++;
               if (x == 1) {
                    x = 0;
                    y++;
               }
               index++;

               InvoiceRowData data = new InvoiceRowData();
               panelItem.add(data, gbc);
          }

          panelItem.revalidate();
          panelItem.repaint();
     }

 
     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panelItem = new javax.swing.JPanel();
          invoiceListingHeader1 = new feature.invoice_listing.component.InvoiceListingHeader();

          javax.swing.GroupLayout panelItemLayout = new javax.swing.GroupLayout(panelItem);
          panelItem.setLayout(panelItemLayout);
          panelItemLayout.setHorizontalGroup(
               panelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 0, Short.MAX_VALUE)
          );
          panelItemLayout.setVerticalGroup(
               panelItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 300, Short.MAX_VALUE)
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 20, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(panelItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(invoiceListingHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 1831, Short.MAX_VALUE))
                    .addGap(20, 20, 20))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(invoiceListingHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(panelItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE))
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private feature.invoice_listing.component.InvoiceListingHeader invoiceListingHeader1;
     private javax.swing.JPanel panelItem;
     // End of variables declaration//GEN-END:variables
}
