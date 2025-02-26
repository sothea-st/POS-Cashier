package feature.invoice_listing.component;

import Components.Event.ButtonEvent;
import Constant.JavaConnection;

import Constant.JavaRoute;
import Model.Report.ReportSaleDetail;
import Model.Report.RepostSaleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.invoice_listing.InvoiceListingView;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Response;
import org.json.JSONObject;

@Setter
@Getter
public class InvoicePanel extends javax.swing.JPanel {

     private String pageNumber = "1";
     private int pageSize = 10;
     private boolean isCheckSearch = true;
     private String searchValue;
     private int dataCount = 0;
     private String pageType;
     private ReportSaleDetail[] saledDetail;
     public ArrayList<ReportSaleDetail> listDetail = new ArrayList<>();

     private String dateFrom;
     private String dateTo;
     
     private InvoiceListingView invoiceListingView;

     public InvoicePanel() {
          initComponents();
          
         
     }
     
     
       private void eventPagination() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick(String value) {
                    if (isCheckSearch) {
                         int _value = Integer.parseInt(value); // value pageNumber star from 0 
                         pageNumber = String.valueOf(_value);
                         read(true);
                    }
               }

               // for pagination
               @Override
               public void onMouseClick(String value, String pType) {
                    pageType = pType;
               }
          };
            invoiceListingView.getPaginationPanel().initEvent(event);
     }

     public void read(boolean isCheck) {
          Response response = JavaConnection.get(JavaRoute.reportSaled + "?pageNumber=" + pageNumber + "&pageSize=" + pageSize
               + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo);

          System.err.println("log view response : " + response);

          try {

               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JSONObject jsonResponse = new JSONObject(responseData);
                    if (jsonResponse.has("error")) {
                         JSONObject error = jsonResponse.getJSONObject("error");
                         String reason = error.getString("reason");
                         JOptionPane.showMessageDialog(null, reason);
                    } else {
                         ObjectMapper objectMapper = new ObjectMapper();
                         RepostSaleResponse data = objectMapper.readValue(responseData, RepostSaleResponse.class);

                         saledDetail = data.getData();

                         // pagination code
                         if (isCheck) {
                              invoiceListingView.getPaginationPanel().setTotalPage(data.getCount(), pageSize);
                         } else {
                              invoiceListingView.getPaginationPanel().resetPage(data.getCount());
                         }

                         listDetail.clear();
                         listDetail.addAll(Arrays.asList(saledDetail));
                         appendData(saledDetail);

                         if (saledDetail.length != 0) {
                              invoiceListingView.getPaginationPanel().setVisible(true);
                         }
                    }

               }

          } catch (Exception e) {
               System.err.println("error get sale report : " + e);
          }

     }

     private void appendData(ReportSaleDetail[] listData) {
          
          panelItem.removeAll();
          
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          panelItem.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;
          int index = 0;

          for (int i = 0; i < listData.length; i++) {
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
               
               ReportSaleDetail detail = listData[i];
               
               InvoiceRowData data = new InvoiceRowData(detail,index);
 
               panelItem.add(data, gbc);
          }

          panelItem.revalidate();
          panelItem.repaint();
          
          // call
          eventPagination();
          
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
               .addGap(0, 450, Short.MAX_VALUE)
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
          this.setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(panelItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(invoiceListingHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, 0))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(invoiceListingHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(panelItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
          );
     }// </editor-fold>//GEN-END:initComponents


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private feature.invoice_listing.component.InvoiceListingHeader invoiceListingHeader1;
     private javax.swing.JPanel panelItem;
     // End of variables declaration//GEN-END:variables
}
