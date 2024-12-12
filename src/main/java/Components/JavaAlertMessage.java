package Components;

import Color.WindowColor;
import Constant.JavaConstant;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import pdf.PrintPanelToPDF;

public class JavaAlertMessage extends javax.swing.JDialog {

     public static String returnMsg = "You are in processing return !";
     public static int countTimeQty = 0;
     private String message;
     private boolean isShow = false;
     private String pathOpen;

     public String getPathOpen() {
          return pathOpen;
     }

     public void setPathOpen(String pathOpen) {
          this.pathOpen = pathOpen;
     }

     public boolean isIsShow() {
          return isShow;
     }

     public void setIsShow(boolean isShow) {
          this.isShow = isShow;
          btnBrowse.setVisible(isShow);
     }

     public JavaAlertMessage(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          btnClose.setButtonName(JavaConstant.titleClose);
          setResizable(false);
          panelMessage.setBackground(WindowColor.mediumGreen);
          btnBrowse.setVisible(false);
//          lbMessage.setFont(WindowFonts.timeNewRoman12);
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panelMessage = new javax.swing.JPanel();
          btnClose = new Button.Button();
          panelOutSide = new javax.swing.JPanel();
          lbMessage = new javax.swing.JLabel();
          btnBrowse = new Button.Button();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          btnClose.setButtonName("Close");
          btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCloseMouseClicked(evt);
               }
          });

          panelOutSide.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

          lbMessage.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
          lbMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbMessage.setText("Message");

          javax.swing.GroupLayout panelOutSideLayout = new javax.swing.GroupLayout(panelOutSide);
          panelOutSide.setLayout(panelOutSideLayout);
          panelOutSideLayout.setHorizontalGroup(
               panelOutSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelOutSideLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
          panelOutSideLayout.setVerticalGroup(
               panelOutSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelOutSideLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );

          btnBrowse.setBackground(new java.awt.Color(47, 155, 70));
          btnBrowse.setButtonName("Browse");
          btnBrowse.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnBrowseMouseClicked(evt);
               }
          });

          javax.swing.GroupLayout panelMessageLayout = new javax.swing.GroupLayout(panelMessage);
          panelMessage.setLayout(panelMessageLayout);
          panelMessageLayout.setHorizontalGroup(
               panelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelMessageLayout.createSequentialGroup()
                    .addGroup(panelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addGroup(panelMessageLayout.createSequentialGroup()
                              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                              .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGroup(panelMessageLayout.createSequentialGroup()
                              .addGap(20, 20, 20)
                              .addComponent(panelOutSide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGap(20, 20, 20))
          );
          panelMessageLayout.setVerticalGroup(
               panelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelMessageLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(panelOutSide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                    .addGroup(panelMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

     private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked

          dispose();
     }//GEN-LAST:event_btnCloseMouseClicked

     private void btnBrowseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseMouseClicked

          String filePath = PrintPanelToPDF.folderPath;
          if (pathOpen != null) {
               filePath = pathOpen;
          }
          File file = new File(filePath);
          if (file.exists()) {
               try {
                    Desktop.getDesktop().open(file);
                    dispose();
               } catch (IOException e) {
                    System.out.println("Error opening file: " + e.getMessage());
               }
          } else {
               System.out.println("File does not exist: " + filePath);
          }
     }//GEN-LAST:event_btnBrowseMouseClicked

     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    JavaAlertMessage dialog = new JavaAlertMessage(new javax.swing.JFrame(), true);
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

     public String getMessage() {
          return message;
     }

     public void setMessage(String message) {
          this.message = message;
          lbMessage.setText("<html>" + message + "</html>");
     }


     // Variables declaration - do not modify//GEN-BEGIN:variables
     private Button.Button btnBrowse;
     private Button.Button btnClose;
     private javax.swing.JLabel lbMessage;
     private javax.swing.JPanel panelMessage;
     private javax.swing.JPanel panelOutSide;
     // End of variables declaration//GEN-END:variables
}
