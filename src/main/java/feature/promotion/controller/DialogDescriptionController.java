package feature.promotion.controller;

import Components.Event.ButtonEvent;
import feature.promotion.model.ProductPromotionResponse.ProductPromotionResponseDetail;
import feature.promotion.view.component.dialog_description.DescriptionRowData;
import feature.promotion.view.component.dialog_description.DialogDescription;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class DialogDescriptionController {

     private DialogDescription view;
     private List<ProductPromotionResponseDetail> detailDescriptionsTmp = new ArrayList<>();
 

     public DialogDescriptionController(DialogDescription view) {

          this.view = view;

     }

     public void read(List<ProductPromotionResponseDetail> detailDescriptions) {

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

               DescriptionRowData rowData = new DescriptionRowData(detail);

               rowData.setIsChcek(false);

               ButtonEvent event = new ButtonEvent() {
                    @Override
                    public void onMouseClick() {
                         
                         boolean isCheck = !rowData.getIsChcek();
                         
                         if (isCheck) {
                              detailDescriptionsTmp.add(detail);
                         } else {
                              detailDescriptionsTmp.remove(detail);
                         }
                         rowData.checked(isCheck);
                    }
               };
               rowData.initEvent(event);

               rowData.setPreferredSize(new Dimension(l >= 10 ? 858 : 867, 45));

               panelData.add(rowData, gbc);
          }

          // Refresh UI
          panelData.revalidate();
          panelData.repaint();
     }

     public void select() {
          view.dispose();
          view.getPromotionCreateView().getPromotionController().setDetailDescriptions(detailDescriptionsTmp);
          view.getPromotionCreateView().getPromotionController().read();
          detailDescriptionsTmp.clear();
     }

}
