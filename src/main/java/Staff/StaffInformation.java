package Staff;

import BlogCode.JavaBlogImage;
import Color.WindowColor;
import Constant.JavaBaseUrl;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;

import Event.ButtonEvent;
import Fonts.WindowFonts;

import Model.Staff.DetailGetDataModel;
import Model.Staff.StaffDataSuccessModel;
import Model.Staff.StaffGetDataModel;
import Model.Staff.StaffModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

public class StaffInformation extends javax.swing.JDialog {

    private String searchValue;
    private String pageNumber = "0";
    private int pageSize = 10;
    private boolean isCheckSearch = true;

    public StaffInformation(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        initComponents();

        header.setBackground(WindowColor.darkGreen);
        getStaff(listGetStaff,true);
        eventSearchStaff();

        // custome scrollbar ui
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        // custom scroll speed jscrollPane for vertical
        JScrollBar verticalScrollBar = jScrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(30);
        verticalScrollBar.setBlockIncrement(35);

        JavaConstant.addTitleAndLogo(this, "Staff Information");
        eventPagination();

    }
     
    private void eventPagination() {
        ButtonEvent event = new ButtonEvent() {
             @Override
             public void onMouseClick(String value) {
                  if (isCheckSearch) {
                       int _value = Integer.parseInt(value) - 1; // value pageNumber star from 0 
                       pageNumber = String.valueOf(_value);
                       getStaff(listGetStaff, true);
                  }
             }
        };
        paginationPanel.initEvent(event);
    }

    public void getStaff(JPanel jpanelData, boolean isCheck) {
        try {
            Response response = null;
            if (isCheck) { // isCheck true get items
                response = JavaConnection.get(JavaRoute.employee + "?pageNumber=" + pageNumber + "&pageSize=10");
            } else { // isCheck false search
                isCheckSearch = false;
                response = JavaConnection.get(JavaRoute.searchEmployee + searchValue + "?pageNumber=" + pageNumber + "&pageSize=50");
            }

            if (response.isSuccessful()) {
                String responseData = response.body().string();
                ObjectMapper objMap = new ObjectMapper();
                StaffDataSuccessModel data = objMap.readValue(responseData, StaffDataSuccessModel.class);
                StaffGetDataModel[] listData = data.getData();
                
                if (isCheck) {
                    paginationPanel.setTotalPage(data.getCount(), pageSize);
                } else {
                    paginationPanel.resetPage();
                }
                
                assignStaff(listData, jpanelData);

            } else {
                System.err.println("fail loading staff");
            }
        } catch (Exception e) {
            System.err.println("error getting staff " + e);
        }
    }

    public void assignStaff(StaffGetDataModel[] listData, JPanel listStaff) {
        ArrayList<StaffModel> staffList = new ArrayList<>();

        for (int i = 0; i < listData.length; i++) {
            var obj = listData[i];
            StaffModel staff = new StaffModel(
                    obj.getId(),
                    obj.getNameEn(),
                    obj.getNameKh(),
                    obj.getGender(),
                    obj.getDob(),
                    obj.getStartDate(),
                    obj.getImageName(),
                    obj.getContact(),
                    obj.getAddress(),
                    obj.getRoleName(),
                    obj.getCreateBy(),
                    obj.getCreateDate(),
                    obj.isStatus(),
                    obj.isDeleted()
            );
            staffList.add(staff);
        }
        appendStaff(staffList, listStaff);
    }
     
     
    private void reloadPanel() {
        listGetStaff.removeAll();
        listGetStaff.revalidate();
        listGetStaff.repaint();
    }

    void appendStaff(ArrayList<StaffModel> listStaff, JPanel listGetStaff) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
        gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        listGetStaff.setLayout(gridBagLayout);
        reloadPanel();

        int x = 0;
        int y = 0;
        for (int i = 0; i < listStaff.size(); i++) {
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

            var listData = listStaff.get(i);
            GetStaff prod = new GetStaff();

            ButtonEvent events = new ButtonEvent() {

                @Override
                public void onSelect(String Key) {  // event edit
                    InsertStaff edit = new InsertStaff(new JFrame(), true);

                    try {
                        Response response = JavaConnection.get(JavaRoute.employee + "/" + listData.getId());
                        String responseData = response.body().string();
                        ObjectMapper objMap = new ObjectMapper();
                        DetailGetDataModel data = objMap.readValue(responseData, DetailGetDataModel.class);

                        edit.setId(data.getId());
                        edit.setListGetStaff(listGetStaff);

                        String _urlImg = "";
                        if (listData.getImageName().contains("media/file/crm/uploadfile/")) {
                            _urlImg = JavaConstant.urlImage + listData.getImageName();
                        } else {
                            _urlImg = new JavaBaseUrl().getBaseUrl() + "/public/addImageForBackground/" + listData.getImageName();
                        }

                        edit.setValueEdit(
                                data.getNameEn(),
                                data.getDob(),
                                data.getStartDate(),
                                data.getAddress(),
                                data.getGender(),
                                "" + data.getRoleId(),
                                data.getContact(),
                                _urlImg
                        );

                        edit.setVisible(true);

                    } catch (Exception e) {
                        System.err.println("error getting product " + e);
                    }
                }

                @Override
                public void onRemove(String Key) {  // event delete staff
                    try {

                        UIManager UI = new UIManager();
                        UI.put("OptionPane.background", WindowColor.mediumGreen);
                        UI.put("Panel.background", WindowColor.mediumGreen);
                        UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

                        int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this staff?",
                                "Delete Staff?", JOptionPane.YES_NO_OPTION);

                        if (resp == JOptionPane.YES_OPTION) {
                            JSONObject json = new JSONObject();
                            json.put("status", false);
                            json.put("is_deleted", true);
                            Response response = JavaConnection.delete(JavaRoute.employee + "/" + listData.getId(), json);

                            if (response.isSuccessful()) {
                                StaffInformation list = new StaffInformation(new JFrame(), true);
                                listGetStaff.removeAll();
                                listGetStaff.revalidate();
                                listGetStaff.repaint();
                                list.getStaff(listGetStaff, true);
                                System.out.println("Successful deleted ");
                            }
                        } else {
                            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        }

                    } catch (Exception e) {
                        System.err.println("error getting product " + e);
                    }
                }
            };

