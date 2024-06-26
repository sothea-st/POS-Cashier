package Setting.Category;

import BlogCode.JavaBlogImage;
import Color.WindowColor;
import Constant.JavaConnection;
import Constant.JavaRoute;
import CustomeUI.CustomScrollBarUI;
import Event.ButtonEvent;
import Fonts.WindowFonts;
import Model.Category.CategoryGetdataModel;
import Model.Category.CategorySuccessModel;
import Model.Category.DetailCategoryModel;
import Model.Category.DetailCategorySuccessModel;
import Model.Category.ModelCategory;
import Setting.Department.InsertDepartment;
import Setting.Division.InsertDivision;
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

public class Category extends javax.swing.JDialog {

    private String code;
    private String searchValue;
    
    public Category(java.awt.Frame parent, boolean modal, String codeType) {
        super(parent, modal);
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        header1.setBackground(WindowColor.darkGreen);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        jScrollPane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        // custom scroll speed jscrollPane for vertical
        JScrollBar verticalScrollBar = jScrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(30);
        verticalScrollBar.setBlockIncrement(35);
        getCategory(listGetCategory, codeType);
        eventSearch();
    }
    
    
    public void getCategory(JPanel jpanelData, String codeType) {
        try {
            
            String codeCategory = "";
            
            if(codeType.equals("division")){
                codeCategory = "division";
            }else if(codeType.equals("category")){
                codeCategory = "category";
            }else if(codeType.equals("department")){
                codeCategory = "department";
            }else if(codeType.equals("subcategory")){
                codeCategory = "subcategory";
            }
            
            Response response = JavaConnection.get(JavaRoute.getCategoryByCode + codeCategory);
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                ObjectMapper objMap = new ObjectMapper();
                CategorySuccessModel data = objMap.readValue(responseData, CategorySuccessModel.class);
                CategoryGetdataModel[] listData = data.getData();
                assignCategory(listData, jpanelData,codeType);

            } else {
                System.err.println("fail loading category");
            }
        } catch (Exception e) {
            System.err.println("error getting category " + e);
        }
    }
    
    public void assignCategory(CategoryGetdataModel[] listData, JPanel listGetCategory, String codeType) {
        ArrayList<ModelCategory> cat = new ArrayList<>();
          
        for (int i = 0; i < listData.length; i++) {
            var obj = listData[i];
            ModelCategory category = new ModelCategory(
                    obj.getId(),
                    obj.getCatNameEn(),
                    obj.getCatNameKh()
            );
            cat.add(category);
        }

        appendCategory(cat, listGetCategory, codeType);
    }
    
     void appendCategory(ArrayList<ModelCategory> listCategory, JPanel listGetCategory, String codeType) {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // one row has 5 column
        gridBagLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        listGetCategory.setLayout(gridBagLayout);

        int x = 0;
        int y = 0;
        if(listCategory.size() > 0){
            for (int i = 0; i < listCategory.size(); i++) {
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

                var listData = listCategory.get(i);
                GetCategory category = new GetCategory();

                ButtonEvent events = new ButtonEvent() {
                    @Override
                    public void onSelect(String Key) {  // event edit
                        
                            if(codeType.equals("division")){
                                InsertDivision edit = new InsertDivision(new JFrame(), true, codeType);
                                try {
                                    Response response = JavaConnection.get(JavaRoute.addCategory + "/" + listData.getId());
                                    String responseData = response.body().string();
                                    ObjectMapper objMap = new ObjectMapper();
                                    DetailCategorySuccessModel datas = objMap.readValue(responseData, DetailCategorySuccessModel.class);
                                    DetailCategoryModel listCategory = datas.getData();

                                    edit.setId(listCategory.getId());
                                    edit.setMovePosition(listCategory.getMovePosition());
                                    edit.setParentId(listCategory.getParentId());
                                    edit.setListGetCategory(listGetCategory);

                                    edit.setValueEdit(
                                        listCategory.getCatNameEn(),
                                        listCategory.getCatNameKh()
                                    );
                                    
                                    edit.setVisible(true);
                                } catch (Exception e) {
                                     System.err.println("error getting product " + e);
                                }
                            }else if(codeType.equals("department")){
                                InsertDepartment edit = new InsertDepartment(new JFrame(), true, codeType);
                                try {
                                    Response response = JavaConnection.get(JavaRoute.addCategory + "/" + listData.getId());
                                    String responseData = response.body().string();
                                    ObjectMapper objMap = new ObjectMapper();
                                    DetailCategorySuccessModel datas = objMap.readValue(responseData, DetailCategorySuccessModel.class);
                                    DetailCategoryModel listCategory = datas.getData();

                                    edit.setId(listCategory.getId());
                                    edit.setMovePosition(listCategory.getMovePosition());
                                    edit.setListGetCategory(listGetCategory);

                                    edit.setValueEdit(
                                        listCategory.getCatNameEn(),
                                        listCategory.getCatNameKh(),
                                        ""+listCategory.getParentId()
                                    );
                                    
                                    edit.setVisible(true);
                                } catch (Exception e) {
                                     System.err.println("error getting product " + e);
                                }
                            }else if(codeType.equals("category")){
                                InsertCategory edit = new InsertCategory(new JFrame(), true, codeType);
                                try {
                                    Response response = JavaConnection.get(JavaRoute.addCategory + "/" + listData.getId());
                                    String responseData = response.body().string();
                                    ObjectMapper objMap = new ObjectMapper();
                                    DetailCategorySuccessModel datas = objMap.readValue(responseData, DetailCategorySuccessModel.class);
                                    DetailCategoryModel listCategory = datas.getData();

                                    edit.setId(listCategory.getId());
                                    edit.setMovePosition(listCategory.getMovePosition());
                                    edit.setListGetCategory(listGetCategory);

                                    edit.setValueEdit(
                                        listCategory.getCatNameEn(),
                                        listCategory.getCatNameKh(),
                                        ""+listCategory.getParentId()
                                    );
                                    
                                    edit.setVisible(true);
                                } catch (Exception e) {
                                     System.err.println("error getting product " + e);
                                }
                            }
                            
                        
//                        }else{
//                            InsertCategory edit = new InsertCategory(new JFrame(), true, codeType);
//                            try {
//                                Response response = JavaConnection.get(JavaRoute.addCategory + "/" + listData.getId());
//                                String responseData = response.body().string();
//                                ObjectMapper objMap = new ObjectMapper();
//                                DetailCategorySuccessModel datas = objMap.readValue(responseData, DetailCategorySuccessModel.class);
//                                DetailCategoryModel listCategory = datas.getData();
//
//                                edit.setId(listCategory.getId());
//                                edit.setMovePosition(listCategory.getMovePosition());
//                                edit.setListGetCategory(listGetCategory);
//                                edit.setValueEdit(
//                                     listCategory.getCatNameEn(),
//                                     listCategory.getCatNameKh(),
//                                     ""+listCategory.getParentId()
//                                );
//                                edit.setVisible(true);
//                            } catch (Exception e) {
//                                 System.err.println("error getting product " + e);
//                            }
//                        }
                    }
                    
                    @Override
                    public void onRemove(String Key) {  // event delete staff
                        try {
                            UIManager UI = new UIManager();
                            UI.put("OptionPane.background", WindowColor.mediumGreen);
                            UI.put("Panel.background", WindowColor.mediumGreen);
                            UI.put("OptionPane.messageFont", WindowFonts.timeNewRomanBold14);

                            int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this ?",
                                    "Delete " + codeType + "?", JOptionPane.YES_NO_OPTION);

                            if (resp == JOptionPane.YES_OPTION) {
                                JSONObject json = new JSONObject();
                                Response response = JavaConnection.delete(JavaRoute.addCategory + "/" + listData.getId(), json);
                                
                                System.out.println(" response " + response);
                                
                                if (response.isSuccessful()) {
                                    Category list = new Category(new JFrame(), true, codeType);
                                    listGetCategory.removeAll();
                                    listGetCategory.revalidate();
                                    listGetCategory.repaint();
                                    list.getCategory(listGetCategory, codeType);
                                }
                            } else {
                                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                            }

                        } catch (Exception e) {
                            System.err.println("error getting product " + e);
                        }
                    }
                };

                category.initEvent(events);
                category.setId(listData.getId());
                category.setCategoryNameEn(listData.getCategoryNameEn());
                category.setCategoryNameKh(listData.getCategoryNameKh());

                try {

                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            // Task to be executed
                            category.setIconEdit(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "Edit.png")));
                            category.setIconDelete(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "DeleteIcon.png")));
                        }
                    };

                    Timer timer = new Timer();
                    timer.schedule(task, 500); // Delays task execution by 1 second

                } catch (Exception e) {
                    System.err.println("error read image = " + e);
                }

                listGetCategory.add(category, gbc);
            }  
        }else{
            NoDataAvailable no = new NoDataAvailable();
            listGetCategory.add(no);
        }
        
        listGetCategory.revalidate();
        listGetCategory.repaint();
    }
     
    //Action Search
    private void eventSearch() {
        // this event was called when user type on searchTextField 
        ButtonEvent event = new ButtonEvent() {
            @Override
            public void onKeyType() {
                searchValue = searchField.getValueTextSearch();
            }
        };
        searchField.initEvent(event);
    }

    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        if(code.equals("division")){
            jLabel10.setText("Divison Name");
            jLabel8.setText("Divison Name Kh");
            btnAdd.setButtonName("+ Add Division");
        }else if(code.equals("department")){
            jLabel10.setText("Department Name");
            jLabel8.setText("Department Name Kh");
            btnAdd.setButtonName("+ Add Department");
        }else if(code.equals("category")){
            jLabel10.setText("Category Name");
            jLabel8.setText("Category Name Kh");
            btnAdd.setButtonName("+ Add Category");
        }else if(code.equals("subcategory")){
            jLabel10.setText("Sub Category Name");
            jLabel8.setText("Sub Category Name Kh");
            btnAdd.setButtonName("+ Add Sub Category");
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelListCategory = new javax.swing.JPanel();
        header1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        searchField = new Components.SearchField();
        jScrollPane = new javax.swing.JScrollPane();
        listGetCategory = new javax.swing.JPanel();
        buttonCancel1 = new ButtonPackage.ButtonCancel();
        btnAdd = new Button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        header1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Actions");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Category Name Kh");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Category Name En");

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

        searchField.setPlaceholder("Search ");
        searchField.setValueTextSearch("");

        jScrollPane.setBackground(new java.awt.Color(176, 215, 181));
        jScrollPane.setBorder(null);

        listGetCategory.setBackground(new java.awt.Color(176, 215, 181));

        javax.swing.GroupLayout listGetCategoryLayout = new javax.swing.GroupLayout(listGetCategory);
        listGetCategory.setLayout(listGetCategoryLayout);
        listGetCategoryLayout.setHorizontalGroup(
            listGetCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
        );
        listGetCategoryLayout.setVerticalGroup(
            listGetCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );

        jScrollPane.setViewportView(listGetCategory);

        buttonCancel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancel1MouseClicked(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(47, 155, 70));
        btnAdd.setButtonName("+ Add Division");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelListCategoryLayout = new javax.swing.GroupLayout(panelListCategory);
        panelListCategory.setLayout(panelListCategoryLayout);
        panelListCategoryLayout.setHorizontalGroup(
            panelListCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListCategoryLayout.createSequentialGroup()
                .addGroup(panelListCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelListCategoryLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelListCategoryLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelListCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelListCategoryLayout.createSequentialGroup()
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(15, 15, 15))
        );
        panelListCategoryLayout.setVerticalGroup(
            panelListCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListCategoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(buttonCancel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancel1MouseClicked
        dispose();
    }//GEN-LAST:event_buttonCancel1MouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        
        if(code.equals("division")){
            InsertDivision addDivision = new InsertDivision(new JFrame(), true, code);
            addDivision.setListGetCategory(listGetCategory);
            addDivision.setVisible(true);
        }if(code.equals("department")){
            InsertDepartment addDepartment = new InsertDepartment(new JFrame(), true, code);
            addDepartment.setListGetCategory(listGetCategory);
            addDepartment.setVisible(true);
        }else{
            InsertCategory addCategory = new InsertCategory(new JFrame(), true, code);
            addCategory.setListGetCategory(listGetCategory);
            addCategory.setVisible(true);
        }
    }//GEN-LAST:event_btnAddMouseClicked

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
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Category.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Category dialog = new Category(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JPanel header1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JPanel listGetCategory;
    private javax.swing.JPanel panelListCategory;
    private Components.SearchField searchField;
    // End of variables declaration//GEN-END:variables
}
