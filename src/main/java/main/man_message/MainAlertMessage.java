/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package main.man_message;

import Components.Color.WindowColor;
import Constant.JavaConstant;
import feature.Print.pdf.PrintPanelToPDF;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class MainAlertMessage extends javax.swing.JDialog {

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

     public MainAlertMessage(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          btnClose.setButtonName(JavaConstant.titleClose);
          setResizable(false);

          btnBrowse.setVisible(false);

          setTitle("Message Path of file");
     }

     public void setMessage(String message) {
          this.message = message;
          lbMessage.setText("<html>" + message + "</html>");
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          btnBrowse = new Button.Button();
          btnClose = new Button.Button();
          lbMessage = new javax.swing.JLabel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          btnBrowse.setBackground(new java.awt.Color(47, 155, 70));
          btnBrowse.setButtonName("Browse");
          btnBrowse.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnBrowseMouseClicked(evt);
               }
          });

          btnClose.setButtonName("Close");
          btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseClicked(java.awt.event.MouseEvent evt) {
                    btnCloseMouseClicked(evt);
               }
          });

          lbMessage.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
          lbMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
          lbMessage.setText("Message");

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                         .addComponent(lbMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                         .addGroup(layout.createSequentialGroup()
                              .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(20, 20, 20))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(lbMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20))
          );

          pack();
          setLocationRelativeTo(null);
     }// </editor-fold>//GEN-END:initComponents

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

     private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked

          dispose();
     }//GEN-LAST:event_btnCloseMouseClicked

     /**
      * @param args the command line
      * arguments
      */
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
               java.util.logging.Logger.getLogger(MainAlertMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(MainAlertMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(MainAlertMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(MainAlertMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>

          /* Create and display the dialog */
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    MainAlertMessage dialog = new MainAlertMessage(new javax.swing.JFrame(), true);
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
     private Button.Button btnBrowse;
     private Button.Button btnClose;
     private javax.swing.JLabel lbMessage;
     // End of variables declaration//GEN-END:variables
}
