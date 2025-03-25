package feature.order_online.view;

import Components.Color.WindowColor;
import Components.JavaAlertMessage;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import feature.order_online.component.DetailOrderOnlineRowData;
import feature.order_online.controller.OrderOnlineViewController;
import feature.order_online.model.OrderOnlineModelResponse;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Response;
import org.json.JSONObject;

@Setter
@Getter
public class OrderOnlineDetail extends javax.swing.JDialog {

     private Integer id;
     private OrderOnlineView view;
     private OrderOnlineModelResponse.OrderOnlineData data;
     private OrderOnlineViewController controller;

     List<String> listOrderStatus = List.of("Accepted", "Picked & Packed", "Out for Delivery", "Delivered", "Pay", "Completed");

     public OrderOnlineDetail(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          custom();
     }

     private void custom() {
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          setBackground(WindowColor.slightGreen);
          setTitle("Order Online Detail");
     }

     public void setData(OrderOnlineModelResponse.OrderOnlineData data, OrderOnlineView view) {
          this.id = data.getId();
          this.view = view;
          this.data = data;
          lbOrderNumber.setLabelName(data.getOrderNumber());
          lbOrderDate.setLabelName(JavaConstant.formateDateDDMMYYYY(data.getOrderDate()));
          lbOrderStatus.setLabelName(data.getOrderStatus());
          lbPaymentMethod.setLabelName(data.getPaymentMethod());
          lbPaymentStatus.setLabelName(data.getPaymentStatus());
          lbCustomerName.setLabelName(data.getCustomerName());
          lbCusomterId.setLabelName(data.getCustomerId());
          lbPhoneNumber.setLabelName(JavaConstant.formatPhoneNumber(data.getPhoneNumber()));
          lbDeliveryAddress.setLabelName(data.getDeliveryAddress());

          if (data.getOrderStatus().equals("Cancelled")) {
               btnReject.setVisible(false);
               buttonSave.setVisible(false);
          } else if (data.getOrderStatus().equals("Completed")) {
               btnReject.setVisible(false);
               btnViewReason.setVisible(false);
               buttonSave.setTitleButton("Paid");
               buttonSave.setBackground(WindowColor.mediumGreen);
          } else {
               btnViewReason.setVisible(false);
          }
 
          for (int i = 0; i < listOrderStatus.size(); i++) {
               
               String item = listOrderStatus.get(i);

               if (data.getOrderStatus().equals("Pay") || data.getOrderStatus().equals("Completed")) {
                    buttonSave.setTitleButton("Paid");
                    buttonSave.setBackground(WindowColor.slightGreen);
               } else {
                    if (data.getOrderStatus().equals(item)) {
                       
                         buttonSave.setTitleButton(listOrderStatus.get(i + 1));
                         break;
                    }
               }
          }

          panelData.removeAll();
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          panelData.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;

          for (int i = 0; i < data.getDetails().size(); i++) {

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

               OrderOnlineModelResponse.OrderOnlineDetailResponse item = data.getDetails().get(i);
               DetailOrderOnlineRowData rowData = new DetailOrderOnlineRowData(item, (i + 1));

               panelData.add(rowData, gbc);
          }

          panelData.revalidate();
          panelData.repaint();

     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          lbProductName = new Components.Label();
          lbProductName1 = new Components.Label();
          lbOrderNumber = new Components.Label();
          lbProductName2 = new Components.Label();
          lbOrderDate = new Components.Label();
          lbPaymentMethod = new Components.Label();
          lbProductName3 = new Components.Label();
          lbOrderStatus = new Components.Label();
          lbProductName4 = new Components.Label();
          lbProductName5 = new Components.Label();
          lbCusomterId = new Components.Label();
          lbProductName6 = new Components.Label();
          lbPhoneNumber = new Components.Label();
          lbProductName7 = new Components.Label();
          lbPaymentStatus = new Components.Label();
          lbProductName8 = new Components.Label();
          lbCustomerName = new Components.Label();
          lbProductName9 = new Components.Label();
          lbEmail = new Components.Label();
          lbProductName10 = new Components.Label();
          lbDeliveryAddress = new Components.Label();
          lbProductName11 = new Components.Label();
          detailOrderOnlineHeader1 = new feature.order_online.component.DetailOrderOnlineHeader();
          btnCancel = new Button.Button();
          btnReject = new Button.Button();
          buttonSave = new ButtonPackage.ButtonSave();
          lbProductName12 = new Components.Label();
          lbProductName13 = new Components.Label();
          lbProductName14 = new Components.Label();
          lbProductName15 = new Components.Label();
          lbProductName16 = new Components.Label();
          lbProductName17 = new Components.Label();
          lbProductName18 = new Components.Label();
          lbProductName19 = new Components.Label();
          lbProductName20 = new Components.Label();
          lbProductName21 = new Components.Label();
          jScrollPane1 = new javax.swing.JScrollPane();
          panelData = new javax.swing.JPanel();
          btnViewReason = new Button.Button();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          lbProductName.setLabelName("Customer Order Information");

          lbProductName1.setLabelName("Order Number");

          lbOrderNumber.setLabelName("");

          lbProductName2.setLabelName("Order Date");

          lbOrderDate.setLabelName("");

          lbPaymentMethod.setLabelName("");

          lbProductName3.setLabelName("Payment Method");

          lbOrderStatus.setLabelName("");

          lbProductName4.setLabelName("Order Status");

          lbProductName5.setLabelName("Phone Number");

          lbCusomterId.setLabelName("");

          lbProductName6.setLabelName("Customer ID");

          lbPhoneNumber.setLabelName("");

          lbProductName7.setLabelName("Payment Status");

          lbPaymentStatus.setLabelName("");

          lbProductName8.setLabelName("Customer Name");

          lbCustomerName.setLabelName("");

          lbProductName9.setLabelName("Email");

          lbEmail.setLabelName("");

          lbProductName10.setLabelName("Delivery Address");

          lbDeliveryAddress.setLabelName("");

          lbProductName11.setLabelName("Product Detail");

          btnCancel.setButtonName("Close");
          btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancelMouseClicked(evt);
               }
          });

          btnReject.setButtonName("Reject");
          btnReject.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnRejectMouseClicked(evt);
               }
          });

          buttonSave.setPreferredSize(new java.awt.Dimension(78, 35));
          buttonSave.setTitleButton("Accepted");
          buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonSaveMouseClicked(evt);
               }
          });

          lbProductName12.setLabelName(":");

          lbProductName13.setLabelName(":");

          lbProductName14.setLabelName(":");

          lbProductName15.setLabelName(":");

          lbProductName16.setLabelName(":");

          lbProductName17.setLabelName(":");

          lbProductName18.setLabelName(":");

          lbProductName19.setLabelName(":");

          lbProductName20.setLabelName(":");

          lbProductName21.setLabelName(":");

          jScrollPane1.setBorder(null);

          javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
          panelData.setLayout(panelDataLayout);
          panelDataLayout.setHorizontalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 1546, Short.MAX_VALUE)
          );
          panelDataLayout.setVerticalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 449, Short.MAX_VALUE)
          );

          jScrollPane1.setViewportView(panelData);

          btnViewReason.setButtonName("View Reaons");
          btnViewReason.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnViewReasonMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(17, 17, 17))
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addGroup(layout.createSequentialGroup()
                              .addGap(20, 20, 20)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addComponent(lbProductName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                             .addGroup(layout.createSequentialGroup()
                                                  .addComponent(lbProductName1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(lbProductName12, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addGap(10, 10, 10)
                                                  .addComponent(lbOrderNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(layout.createSequentialGroup()
                                                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                       .addGroup(layout.createSequentialGroup()
                                                            .addComponent(lbProductName2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(lbProductName13, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                       .addGroup(layout.createSequentialGroup()
                                                            .addComponent(lbProductName4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(lbProductName14, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                       .addGroup(layout.createSequentialGroup()
                                                            .addComponent(lbProductName3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(lbProductName15, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                       .addGroup(layout.createSequentialGroup()
                                                            .addComponent(lbProductName7, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(lbProductName16, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                       .addComponent(lbOrderDate, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addComponent(lbOrderStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addComponent(lbPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                       .addComponent(lbPaymentStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                             .addComponent(lbProductName5, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(lbProductName6, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(lbProductName8, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(lbProductName9, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(lbProductName10, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                  .addComponent(lbProductName19, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                  .addComponent(lbPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                  .addComponent(lbProductName18, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                  .addComponent(lbCusomterId, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                  .addComponent(lbProductName17, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                  .addComponent(lbCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                  .addComponent(lbProductName20, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                  .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                                             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                  .addComponent(lbProductName21, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                  .addComponent(lbDeliveryAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                   .addComponent(lbProductName11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(detailOrderOnlineHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(jScrollPane1)))
                         .addGroup(layout.createSequentialGroup()
                              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(btnViewReason, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(6, 6, 6)
                              .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(btnReject, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(20, 20, 20))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                              .addComponent(buttonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(btnReject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                         .addComponent(btnViewReason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addComponent(lbProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(lbCustomerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(lbProductName8, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                         .addComponent(lbProductName1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                         .addComponent(lbProductName12, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                         .addComponent(lbOrderNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(lbProductName17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbOrderDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbProductName2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(lbProductName13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbOrderStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbProductName4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(lbProductName14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbPaymentMethod, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbProductName3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(lbProductName15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                   .addComponent(lbPaymentStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(lbProductName7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(lbProductName16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                         .addGroup(layout.createSequentialGroup()
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbCusomterId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbProductName6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(lbProductName18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbPhoneNumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbProductName5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(lbProductName19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbProductName9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                   .addComponent(lbProductName20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                   .addComponent(lbDeliveryAddress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                   .addComponent(lbProductName10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                   .addComponent(lbProductName21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGap(10, 10, 10)
                    .addComponent(lbProductName11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(detailOrderOnlineHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
          this.dispose();
          OrderOnlineView orderOnlineView = new OrderOnlineView(new JFrame(), true);
          orderOnlineView.setVisible(true);
     }//GEN-LAST:event_btnCancelMouseClicked

     private void btnRejectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRejectMouseClicked
          OrderReject orderReject = new OrderReject(new JFrame(), true);
          orderReject.setId(id);
          orderReject.setDetail(this);
          orderReject.setVisible(true);
     }//GEN-LAST:event_btnRejectMouseClicked

     private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked

          String btnName = buttonSave.getTitleButton().trim();
          String encodedStatus = URLEncoder.encode(btnName, StandardCharsets.UTF_8);

          if (btnName.equals("Paid")) {
               return;
          }

          Response response = JavaConnection.get(JavaRoute.orderOnline + "/update/orderStatus/" + id + "?orderStatus=" + encodedStatus);

          //System.err.println("respnsefffffff = " + response);
          try {
               if (response.isSuccessful()) {

                    String responseData = response.body().string();

                    JSONObject json = new JSONObject(responseData);

                    if (json.has("error")) {
                         JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
                         j.setMessage("Invalid status");
                         j.setVisible(true);
                    } else {
                         String orderStatus = json.getString("data");
                         lbOrderStatus.setLabelName(orderStatus);

                         view.getPanelData().removeAll();
                         view.getController().init();

                         for (int i = 0; i < listOrderStatus.size(); i++) {
                              String item = listOrderStatus.get(i);

                              if (orderStatus.equals("Completed")) {
                                   buttonSave.setTitleButton("Paid");
                                   buttonSave.setBackground(WindowColor.mediumGreen);
                                   btnReject.setVisible(false);
                                   controller.calculateOrder(); // refresh 
                              } else {
                                   if (orderStatus.equals(item)) {
                                        buttonSave.setTitleButton(listOrderStatus.get(i + 1));
                                        break;
                                   }
                              }
                         }

                    }
               }
          } catch (Exception e) {
               System.err.println("error : " + e);
          }

     }//GEN-LAST:event_buttonSaveMouseClicked

     private void btnViewReasonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewReasonMouseClicked
          JavaAlertMessage j = new JavaAlertMessage(new JFrame(), true);
          j.setMessage(data.getReason());
          j.setVisible(true);
     }//GEN-LAST:event_btnViewReasonMouseClicked

     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    OrderOnlineDetail dialog = new OrderOnlineDetail(new javax.swing.JFrame(), true);
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
     private Button.Button btnCancel;
     private Button.Button btnReject;
     private Button.Button btnViewReason;
     private ButtonPackage.ButtonSave buttonSave;
     private feature.order_online.component.DetailOrderOnlineHeader detailOrderOnlineHeader1;
     private javax.swing.JScrollPane jScrollPane1;
     private Components.Label lbCusomterId;
     private Components.Label lbCustomerName;
     private Components.Label lbDeliveryAddress;
     private Components.Label lbEmail;
     private Components.Label lbOrderDate;
     private Components.Label lbOrderNumber;
     private Components.Label lbOrderStatus;
     private Components.Label lbPaymentMethod;
     private Components.Label lbPaymentStatus;
     private Components.Label lbPhoneNumber;
     private Components.Label lbProductName;
     private Components.Label lbProductName1;
     private Components.Label lbProductName10;
     private Components.Label lbProductName11;
     private Components.Label lbProductName12;
     private Components.Label lbProductName13;
     private Components.Label lbProductName14;
     private Components.Label lbProductName15;
     private Components.Label lbProductName16;
     private Components.Label lbProductName17;
     private Components.Label lbProductName18;
     private Components.Label lbProductName19;
     private Components.Label lbProductName2;
     private Components.Label lbProductName20;
     private Components.Label lbProductName21;
     private Components.Label lbProductName3;
     private Components.Label lbProductName4;
     private Components.Label lbProductName5;
     private Components.Label lbProductName6;
     private Components.Label lbProductName7;
     private Components.Label lbProductName8;
     private Components.Label lbProductName9;
     private javax.swing.JPanel panelData;
     // End of variables declaration//GEN-END:variables
}
