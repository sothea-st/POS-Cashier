package feature.report.report_stock.component;

import Constant.JavaConstant;
import feature.report.report_stock.model.StockAvailableResponse.StockAvailableDetail;

public class StockAvailableRowData extends javax.swing.JPanel {

     private StockAvailableDetail detail;
     private Integer numebr;

     public StockAvailableRowData(StockAvailableDetail detail,Integer number) {
          initComponents();

          this.detail = detail;
          this.numebr = number;
          
          setData();
     }
     
     private void setData(){
          lbNO.setText(String.valueOf(numebr));
          lbProductName.setText(detail.getProductName());
          lbCategoryName.setText(" "+detail.getCategoryName());
          lbUnitCost.setText(" "+JavaConstant.setAmount(detail.getCost()));
          lbUnitPrice.setText(" "+JavaConstant.setAmount(detail.getPrice()));
          lbQty.setText(" "+String.valueOf(detail.getQty()));
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        lbNO = new javax.swing.JLabel();
        lbQty = new javax.swing.JLabel();
        lbProductName = new javax.swing.JLabel();
        lbCategoryName = new javax.swing.JLabel();
        lbUnitCost = new javax.swing.JLabel();
        lbUnitPrice = new javax.swing.JLabel();

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        header.setPreferredSize(new java.awt.Dimension(1487, 45));

        lbNO.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbNO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNO.setText("No.");

        lbQty.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbQty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbQty.setText("Qty");

        lbProductName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbProductName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbProductName.setText("Product Name");

        lbCategoryName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbCategoryName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCategoryName.setText("Category Name");

        lbUnitCost.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbUnitCost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUnitCost.setText("Unit Cost");

        lbUnitPrice.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbUnitPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUnitPrice.setText("Unit Price");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(lbNO, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbProductName, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(lbCategoryName, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbUnitCost, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbUnitPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbQty, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbNO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbProductName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbUnitCost, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUnitPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1518, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel header;
    private javax.swing.JLabel lbCategoryName;
    private javax.swing.JLabel lbNO;
    private javax.swing.JLabel lbProductName;
    private javax.swing.JLabel lbQty;
    private javax.swing.JLabel lbUnitCost;
    private javax.swing.JLabel lbUnitPrice;
    // End of variables declaration//GEN-END:variables
}
