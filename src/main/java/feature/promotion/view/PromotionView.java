package feature.promotion.view;

public class PromotionView extends javax.swing.JDialog {

     public PromotionView(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 1400, Short.MAX_VALUE)
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 776, Short.MAX_VALUE)
          );

          pack();
     }// </editor-fold>//GEN-END:initComponents

     public static void main(String args[]) {
          java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    PromotionView dialog = new PromotionView(new javax.swing.JFrame(), true);
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
     // End of variables declaration//GEN-END:variables
}
