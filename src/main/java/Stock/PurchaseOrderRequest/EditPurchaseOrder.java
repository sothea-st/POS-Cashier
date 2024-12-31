package Stock.PurchaseOrderRequest;

import BlogCode.JavaBlogImage;
import Color.WindowColor;
import Components.NotFound;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.PurchaseOrder.DetailPurchaseModel;
import Model.PurchaseOrder.DetailPurchaseModelFirst;
import Model.PurchaseOrder.DetailPurchaseModelSecond;
import Model.PurchaseOrder.DetailPurchaseModelThird;
import Stock.PurchaseOrderCheck.POCheckDetailsModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.Border;
import okhttp3.Response;
import org.json.JSONObject;

public class EditPurchaseOrder extends javax.swing.JDialog {

     private int totalQty = 0;
     private double totalCost = 0;
     private String vendorId;
     private Integer _Id;
     ArrayList<ImportRequestSecond.ImportDetailRequestSecond> details = new ArrayList<>();
     private POCheckDetailsModel detailData;
     private JPanel listGetOrder;

     public EditPurchaseOrder(java.awt.Frame parent, boolean modal, Integer id) {
          super(parent, modal);
          initComponents();

          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          header.setBackground(WindowColor.darkGreen);
          orderDate.setEnabled(false);
          // getListDetailPurchase(listGetDetailOrder, id);
          jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          JScrollBar verticalScrollBar = jScrollPane1.getVerticalScrollBar();
          verticalScrollBar.setUnitIncrement(30);
          verticalScrollBar.setBlockIncrement(35);
          jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          _Id = id;
          Border topBorder = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK);
          borderUnderLine.setBorder(topBorder);

     }

     public void getListDetailPurchase(JPanel jpanelData, Integer id) {
          try {

               Response response = JavaConnection.get(JavaRoute.imports + "/" + id);
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    DetailPurchaseModelFirst data = objMap.readValue(responseData, DetailPurchaseModelFirst.class);
                    DetailPurchaseModelSecond listDataOne = data.getData();
                    DetailPurchaseModelThird[] listDetail = listDataOne.getDetails();
                    assignPurchaseDetail(listDetail, jpanelData, id);
               } else {
                    System.err.println("fail loading purchase");
               }
          } catch (Exception e) {
               System.err.println("error getting purchase " + e);
          }
     }

     public void assignPurchaseDetail(DetailPurchaseModelThird[] listData, JPanel listGetDetailOrder, Integer id) {
//          ArrayList<DetailPurchaseModel> purchase = new ArrayList<>();
//
//          for (int i = 0; i < listData.length; i++) {
//               var obj = listData[i];
//               DetailPurchaseModel getPurchase = new DetailPurchaseModel(
//                    obj.getId(),
//                    obj.getProductId(),
//                    obj.getBarcode(),
//                    obj.getProNameEn(),
//                    obj.getProNameKh(),
//                    obj.getDivision(),
//                    obj.getDepartment(),
//                    obj.getCategory(),
//                    obj.getSubCategory(),
//                    obj.getAvailableQty(),
//                    obj.getOrderQty(),
//                    obj.getCost(),
//                    obj.getTotalCost()
//               );
//               purchase.add(getPurchase);
//          }
//
//          appendPurchaeOrderDetail(purchase, listGetDetailOrder);
     }

     void appendPurchaeOrderDetail(ArrayList<DetailPurchaseModel> listPurchase, JPanel listGetDetailOrder) {
          reloadPanel();
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          listGetDetailOrder.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;
          int index = 0;
          if (listPurchase.size() > 0) {
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
                    TdDetailPurchaseOrder detail = new TdDetailPurchaseOrder();
                    detail.setDetailSecond(
                         String.valueOf(index),
                         String.valueOf(listData.getBarcode()),
                         String.valueOf(listData.getProNameEn()),
                         String.valueOf(listData.getSubCategory()),
                         String.valueOf(listData.getAvailableQty()),
                         String.valueOf(listData.getOrderQty()),
                         JavaConstant.setAmount(BigDecimal.valueOf(listData.getCost())),
                         JavaConstant.setAmount(BigDecimal.valueOf(listData.getTotalCost())),
                         String.valueOf(listData.getId()),
                         String.valueOf(listData.getProductId())
                    );

                    totalQty += listData.getOrderQty();
                    totalCost += listData.getCost().doubleValue();

                    ButtonEvent event = new ButtonEvent() {
                         @Override
                         public void onRemove(String index) {
                              eventRemove(index);
                         }

                         @Override
                         public void onKeyPress() {
                              calculate();
                         }
                    };

                    detail.initEvent(event);

                    listGetDetailOrder.add(detail, gbc);
               }
          } else {
               NotFound nofound = new NotFound();
               listGetDetailOrder.add(nofound);
          }

          lbTotalQty.setText(String.valueOf(totalQty));
          lbTotalCost.setText(JavaConstant.setAmount(BigDecimal.valueOf(totalCost)));

          listGetDetailOrder.revalidate();
          listGetDetailOrder.repaint();
     }

     private void reloadPanel() {
          listGetDetailOrder.removeAll();
          listGetDetailOrder.revalidate();
          listGetDetailOrder.repaint();
     }

     private void calculate() {
          Component[] listCom = listGetDetailOrder.getComponents();
          totalCost = 0;
          totalQty = 0;
          for (Component c : listCom) {
               var objData = ((TdDetailPurchaseOrder) c);
               String _amount = objData.getAmountValue().replace("$", "");
               _amount = _amount.replace(",", "");
               totalCost += Double.parseDouble(_amount);
               Integer _qty = Integer.valueOf(objData.getQtyUnit());
               totalQty += _qty;
          }
          lbTotalCost.setText(JavaConstant.setAmount(BigDecimal.valueOf(totalCost)));
          lbTotalQty.setText(String.valueOf(totalQty));

          for (int i = 0; i < listCom.length; i++) {
               var obj = ((TdDetailPurchaseOrder) listCom[i]);
               String ind = String.valueOf(i + 1);
               obj.setIndex(ind);
          }
     }

     private void eventRemove(String index) {
          try {
               UIManager UI = new UIManager();
               UI.put("OptionPane.background", WindowColor.mediumGreen);
               UI.put("Panel.background", WindowColor.mediumGreen);
               UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

               int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this ?",
                    "Delete?", JOptionPane.YES_NO_OPTION);

               if (resp == JOptionPane.YES_OPTION) {
                    int ind = Integer.parseInt(index) - 1;
                    Component[] listComponent = listGetDetailOrder.getComponents();
                    for (int i = 0; i < listComponent.length; i++) {
                         if (ind == i) {
                              listGetDetailOrder.remove(ind);
                              break;
                         }
                    }
                    calculate();
               } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
               }

          } catch (Exception e) {
               System.err.println("error getting product " + e);
          }
     }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        labelPopUpTitle2 = new Components.LabelPopUpTitle();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jLabel10 = new javax.swing.JLabel();
        lbTotalQty = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbTotalCost = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listGetDetailOrder = new javax.swing.JPanel();
        borderUnderLine = new javax.swing.JLabel();
        txtVendorName = new FormComponent.JavaTextField();
        txtTransactionNo = new FormComponent.JavaTextField();
        txtReference = new FormComponent.JavaTextField();
        txtPurchaseOrderNo = new FormComponent.JavaTextField();
        orderDate = new FormComponent.JavaTextField();
        transactionDate = new FormComponent.JavaTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPopUpTitle2.setLabelTitle("Edit Purchase Request");

        buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancelMouseClicked(evt);
            }
        });

        buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSaveMouseClicked(evt);
            }
        });

        header.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("#");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Barcode");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Sub Category");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Product Name");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Cost");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Amount");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Available QTY");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Qty");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Action");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jScrollPane.setBorder(null);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setText("Total Qty  :");

        lbTotalQty.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTotalQty.setText("0");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel15.setText("Total Cost :");

        lbTotalCost.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lbTotalCost.setText("0");

        jScrollPane1.setBorder(null);

        javax.swing.GroupLayout listGetDetailOrderLayout = new javax.swing.GroupLayout(listGetDetailOrder);
        listGetDetailOrder.setLayout(listGetDetailOrderLayout);
        listGetDetailOrderLayout.setHorizontalGroup(
            listGetDetailOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1175, Short.MAX_VALUE)
        );
        listGetDetailOrderLayout.setVerticalGroup(
            listGetDetailOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(listGetDetailOrder);

        txtVendorName.setLabelName("Vendor");
        txtVendorName.setPlaceHolder("Vendor");

        txtTransactionNo.setLabelName("Transaction № ");
        txtTransactionNo.setName(""); // NOI18N
        txtTransactionNo.setPlaceHolder("Transaction №");

        txtReference.setLabelName("Reference №");
        txtReference.setPlaceHolder("Reference №");

        txtPurchaseOrderNo.setLabelName("Purchase Order №");
        txtPurchaseOrderNo.setPlaceHolder("Purchase Order №");

        orderDate.setLabelName("Order Date");

        transactionDate.setLabelName("Transaction Date");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbTotalCost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbTotalQty, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(borderUnderLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(header, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTransactionNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(137, 137, 137)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtReference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPurchaseOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(orderDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(transactionDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(labelPopUpTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtReference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTransactionNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPurchaseOrderNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transactionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(borderUnderLine, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lbTotalQty))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(lbTotalCost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
         this.dispose();
         PurchaseOrder purchase = new PurchaseOrder(new JFrame(), true);
         purchase.setVisible(true);
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked
         String orderDateValue = orderDate.getValueTextField();
         String referenceNo = txtReference.getValueTextField();
         String transactionDateValue = transactionDate.getValueTextField();

         JSONObject json = new JSONObject();
         json.put("createBy", JavaConstant.cashierId);
         json.put("empId", JavaConstant.empId);
         json.put("vendorId", vendorId);
         json.put("impDate", orderDateValue);
         json.put("discount", "0");
         json.put("referenceNo", referenceNo);
         json.put("transactionDate", transactionDateValue);
         String _totalCost = lbTotalCost.getText().replace("$", "");
         _totalCost = _totalCost.replace(",", "");
         json.put("total", _totalCost);
         json.put("totalQty", lbTotalQty.getText());
         json.put("remark", "requested");

         Component[] listCom = listGetDetailOrder.getComponents();

         for (Component p : listCom) {
              var data = ((TdDetailPurchaseOrder) p);
              ImportRequestSecond importRequest = new ImportRequestSecond();
              ImportRequestSecond.ImportDetailRequestSecond imps = importRequest.new ImportDetailRequestSecond(
                   data.getId(),
                   data.getProductId(),
                   Integer.valueOf(data.getQtyUnit()),
                   BigDecimal.valueOf(Double.parseDouble(data.getCost())),
                   BigDecimal.valueOf(Double.parseDouble(data.getAmount())),
                   "");

              details.add(imps);
         }

         json.put("details", details);

         Response response = JavaConnection.put(JavaRoute.imports + "/" + _Id, json);

         JavaConstant.setCircleLoadingCursor(this);
         try {
              if (response.isSuccessful()) {
                   dispose();
                   JavaConstant.restoreDefaultCursor(this);
                   PurchaseOrder purchase = new PurchaseOrder(new JFrame(), true);
                   purchase.setVisible(true);
                   listGetOrder.removeAll();
                   listGetOrder.revalidate();
                   listGetOrder.repaint();
                   purchase.getListPurchase(listGetOrder, true);

              }
         } catch (Exception e) {
              System.out.println("import request fails : " + e);
         }
    }//GEN-LAST:event_buttonSaveMouseClicked

     public void setValue(
          String vendorNameValue,
          String referenceNoValue,
          String transactionNoValue,
          String purchaseOrdernoValue,
          String totalQty,
          String totalCost,
          String _vendorId,
          String _orderDate,
          String _transactionDate,
          String _remark
     ) {
          txtVendorName.setText(vendorNameValue);
          txtVendorName.setDisable();
          txtReference.setText(referenceNoValue);
          txtReference.setDisable();
          txtTransactionNo.setText(transactionNoValue);
          txtTransactionNo.setDisable();
          txtPurchaseOrderNo.setText(purchaseOrdernoValue);
          txtPurchaseOrderNo.setDisable();
          lbTotalQty.setText(totalQty);
          lbTotalCost.setText("$ " + totalCost);
          vendorId = _vendorId;
          orderDate.setText(_orderDate);
          orderDate.setDisable();
          transactionDate.setText(_transactionDate);
          transactionDate.setDisable();

          if (_remark.equals("requested")) {
               buttonSave.setVisible(true);
          } else {
               buttonSave.setVisible(false);
          }

     }

     public JPanel getListGetOrder() {
          return listGetOrder;
     }

     public void setListGetOrder(JPanel listGetOrder) {
          this.listGetOrder = listGetOrder;
     }

     public POCheckDetailsModel getDetailData() {
          return detailData;
     }

     public void setDetailData(POCheckDetailsModel detailData) {
          this.detailData = detailData;
          ArrayList<DetailPurchaseModel> purchase = new ArrayList<>();
          for (int i = 0; i < detailData.getDetails().length; i++) {
               var obj = detailData.getDetails()[i];

               DetailPurchaseModel getPurchase = new DetailPurchaseModel(
                    obj.getID(),
                    obj.getProductID(),
                    obj.getBarcode(),
                    obj.getProNameEn(),
                    obj.getProNameKh(),
                    obj.getDivision(),
                    obj.getDepartment(),
                    obj.getCategory(),
                    obj.getSubCategory(),
                    obj.getAvailableQty(),
                    obj.getOrderQty(),
                    Double.valueOf(String.valueOf(obj.getCost())),
                    Double.valueOf(String.valueOf(obj.getTotalCost()))
               );
               purchase.add(getPurchase);
          }
          appendPurchaeOrderDetail(purchase, listGetDetailOrder);

     }

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
               java.util.logging.Logger.getLogger(EditPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(EditPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(EditPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(EditPurchaseOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    EditPurchaseOrder dialog = new EditPurchaseOrder(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JLabel borderUnderLine;
    private ButtonPackage.ButtonCancel buttonCancel;
    private ButtonPackage.ButtonSave buttonSave;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private Components.LabelPopUpTitle labelPopUpTitle2;
    private javax.swing.JLabel lbTotalCost;
    private javax.swing.JLabel lbTotalQty;
    private javax.swing.JPanel listGetDetailOrder;
    private FormComponent.JavaTextField orderDate;
    private FormComponent.JavaTextField transactionDate;
    private FormComponent.JavaTextField txtPurchaseOrderNo;
    private FormComponent.JavaTextField txtReference;
    private FormComponent.JavaTextField txtTransactionNo;
    private FormComponent.JavaTextField txtVendorName;
    // End of variables declaration//GEN-END:variables
}
