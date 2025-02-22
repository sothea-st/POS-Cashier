package feature.promotion.view;

import Components.Color.WindowColor;
import Components.Event.ButtonEvent;
import Constant.JavaConstant;
import feature.promotion.controller.PromotionCreateController;
import feature.promotion.view.component.dialog_category.DialogCategory;
import feature.promotion.view.component.dialog_description.DialogDescription;
import java.util.LinkedHashMap;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PromotionCreateView extends javax.swing.JDialog {

     public PromotionCreateView(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();

          custom();

          // call cmdPromotionType
          cmdPromotionType();

          new PromotionCreateController(this);

          categoryEvent();
     }

     private void custom() {
          objCategory.setFocusable();
          objEngDesc.setDiable();
          JavaConstant.setPointer(objCategory);
          JavaConstant.setPointer(objEngDesc);
          objPercentage.setValidateAmount();

          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);
          panelData.setBackground(WindowColor.mediumGreen);
          JavaConstant.setScroll(jScrollPane);

          objPercentage.setFocus();
     }

     private void cmdPromotionType() {

          LinkedHashMap<String, String> map = new LinkedHashMap<>();
          map.put("Percentage", "Percentage");

          objPromotionType.setMapWithNoPlaceHolder(map);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          mainPanel = new javax.swing.JPanel();
          panel = new javax.swing.JPanel();
          objEngDesc = new FormComponent.combobox.JavaCombobox();
          objPromotionType = new FormComponent.combobox.JavaCombobox();
          objCategory = new FormComponent.combobox.JavaCombobox();
          objStartDate = new FormComponent.datepicker.JavaDatePicker();
          objEndDate = new FormComponent.datepicker.JavaDatePicker();
          objPercentage = new FormComponent.JavaTextField();
          btnCancel = new Button.Button();
          buttonSave1 = new ButtonPackage.ButtonSave();
          panelDetail = new javax.swing.JPanel();
          promotionCreateHeader1 = new feature.promotion.view.component.PromotionCreateHeader();
          jScrollPane = new javax.swing.JScrollPane();
          panelData = new javax.swing.JPanel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          objEngDesc.setLabelName("English Description");

          objPromotionType.setLabelName("Promotion Type *");

          objCategory.setLabelName("Category Name");

          objStartDate.setLabelName("Start Date *");

          objEndDate.setLabelName("End Date *");

          objPercentage.setLabelName("Percentage *");
          objPercentage.setPlaceHolder("0%");

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objPromotionType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(objCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(objEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                         .addComponent(objEngDesc, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                         .addComponent(objPercentage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(objStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(20, 20, 20))
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objEngDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(objPromotionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(objEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(objStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(10, Short.MAX_VALUE))
          );

          btnCancel.setButtonName("Close");
          btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCancelMouseClicked(evt);
               }
          });

          buttonSave1.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    buttonSave1MouseClicked(evt);
               }
          });

          jScrollPane.setBorder(null);

          javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
          panelData.setLayout(panelDataLayout);
          panelDataLayout.setHorizontalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 1362, Short.MAX_VALUE)
          );
          panelDataLayout.setVerticalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 388, Short.MAX_VALUE)
          );

          jScrollPane.setViewportView(panelData);

          javax.swing.GroupLayout panelDetailLayout = new javax.swing.GroupLayout(panelDetail);
          panelDetail.setLayout(panelDetailLayout);
          panelDetailLayout.setHorizontalGroup(
               panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(promotionCreateHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addComponent(jScrollPane)
          );
          panelDetailLayout.setVerticalGroup(
               panelDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelDetailLayout.createSequentialGroup()
                    .addComponent(promotionCreateHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane))
          );

          javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
          mainPanel.setLayout(mainPanelLayout);
          mainPanelLayout.setHorizontalGroup(
               mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(panelDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addGroup(mainPanelLayout.createSequentialGroup()
                              .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(20, 20, 20))
          );
          mainPanelLayout.setVerticalGroup(
               mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(mainPanelLayout.createSequentialGroup()
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(buttonSave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
          dispose();
     }//GEN-LAST:event_btnCancelMouseClicked

     private void buttonSave1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSave1MouseClicked

     }//GEN-LAST:event_buttonSave1MouseClicked

     private void categoryEvent() {
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                    DialogCategory dialogCategory = new DialogCategory(new JFrame(), true);
                    dialogCategory.setVisible(true);
               }
          };
          objCategory.eventCall(event);

          ButtonEvent eventDesc = new ButtonEvent() {
               @Override
               public void onMouseClick() {
                    DialogDescription dialogDescription = new DialogDescription(new JFrame(), true);
                    dialogDescription.setVisible(true);
               }
          };
          objEngDesc.eventCall(eventDesc);

     }

     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    PromotionCreateView dialog = new PromotionCreateView(new javax.swing.JFrame(), true);
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
     private ButtonPackage.ButtonSave buttonSave1;
     private javax.swing.JScrollPane jScrollPane;
     private javax.swing.JPanel mainPanel;
     private FormComponent.combobox.JavaCombobox objCategory;
     private FormComponent.datepicker.JavaDatePicker objEndDate;
     private FormComponent.combobox.JavaCombobox objEngDesc;
     private FormComponent.JavaTextField objPercentage;
     private FormComponent.combobox.JavaCombobox objPromotionType;
     private FormComponent.datepicker.JavaDatePicker objStartDate;
     private javax.swing.JPanel panel;
     private javax.swing.JPanel panelData;
     private javax.swing.JPanel panelDetail;
     private feature.promotion.view.component.PromotionCreateHeader promotionCreateHeader1;
     // End of variables declaration//GEN-END:variables

}
