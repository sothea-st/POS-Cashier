package feature.user_permission;

import Color.WindowColor;

public class UserPermissionForm extends javax.swing.JDialog {

     public UserPermissionForm(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();

          //call init
          init();
     }

     public void init() {
          setDefaultCloseOperation(DISPOSE_ON_CLOSE); // not allow close dialog
          setResizable(false); // no allow resize dialog
          
          panel.setBackground(WindowColor.slightGreen); // set background panel
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panel = new javax.swing.JPanel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 1167, Short.MAX_VALUE)
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 589, Short.MAX_VALUE)
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          pack();
     }// </editor-fold>//GEN-END:initComponents

     public static void main(String args[]) {

          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    UserPermissionForm dialog = new UserPermissionForm(new javax.swing.JFrame(), true);
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
     private javax.swing.JPanel panel;
     // End of variables declaration//GEN-END:variables
}
