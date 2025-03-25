 
package feature.report.report_product_stock_in.component;
 
import Constant.JavaConstant;
import feature.report.report_product_stock_in.model.ReportProductStockInResponse.ReportProductStockInDetailResponse;

 
public class ReportProductStockInRowData extends javax.swing.JPanel {

     private ReportProductStockInDetailResponse detail;
     private Integer number;
     public ReportProductStockInRowData(ReportProductStockInDetailResponse detail ,Integer number) {
          initComponents();
          this.detail =  detail;
          this.number = number;
          
          setData();
     }
     
     private void setData(){
          lbNO.setText(String.valueOf(number));
          lbProductName.setText(detail.getProductName());
          lbCategoryName.setText(detail.getCategoryName());
          lbSupplierName.setText(detail.getSupplierName());
          lbPrice.setText(detail.getPrice() == null ? "N/A" : JavaConstant.setAmount(detail.getPrice()));
          lbQty.setText(String.valueOf(detail.getQty()));
          lbDate.setText(JavaConstant.formateDateDDMMYYYY(detail.getDate()));
          lbDescription.setText(detail.getDescription());
     }

     
     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        lbNO = new javax.swing.JLabel();
        lbQty = new javax.swing.JLabel();
        lbProductName = new javax.swing.JLabel();
        lbCategoryName = new javax.swing.JLabel();
        lbSupplierName = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        lbDate = new javax.swing.JLabel();
        lbDescription = new javax.swing.JLabel();

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
        lbCategoryName.setText("Cateogory Name");

        lbSupplierName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbSupplierName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbSupplierName.setText("Supplier Name");

        lbPrice.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPrice.setText("Price");

        lbDate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbDate.setText("Date");

        lbDescription.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbDescription.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbDescription.setText("Description");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(lbNO, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbQty, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDate, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbNO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
            .addComponent(lbProductName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbCategoryName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbSupplierName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbQty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbDescription, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1500, Short.MAX_VALUE)
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
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbNO;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbProductName;
    private javax.swing.JLabel lbQty;
    private javax.swing.JLabel lbSupplierName;
    // End of variables declaration//GEN-END:variables
}
