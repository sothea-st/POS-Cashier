package feature.user_permission;

import Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import Model.ProductModelV1.ProductResponseV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import feature.user_permission.model.UserPermissionModel;
import feature.user_permission.model.UserPermissionModel.UserPermissionDetail;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import okhttp3.Response;

public class UserPermissionForm extends javax.swing.JDialog {

     public UserPermissionForm(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();

          //call init
          init();

          // call getRole()
          getRole();
     }

     public void init() {
          setDefaultCloseOperation(DISPOSE_ON_CLOSE); // not allow close dialog
          setResizable(false); // no allow resize dialog
          setTitle("User Permission");
          panel.setBackground(WindowColor.mediumGreen); // set background panel

          JavaConstant.setScroll(jScrollPane1);
     }

     private void getRole() {
          try {
               Response response = JavaConnection.get(JavaRoute.permissionByParentId + "/0");

               String responseData = response.body().string();

               ObjectMapper object = new ObjectMapper();

               UserPermissionModel model = object.readValue(responseData, UserPermissionModel.class);

               appentData(model.getData());

          } catch (Exception e) {
               System.err.println("error getting product " + e);
          }
     }

     private void appentData(UserPermissionDetail[] userPermissionDetails) {

          // reload panel
          panelData.removeAll();
          reloadPanelData();

          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

          panelData.setLayout(gridBagLayout);

          int x = 0;
          int y = 0;

//          for (UserPermissionDetail p : userPermissionDetails) {
          for( int i = 0 ; i < 100 ; i++) {
               GridBagConstraints gbc = new GridBagConstraints();

               gbc.gridx = 0; // Start at the first column
               gbc.gridy = y; // Increment row for each panelRole
               gbc.gridwidth = GridBagConstraints.REMAINDER; // Spans the entire row
               gbc.fill = GridBagConstraints.HORIZONTAL; // Makes panelRole stretch horizontally
               gbc.weightx = 1.0; // Allocates extra horizontal space to panelRole
               gbc.anchor = GridBagConstraints.NORTH; // Aligns to the top
               x++;
               if (x == 1) {
                    x = 0;
                    y++;
               }

               PanelRole panelRole = new PanelRole();
               panelRole.setText(""+i);
               panelRole.setId(i);
               
               ButtonEvent event = new ButtonEvent() {
                    @Override
                    public void onMouseClick(String value) {
                         System.err.println("valuePermissionID : " + value);
                         panelRole.setChecked();
                    }  
               };
               panelRole.initEvent(event);

               panelData.add(panelRole, gbc);

          }
          reloadPanelData();

     }

     private void reloadPanelData() {

          panelData.revalidate();
          panelData.repaint();
     }

     @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     private void initComponents() {

          panel = new javax.swing.JPanel();
          cmbBrand = new FormComponent.combobox.JavaCombobox();
          jScrollPane1 = new javax.swing.JScrollPane();
          panelData = new javax.swing.JPanel();

          setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

          cmbBrand.setLabelName("Role *");
          cmbBrand.setName(""); // NOI18N

          javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
          panelData.setLayout(panelDataLayout);
          panelDataLayout.setHorizontalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 0, Short.MAX_VALUE)
          );
          panelDataLayout.setVerticalGroup(
               panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGap(0, 509, Short.MAX_VALUE)
          );

          jScrollPane1.setViewportView(panelData);

          javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
          panel.setLayout(panelLayout);
          panelLayout.setHorizontalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                         .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1217, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(cmbBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(20, Short.MAX_VALUE))
          );
          panelLayout.setVerticalGroup(
               panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(panelLayout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(cmbBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE))
          );

          javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
          getContentPane().setLayout(layout);
          layout.setHorizontalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
          );
          layout.setVerticalGroup(
               layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );

          pack();
          setLocationRelativeTo(null);
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
     private FormComponent.combobox.JavaCombobox cmbBrand;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JPanel panel;
     private javax.swing.JPanel panelData;
     // End of variables declaration//GEN-END:variables
}
