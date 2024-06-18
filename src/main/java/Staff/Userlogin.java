package Staff;

import BlogCode.JavaBlogImage;
import Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Model.Userlogin.UserDataModel;
import Model.Userlogin.UserModel;
import Model.Userlogin.UserSuccessModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import okhttp3.Response;

public class Userlogin extends javax.swing.JDialog {

    private String searchValue;
    
    public Userlogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getUserLogin(listGetUserLogin);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        header1.setBackground(WindowColor.darkGreen);
        eventSearchUser();
        
        jScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        // custom scroll speed jscrollPane for vertical
        JScrollBar verticalScrollBar = jScrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(30);
        verticalScrollBar.setBlockIncrement(35);
    }
    
    public void getUserLogin(JPanel jpanelData) {
          try {
               Response response = JavaConnection.get(JavaRoute.userAccount);
               if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    ObjectMapper objMap = new ObjectMapper();
                    UserSuccessModel data = objMap.readValue(responseData, UserSuccessModel.class);
                    UserDataModel[] listData = data.getData();
                    assignUser(listData, jpanelData);
                    
               } else {
                    System.err.println("fail loading user");
               }
          } catch (Exception e) {
               System.err.println("error getting user " + e);
          }
     }
    
    public void assignUser(UserDataModel[] listData, JPanel listGetUserLogin) {
          ArrayList<UserModel> user = new ArrayList<>();
          
          for (int i = 0; i < listData.length; i++) {
               var obj = listData[i];
               UserModel userLogin = new UserModel(
                    obj.getId(),
                    obj.getEmp_id(),
                    obj.getFull_name(),
                    obj.getUser_code()
               );
               user.add(userLogin);
          }
          
          appendUser(user, listGetUserLogin);
    }
    
     void appendUser(ArrayList<UserModel> listUser, JPanel listGetUserLogin) {
          GridBagLayout gridBagLayout = new GridBagLayout();
          gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
          gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
          gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
          
          listGetUserLogin.setLayout(gridBagLayout);
          
          int x = 0;
          int y = 0;
          for (int i = 0; i < listUser.size(); i++) {
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
               
               var listData = listUser.get(i);
               GetUserLogin user = new GetUserLogin();
               
               ButtonEvent events = new ButtonEvent() {
                    @Override
                    public void onSelect(String Key) {  // event edit
                       
                         ChangeUserPassword edit = new ChangeUserPassword(new JFrame(), true);
                         edit.setIconImage(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "bgwhite.jpg")).getImage());
                         edit.setEmId(listData.getEmId());
                         edit.setUserCode(listData.getUserCode());
                         edit.setVisible(true);
                         
                    }
               };
               
               user.initEvent(events);
               user.setId(listData.getId());
               user.setUserName(listData.getUserName());
               user.setUserCode(listData.getUserCode());
               
               try {
                    
                    TimerTask task = new TimerTask() {
                         @Override
                         public void run() {
                              // Task to be executed
                              user.setIconEdit(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "Edit.png")));
                         }
                    };
                    
                    Timer timer = new Timer();
                    timer.schedule(task, 500); // Delays task execution by 1 second

               } catch (Exception e) {
                    System.err.println("error read image = " + e);
               }
               
               
               listGetUserLogin.add(user, gbc);
          }
          listGetUserLogin.revalidate();
          listGetUserLogin.repaint();
     }
     
      //Action Search
     private void eventSearchUser() {
          // this event was called when user type on searchTextField 
          ButtonEvent event = new ButtonEvent() {
               @Override
               public void onKeyType() {
                    searchValue = searchField.getValueTextSearch();

                    if (searchValue.isEmpty()) {
                         listGetUserLogin.removeAll();
                         listGetUserLogin.revalidate();
                         listGetUserLogin.repaint();
                         getUserLogin(listGetUserLogin);
                    } else {

                         Response response = JavaConnection.get(JavaRoute.searchUserAccount + searchValue);

                         if (response.isSuccessful()) {
                              try {
                                  
                                   listGetUserLogin.removeAll();
                                   listGetUserLogin.revalidate();
                                   listGetUserLogin.repaint();
                                   String responseData = response.body().string();
                                   ObjectMapper obj = new ObjectMapper();
                                   UserSuccessModel data = obj.readValue(responseData, UserSuccessModel.class);
                                   UserDataModel[] listData = data.getData();
                                   
                                   if(listData.length > 0){
                                       assignUser(listData, listGetUserLogin);
                                   }else{
                                       listGetUserLogin.removeAll();
                                       UserNotFound nofound = new UserNotFound();
                                       listGetUserLogin.add(nofound);
                                       listGetUserLogin.revalidate();
                                       listGetUserLogin.repaint();
                                   }

                              } catch (Exception e) {
                                   System.out.println("err from search product = " + e);
                              }
                         }
                    }
               }
          };
          searchField.initEvent(event);
     }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelListProduct1 = new javax.swing.JPanel();
        header1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        searchField = new Components.SearchField();
        jScrollPane = new javax.swing.JScrollPane();
        listGetUserLogin = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        header1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Actions");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("User Code");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("User Name");

        javax.swing.GroupLayout header1Layout = new javax.swing.GroupLayout(header1);
        header1.setLayout(header1Layout);
        header1Layout.setHorizontalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        header1Layout.setVerticalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        searchField.setPlaceholder("Search by user name");
        searchField.setValueTextSearch("");

        jScrollPane.setBackground(new java.awt.Color(176, 215, 181));
        jScrollPane.setBorder(null);

        listGetUserLogin.setBackground(new java.awt.Color(176, 215, 181));

        javax.swing.GroupLayout listGetUserLoginLayout = new javax.swing.GroupLayout(listGetUserLogin);
        listGetUserLogin.setLayout(listGetUserLoginLayout);
        listGetUserLoginLayout.setHorizontalGroup(
            listGetUserLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
        );
        listGetUserLoginLayout.setVerticalGroup(
            listGetUserLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(listGetUserLogin);

        javax.swing.GroupLayout panelListProduct1Layout = new javax.swing.GroupLayout(panelListProduct1);
        panelListProduct1.setLayout(panelListProduct1Layout);
        panelListProduct1Layout.setHorizontalGroup(
            panelListProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListProduct1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelListProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelListProduct1Layout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        panelListProduct1Layout.setVerticalGroup(
            panelListProduct1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListProduct1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListProduct1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListProduct1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Userlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Userlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Userlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Userlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Userlogin dialog = new Userlogin(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel header;
    private javax.swing.JPanel header1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JPanel listGetUserLogin;
    private javax.swing.JPanel panelListProduct;
    private javax.swing.JPanel panelListProduct1;
    private Components.SearchField searchField;
    // End of variables declaration//GEN-END:variables
}
