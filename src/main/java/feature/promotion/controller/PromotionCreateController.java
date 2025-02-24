package feature.promotion.controller;

import Components.Color.WindowColor;
import Components.Event.ButtonEvent;
import Components.Fonts.WindowFonts;
import Constant.JavaConstant;
import feature.promotion.model.ProductPromotionResponse.ProductPromotionResponseDetail;
import feature.promotion.view.PromotionCreateView;
import feature.promotion.view.component.PromotionCreateRowData;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PromotionCreateController {

     private PromotionCreateView view;
     private List<ProductPromotionResponseDetail> detailDescriptions = new ArrayList<>();

     public PromotionCreateController(PromotionCreateView view) {

          this.view = view;

          eventObjPercentage();

     }

     private void eventObjPercentage() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyRelease() {
                    calculateBoxTotal();
               }
          };

          view.getObjPercentage().initEvent(event);
     }

     private void calculateBoxTotal() {

          String value = view.getObjPercentage().getValueTextField();
          Integer percentage = 0;
          if (value != null && !value.isEmpty()) {
               percentage = Integer.valueOf(value);
          }

          JPanel panelData = view.getPanelData();

          Double totalSalePrice = 0.00;
          Double totalAfterDiscount = 0.00;

          for (Component com : panelData.getComponents()) {
               if (com instanceof PromotionCreateRowData rowData) {
                    Double salePrice = JavaConstant.getReplace(rowData.getLbSalePrice().getText());
                    Double afterDiscount = salePrice - (salePrice * percentage) / 100;

                    totalSalePrice += salePrice;
                    totalAfterDiscount += afterDiscount;

                    rowData.getLbPercentage().setText("%".concat(String.valueOf(percentage)));
                    rowData.getLbAfterDiscount().setText(JavaConstant.setAmount(BigDecimal.valueOf(afterDiscount)));

               }
          }

          view.getBoxTotal().getTxtTotalSalePrice().setText(JavaConstant.setAmount(BigDecimal.valueOf(totalSalePrice)));
          view.getBoxTotal().getTxtTotalAfterDiscount().setText(JavaConstant.setAmount(BigDecimal.valueOf(totalAfterDiscount)));

     }

     public void read() {
         
          JPanel panelData = view.getPanelData();

          panelData.removeAll();

          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          panelData.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;

          int l = detailDescriptions.size();

          for (int i = 0; i < l; i++) {

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

               ProductPromotionResponseDetail detail = detailDescriptions.get(i);

               PromotionCreateRowData rowData = new PromotionCreateRowData(detail, i + 1);

               ButtonEvent event = new ButtonEvent() {
                    @Override
                    public void onMouseClick() {
                         delete(detail.getBarcode());
                    }
               };
               rowData.initEvent(event);

               rowData.setPreferredSize(new Dimension(l > 10 ? 1353 : 1360, 45));

               panelData.add(rowData, gbc);
          }

          // Refresh UI
          panelData.revalidate();
          panelData.repaint();

          // calculate
          calculateBoxTotal();
     }

     public void delete(String barcode) {
          UIManager UI = new UIManager();
          UI.put("OptionPane.background", WindowColor.mediumGreen);
          UI.put("Panel.background", WindowColor.mediumGreen);
          UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

          int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to Delete?",
               "Delete", JOptionPane.YES_NO_OPTION);

          if (resp == JOptionPane.YES_OPTION) {
               for (Component com : view.getPanelData().getComponents()) {
                    if (com instanceof PromotionCreateRowData rowData) {
                         if (rowData.getLbBarcode().getText().equals(barcode)) {
                              view.getPanelData().remove(rowData);
                              view.getPanelData().revalidate();
                              view.getPanelData().repaint();
                              // calculate
                              calculateBoxTotal();
                              break;
                         }
                    }
               }
          }
     }
}
