package feature.promotion.controller;

import feature.promotion.view.PromotionCreateView;
import feature.promotion.view.component.PromotionCreateRowData;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class PromotionCreateController {

     private PromotionCreateView view;

     public PromotionCreateController(PromotionCreateView view) {
          this.view = view;
          appendData();
     }

     private void appendData() {

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

          int l = 4;
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

               PromotionCreateRowData rowData = new PromotionCreateRowData();
               if (l > 10) {
                    rowData.setPreferredSize(new Dimension(1353, 45));
               } else {
                    rowData.setPreferredSize(new Dimension(1360, 45));
               }

               //view.getPaginationPanel().setVisible(true);
               panelData.add(rowData, gbc);
          }

          // Refresh UI
          panelData.revalidate();
          panelData.repaint();
     }

}
