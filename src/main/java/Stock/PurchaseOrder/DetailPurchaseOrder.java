package Stock.PurchaseOrder;

import Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Model.PurchaseOrder.DetailPurchaseModel;
import Model.PurchaseOrder.DetailPurchaseModelFirst;
import Model.PurchaseOrder.DetailPurchaseModelSecond;
import Model.PurchaseOrder.DetailPurchaseModelThird;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import okhttp3.Response;

public class DetailPurchaseOrder extends javax.swing.JDialog {

    private String vendorName;
    private String transacionNo;
    private String purchaseOrderNo;
    private String referenceNo;
    private String transactionDate;
    private String orderDate;
    private String totalQty;
    private String totalCost;
    
    public DetailPurchaseOrder(java.awt.Frame parent, boolean modal, Integer Id) {
        super(parent, modal);
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        header1.setBackground(WindowColor.darkGreen);
        getListDetailPurchase(listGetDetailOrder, Id);
        jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(30);
        verticalScrollBar.setBlockIncrement(35);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        System.out.println("Id : " + Id);
    }
    
    public void getListDetailPurchase(JPanel jpanelData, Integer Id) {
        try {

            Response response = JavaConnection.get(JavaRoute.imports + "/" + Id );
            
            System.out.println("response : " + response);
            
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                ObjectMapper objMap = new ObjectMapper();
                DetailPurchaseModelFirst data = objMap.readValue(responseData, DetailPurchaseModelFirst.class);
                DetailPurchaseModelSecond listDataOne = data.getData();
                DetailPurchaseModelThird[] listDetail = listDataOne.getDetails();
                assignPurchaseDetail(listDetail, jpanelData);
            } else {
                System.err.println("fail loading purchase");
            }
        } catch (Exception e) {
            System.err.println("error getting purchase " + e);
        }
    }
    
    
    public void assignPurchaseDetail(DetailPurchaseModelThird[] listData, JPanel listGetDetailOrder) {
        ArrayList<DetailPurchaseModel> purchase = new ArrayList<>();
          
        for (int i = 0; i < listData.length; i++) {
            var obj = listData[i];
            DetailPurchaseModel getPurchase = new DetailPurchaseModel(
                    obj.getBarcode(),
                    obj.getProNameEn(),
                    obj.getProNameKh(),
                    obj.getDivision(),
                    obj.getDepartment(),
                    obj.getCategory(),
                    obj.getSubCategory(),
                    obj.getAvailableQty(),
                    obj.getOrderQty(),
                    obj.getCost(),
                    obj.getTotalCost()
            );
            purchase.add(getPurchase);
        }

        appendPurchaeOrderDetail(purchase, listGetDetailOrder);
    }

    void appendPurchaeOrderDetail(ArrayList<DetailPurchaseModel> listPurchase, JPanel listGetDetailOrder) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
        gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        listGetDetailOrder.setLayout(gridBagLayout);

        int x = 0;
        int y = 0;
        int index = 0;
        if(listPurchase.size() > 0){
            for (int i = 0; i < listPurchase.size(); i++) {
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = x;
                gbc.gridy = y;
                gbc.gridwidth = 1;
                gbc.anchor = gbc.NORTH;
                x++;
                if (x == 1) {
                    x = 0;
                    y++;
                }
                var listData = listPurchase.get(i);
                index++;
                GetDetailPurchase b = new GetDetailPurchase();

                b.setValue(
                    String.valueOf(index),
                    String.valueOf(listData.getBarcode()),
                    String.valueOf(listData.getProNameEn()),
                    String.valueOf(listData.getDivision()),
                    String.valueOf(listData.getDepartment()),
                    String.valueOf(listData.getCategory()),
                    String.valueOf(listData.getSubCategory()),
                    String.valueOf(listData.getAvailableQty()),
                    String.valueOf(listData.getOrderQty()),
                    String.valueOf(listData.getCost()),
                    String.valueOf(listData.getTotalCost())
               );
                
                listGetDetailOrder.add(b, gbc);
            }  
        }else{
            PurchaseNoData no = new PurchaseNoData();
            listGetDetailOrder.add(no);
        }
        
        listGetDetailOrder.revalidate();
        listGetDetailOrder.repaint();
    }
    
    
    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
        lbPosId8.setLabelName( vendorName);
    }

    public String getTransacionNo() {
        return transacionNo;
    }

    public void setTransacionNo(String transacionNo) {
        this.transacionNo = transacionNo;
        lbPosId11.setLabelName(transacionNo);
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
        lbPosId10.setLabelName( purchaseOrderNo);
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
        lbPosId9.setLabelName(referenceNo);
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
        lbPosId12.setLabelName(transactionDate);
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
        lbPosId15.setLabelName(orderDate);
    }

    public String getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(String totalQty) {
        this.totalQty = totalQty;
        lbPosId14.setLabelName(totalQty);
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
        lbPosId13.setLabelName("$ " + totalCost);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPopUpTitle2 = new Components.LabelPopUpTitle();
        lbTransactionNo = new Components.Label();
        lbVendorName = new Components.Label();
        lbTransactionDate = new Components.Label();
        lbOrderDate = new Components.Label();
        lbPurchaseeOrderNo = new Components.Label();
        lbReferenceNo = new Components.Label();
        lbTotalQty = new Components.Label();
        lbTotalCost = new Components.Label();
        lbPosId8 = new Components.Label();
        lbPosId9 = new Components.Label();
        lbPosId10 = new Components.Label();
        lbPosId11 = new Components.Label();
        lbPosId12 = new Components.Label();
        lbPosId13 = new Components.Label();
        lbPosId14 = new Components.Label();
        lbPosId15 = new Components.Label();
        header1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listGetDetailOrder = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle2.setLabelTitle("Detail Purchase Order");

        lbTransactionNo.setLabelName("Transaction № :");

        lbVendorName.setLabelName("Vendor Name :");

        lbTransactionDate.setLabelName("Transaction Date :");

        lbOrderDate.setLabelName("Order Date :");

        lbPurchaseeOrderNo.setLabelName("Purchase Order № :");

        lbReferenceNo.setLabelName("Reference № :");

        lbTotalQty.setLabelName("Total Qty :");

        lbTotalCost.setLabelName("Total Cost :");

        lbPosId8.setLabelName("");

        lbPosId9.setLabelName("");

        lbPosId10.setLabelName("");

        lbPosId11.setLabelName("");

        lbPosId12.setLabelName("");

        lbPosId13.setLabelName("");

        lbPosId14.setLabelName("");

        lbPosId15.setLabelName("");

        header1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("#");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Product Name");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Division Name");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Barcode");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Category Name");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Available Qty");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Cost");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Total Cost");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Order Qty");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Sub Category Name");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Department Name");

        javax.swing.GroupLayout header1Layout = new javax.swing.GroupLayout(header1);
        header1.setLayout(header1Layout);
        header1Layout.setHorizontalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
        );
        header1Layout.setVerticalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(176, 215, 181));
        jScrollPane1.setBorder(null);

        listGetDetailOrder.setBackground(new java.awt.Color(176, 215, 181));

        javax.swing.GroupLayout listGetDetailOrderLayout = new javax.swing.GroupLayout(listGetDetailOrder);
        listGetDetailOrder.setLayout(listGetDetailOrderLayout);
        listGetDetailOrderLayout.setHorizontalGroup(
            listGetDetailOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        listGetDetailOrderLayout.setVerticalGroup(
            listGetDetailOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(listGetDetailOrder);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbReferenceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbPurchaseeOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTransactionNo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbPosId8, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                            .addComponent(lbPosId11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbPosId10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbPosId9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbTotalCost, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbPosId13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbTransactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbPosId12, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbOrderDate, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbPosId15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbPosId14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jScrollPane1)
                    .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbPosId8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbVendorName, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                            .addComponent(lbPosId12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbTransactionNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbPosId11, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addComponent(lbPosId15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTransactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbOrderDate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbPosId14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPurchaseeOrderNo, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(lbTotalQty, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(lbPosId10, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbPosId13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbReferenceNo, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(lbTotalCost, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(lbPosId9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DetailPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetailPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetailPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetailPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DetailPurchaseOrder dialog = new DetailPurchaseOrder(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel header;
    private javax.swing.JPanel header1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private Components.LabelPopUpTitle labelPopUpTitle2;
    private Components.Label lbOrderDate;
    private Components.Label lbPosId10;
    private Components.Label lbPosId11;
    private Components.Label lbPosId12;
    private Components.Label lbPosId13;
    private Components.Label lbPosId14;
    private Components.Label lbPosId15;
    private Components.Label lbPosId8;
    private Components.Label lbPosId9;
    private Components.Label lbPurchaseeOrderNo;
    private Components.Label lbReferenceNo;
    private Components.Label lbTotalCost;
    private Components.Label lbTotalQty;
    private Components.Label lbTransactionDate;
    private Components.Label lbTransactionNo;
    private Components.Label lbVendorName;
    private javax.swing.JPanel listGetDetailOrder;
    // End of variables declaration//GEN-END:variables
}