            prod.initEvent(events);
            prod.setId(listData.getId());
            prod.setStaffName(listData.getNameEn());
            prod.setDateOfBirth(listData.getDob());
            prod.setContact(listData.getContact());
            prod.setGender(StringUtils.capitalize(listData.getGender()));
            prod.setAddress(listData.getAddress());
            prod.setRoleName(listData.getRoleName());
            prod.setStartDate(listData.getStartDate());

            try {

                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        // Task to be executed
                        prod.setIconEdit(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "Edit.png")));
                        prod.setIconDelete(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "DeleteIcon.png")));
                    }
                };

                Timer timer = new Timer();
                timer.schedule(task, 500); // Delays task execution by 1 second

            } catch (Exception e) {
                System.err.println("error read image = " + e);
            }

            paginationPanel.setVisible(true);
            listGetStaff.add(prod, gbc);
        }
        
        if (listStaff.size() == 0) {
            listGetStaff.setLayout(new BorderLayout());
            UserNotFound nofound = new UserNotFound();
            listGetStaff.add(nofound, BorderLayout.CENTER);
            listGetStaff.add(nofound);
            listGetStaff.revalidate();
            listGetStaff.repaint();
            paginationPanel.setVisible(false);
        }
        
        listGetStaff.revalidate();
        listGetStaff.repaint();
    }

    //Action Search
    private void eventSearchStaff() {
        // this event was called when user type on searchTextField 
        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onKeyType() {
                searchValue = searchField.getValueTextSearch();

                if (searchValue.isEmpty()) {
                    isCheckSearch = true;
                    pageNumber = "0";
                    getStaff(listGetStaff, true);
                    return;
                }
                getStaff(listGetStaff, false);
            }
        };
        searchField.initEvent(event);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelListProduct = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        searchField = new Components.SearchField();
        jScrollPane = new javax.swing.JScrollPane();
        listGetStaff = new javax.swing.JPanel();
        btnAddStaff = new Button.Button();
        buttonCancel1 = new ButtonPackage.ButtonCancel();
        paginationPanel = new pagination.PaginationPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        header.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Actions");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Date of Birth");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Contact");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Staff Name");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Gender");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Address");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Role ");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Started Date");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        searchField.setPlaceholder("Search by staff name");
        searchField.setValueTextSearch("");

        jScrollPane.setBackground(new java.awt.Color(176, 215, 181));
        jScrollPane.setBorder(null);

        listGetStaff.setBackground(new java.awt.Color(176, 215, 181));

        javax.swing.GroupLayout listGetStaffLayout = new javax.swing.GroupLayout(listGetStaff);
        listGetStaff.setLayout(listGetStaffLayout);
        listGetStaffLayout.setHorizontalGroup(
            listGetStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1315, Short.MAX_VALUE)
        );
        listGetStaffLayout.setVerticalGroup(
            listGetStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(listGetStaff);

        btnAddStaff.setBackground(new java.awt.Color(47, 155, 70));
        btnAddStaff.setButtonName("+ Add Staff");
        btnAddStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddStaffMouseClicked(evt);
            }
        });

        buttonCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelListProductLayout = new javax.swing.GroupLayout(panelListProduct);
        panelListProduct.setLayout(panelListProductLayout);
        panelListProductLayout.setHorizontalGroup(
            panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListProductLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelListProductLayout.createSequentialGroup()
                        .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelListProductLayout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        panelListProductLayout.setVerticalGroup(
            panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelListProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paginationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddStaffMouseClicked

          InsertStaff addStaff = new InsertStaff(new JFrame(), true);
          addStaff.setListGetStaff(listGetStaff);
          addStaff.setVisible(true);
          
    }//GEN-LAST:event_btnAddStaffMouseClicked

     private void buttonCancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancel1MouseClicked
         dispose();
     }//GEN-LAST:event_buttonCancel1MouseClicked

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(StaffInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                StaffInformation dialog = new StaffInformation(new javax.swing.JFrame(), true);
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
    private Button.Button btnAddStaff;
    private ButtonPackage.ButtonCancel buttonCancel1;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JPanel listGetStaff;
    private pagination.PaginationPanel paginationPanel;
    private javax.swing.JPanel panelListProduct;
    private Components.SearchField searchField;
    // End of variables declaration//GEN-END:variables
}
