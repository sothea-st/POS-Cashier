package feature.adjustment.adjustmetn_detail;

import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.adjustment.adjustmetn_detail.model.AdjustmentDetailResponse;
import feature.adjustment.adjustmetn_detail.model.AdjustmentDetailResponse.AdjustmentData;
import feature.adjustment.component.ItemFormCreate;
import feature.adjustment.model.ProductAdjustment;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.math.BigDecimal;
import javax.swing.JPanel;
import okhttp3.Response;

public class AdjustmentDetailController {
     
     private AdjustmentDetail main;
     
     public AdjustmentDetailController(AdjustmentDetail main) {
          this.main = main;
          
     }
     
     public void readById(Integer adjustmentId) {
          
          Response response = JavaConnection.get(JavaRoute.adjustment + "/" + adjustmentId);
          
          try {
               
               String responseData = response.body().string();
               
               ObjectMapper objMapper = new ObjectMapper();

               // convert responeData to objMapper
               AdjustmentDetailResponse model = objMapper.readValue(responseData, AdjustmentDetailResponse.class);
               
               appendData(model);
               
          } catch (Exception e) {
               System.err.println("error read adjustment detail : " + e);
          }
     }
     
     private void appendData(AdjustmentDetailResponse model) {
          AdjustmentData data = model.getData();
          main.getObjTransactionDate().setData(JavaConstant.formateDateDDMMYYYY(data.getTransactionDate()));
          main.getObjReason().setData(data.getReason().getName());
          main.getObjTransaction().setData(data.getTransaction());
          main.getObjReference().setData(data.getReference());
          main.getObjComment().setData(data.getComment());
          
          JPanel panelData = main.getPanelData();
          
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          
          panelData.setLayout(gridBagLayout);
          
          AdjustmentDetailResponse.Detail[] listData = data.getDetails();
          
          int x = 0;
          int y = 0;
          int totalQty = 0;
          Double totalCost = 0.00;
          
          for (int i = 0; i < listData.length; i++) {
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
               
               var product = listData[i];
               
               ProductAdjustment productAdjustment = ProductAdjustment.builder()
                    .itemCode(product.getItemCode())
                    .barcode(product.getBarcode())
                    .proNameEn(product.getProductNameEn())
                    .proNameKh(product.getProductNameKh())
                    .uom(product.getOum())
                    .onHandQty(product.getOnHandQty())
                    .adjustQty(product.getAdjustQty())
                    .cost(product.getCost())
                    .build();
               
               ItemFormCreate item = new ItemFormCreate(productAdjustment, i);
               item.hideDelete();
               item.setDisable();
               item.setPreferredSize(new Dimension(1418, 45));
               panelData.add(item, gbc);

               // calculate
               totalQty += product.getAdjustQty();
               
               Double amount = product.getAdjustQty() * product.getCost().doubleValue();
               totalCost += amount;
          }
          
          panelData.revalidate();
          panelData.repaint();

          // reload total qty and cost
          main.getBoxTotal().getTxtTotalQty().setText(String.valueOf(totalQty));
          main.getBoxTotal().getTxtTotalAmount().setText(JavaConstant.setAmount(BigDecimal.valueOf(totalCost)));
          
     }
     
}
