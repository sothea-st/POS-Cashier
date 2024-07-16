package Setting.Attribute;

import BlogCode.JavaBlogImage;
import Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.Attribute.Attribute;
import Model.Attribute.DataAttributeModel;
import Model.Attribute.DetailAttributeModel;
import Model.Attribute.ListAttributeModel;
import Setting.Category.GetCategory;
import Setting.Category.NoDataAvaibalePanel;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import okhttp3.Response;
import org.json.JSONObject;

public class ListAttribute extends javax.swing.JDialog {

    String searchValue;
    
    public ListAttribute(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        // custom scroll speed jscrollPane for vertical
        JScrollBar verticalScrollBar = jScrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(30);
        verticalScrollBar.setBlockIncrement(35);
        
        header.setBackground(WindowColor.darkGreen);
        JavaConstant.addTitleAndLogo(this, "Attribute");
        
        getAttribute(listGetAttribute);
        eventSearchAttribute();
    }

    public void getAttribute(JPanel jpanelData) {
        try {

            Response response = JavaConnection.get(JavaRoute.attribute + "?pageNumber=0&pageSize=1000" );
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                ObjectMapper objMap = new ObjectMapper();
                ListAttributeModel data = objMap.readValue(responseData, ListAttributeModel.class);
                DataAttributeModel[] listData = data.getData();
                assignAttribute(listData, jpanelData);
            } else {
                System.err.println("fail loading attribute");
            }
        } catch (Exception e) {
            System.err.println("error getting attribute " + e);
        }
    }
     
    public void assignAttribute(DataAttributeModel[] listData, JPanel listGetAttribute) {
        ArrayList<Attribute> attr = new ArrayList<>();
          
        for (int i = 0; i < listData.length; i++) {
            var obj = listData[i];
            Attribute getAttr = new Attribute(
                    obj.getId(),
                    obj.getAttrNameEn(),
                    obj.getAttrNameKh()
            );
            attr.add(getAttr);
        }

        appenAttribute(attr, listGetAttribute);
    }
    
    void appenAttribute(ArrayList<Attribute> listAttribute, JPanel listGetAttribute) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
        gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        listGetAttribute.setLayout(gridBagLayout);

        int x = 0;
        int y = 0;
        if(listAttribute.size() > 0){
            for (int i = 0; i < listAttribute.size(); i++) {
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

                var listData = listAttribute.get(i);
                
                GetCategory b = new GetCategory();

                ButtonEvent events = new ButtonEvent() {
                    @Override
                    public void onSelect(String Key) {  // event edit
                        AddAttribute edit = new AddAttribute(new JFrame(), true);
                        try {
                            Response response = JavaConnection.get(JavaRoute.attribute + "/" + listData.getId());
                            String responseData = response.body().string();
                            ObjectMapper objMap = new ObjectMapper();
                            DetailAttributeModel data = objMap.readValue(responseData, DetailAttributeModel.class);
                            
                            System.out.println("data : " + data);

                            edit.setId(data.getId());
                            edit.setListGetAttribute(listGetAttribute);

                            edit.setValueEdit(
                                data.getAttrNameEn(),
                                data.getAttrNameKh()
                            );

                            edit.setVisible(true);
                        } catch (Exception e) {
                             System.err.println("error getting attribute " + e);
                        }
                    }
                    
                    
                    @Override
                    public void onRemove(String Key) {  // event delete brand
                        try {
                            UIManager UI = new UIManager();
                            UI.put("OptionPane.background", WindowColor.mediumGreen);
                            UI.put("Panel.background", WindowColor.mediumGreen);
                            UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

                            int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this attribute?",
                                    "Delete Attribute?", JOptionPane.YES_NO_OPTION);

                            if (resp == JOptionPane.YES_OPTION) {
                                JSONObject json = new JSONObject();
                                json.put("status", false);
                                json.put("isDeleted", true);
                                Response response = JavaConnection.delete(JavaRoute.attribute + "/" + listData.getId(), json);

                                if (response.isSuccessful()) {
                                    ListAttribute list = new ListAttribute(new JFrame(), true);
                                    listGetAttribute.removeAll();
                                    listGetAttribute.revalidate();
                                    listGetAttribute.repaint();
                                    list.getAttribute(listGetAttribute);
                                    System.out.println("Successful deleted ");
                                }
                            } else {
                                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                            }

                        } catch (Exception e) {
                            System.err.println("error getting attribute " + e);
                        }
                    }
                };

                b.initEvent(events);
                b.setId(listData.getId());
                
                b.setCategoryNameEn(listData.getAttributeNameEn());
                b.setCategoryNameKh(listData.getAttributeNameKh());

                try {

                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            // Task to be executed
                            b.setIconEdit(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "Edit.png")));
                            b.setIconDelete(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "DeleteIcon.png")));
                        }
                    };

                    Timer timer = new Timer();
                    timer.schedule(task, 500); // Delays task execution by 1 second

                } catch (Exception e) {
                    System.err.println("error read image = " + e);
                }

                listGetAttribute.add(b, gbc);
            }  
        }else{
            NoDataAvaibalePanel no = new NoDataAvaibalePanel();
            listGetAttribute.add(no);
        }
        
        listGetAttribute.revalidate();
        listGetAttribute.repaint();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelListAttribute = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        searchField = new Components.SearchField();
        jScrollPane = new javax.swing.JScrollPane();
        listGetAttribute = new javax.swing.JPanel();
        buttonCancel1 = new ButtonPackage.ButtonCancel();
        btnAdd = new Button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        header.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Actions");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Attribute Name Kh");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Attribute Name");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        searchField.setPlaceholder("Search");
        searchField.setValueTextSearch("");

        jScrollPane.setBackground(new java.awt.Color(176, 215, 181));
        jScrollPane.setBorder(null);

        listGetAttribute.setBackground(new java.awt.Color(176, 215, 181));

        javax.swing.GroupLayout listGetAttributeLayout = new javax.swing.GroupLayout(listGetAttribute);
        listGetAttribute.setLayout(listGetAttributeLayout);
        listGetAttributeLayout.setHorizontalGroup(
            listGetAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );
        listGetAttributeLayout.setVerticalGroup(
            listGetAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(listGetAttribute);

        buttonCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancel1MouseClicked(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(47, 155, 70));
        btnAdd.setButtonName("+ Add Attribute");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelListAttributeLayout = new javax.swing.GroupLayout(panelListAttribute);
        panelListAttribute.setLayout(panelListAttributeLayout);
        panelListAttributeLayout.setHorizontalGroup(
            panelListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListAttributeLayout.createSequentialGroup()
                .addGroup(panelListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelListAttributeLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelListAttributeLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelListAttributeLayout.createSequentialGroup()
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(15, 15, 15))
        );
        panelListAttributeLayout.setVerticalGroup(
            panelListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListAttributeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListAttributeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelListAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListAttribute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancel1MouseClicked
        dispose();
    }//GEN-LAST:event_buttonCancel1MouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        AddAttribute add = new AddAttribute(new JFrame(), true);
        add.setListGetAttribute(listGetAttribute);
        add.setVisible(true);
    }//GEN-LAST:event_btnAddMouseClicked

    //Action Search
    private void eventSearchAttribute() {
        // this event was called when user type on searchTextField 
        ButtonEvent events = new ButtonEvent() {
            @Override
            public void onKeyType() {
                searchValue = searchField.getValueTextSearch();
                
                if (searchValue.isEmpty()) {
                    listGetAttribute.removeAll();
                    listGetAttribute.revalidate();
                    listGetAttribute.repaint();
                    getAttribute(listGetAttribute);
                } else {

                    Response response = JavaConnection.get(JavaRoute.searchAttribute + searchValue);

                    if (response.isSuccessful()) {
                        try {
                            listGetAttribute.removeAll();
                            listGetAttribute.revalidate();
                            listGetAttribute.repaint();
                            String responseData = response.body().string();
                            ObjectMapper obj = new ObjectMapper();
                            ListAttributeModel data = obj.readValue(responseData, ListAttributeModel.class);
                            DataAttributeModel[] listData = data.getData();
                            if (listData.length > 0) {
                                assignAttribute(listData, listGetAttribute);
                            } else {
                                listGetAttribute.removeAll();
                                NoDataAvaibalePanel notfound = new NoDataAvaibalePanel();
                                notfound.setLabelName("Not Found!");
                                listGetAttribute.add(notfound);
                                listGetAttribute.revalidate();
                                listGetAttribute.repaint();
                            }

                        } catch (Exception e) {
                            System.out.println("err from search attribute = " + e);
                        }
                    }
                }
            }
        };
        searchField.initEvent(events);
    }
    
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
            java.util.logging.Logger.getLogger(ListAttribute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListAttribute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListAttribute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListAttribute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListAttribute dialog = new ListAttribute(new javax.swing.JFrame(), true);
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
    private Button.Button btnAdd;
    private ButtonPackage.ButtonCancel buttonCancel1;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JPanel listGetAttribute;
    private javax.swing.JPanel panelListAttribute;
    private Components.SearchField searchField;
    // End of variables declaration//GEN-END:variables
}
