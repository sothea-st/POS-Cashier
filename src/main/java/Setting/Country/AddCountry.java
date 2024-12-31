package Setting.Country;

import Color.WindowColor;
import Constant.JNAFileChooser;
import Constant.JavaBaseUrl;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Model.Country.GetFlagModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.Setter;
import main_validation.JavaConflicValidation;
import main_validation.JavaValidation;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

@Setter
@Getter

public class AddCountry extends javax.swing.JDialog {

    private Integer id;
    String path;
    String fileName;
    private String pageNumber;
    private ListCountry obj;
    private JPanel listGetCountry;

    public AddCountry(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtCountry.requestFocus();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        browse.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, WindowColor.darkBlue));

//        try {
//            JavaConstant.coverImage(JavaBaseUrl.baseUrlDefaultImageStaff, lbFile, 150, 135);
//        } catch (IOException ex) {
//            Logger.getLogger(InsertStaff.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    //Value Edit
    public void setValueEdit(
            String country,
            //          String urlImg,
            String uuid
    ) throws IOException {
        txtCountry.setText(country);

        if (uuid != null) {
            String _url = new JavaBaseUrl().getBaseUrl() + JavaRoute.bgImage + uuid;

            // Check if the URL exists
            if (JavaConstant.doesUrlExist(_url)) { // return true url exist
                JavaConstant.coverImage(_url, lbFile, 150, 135);
                fileName = uuid;
            }  
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        titlePopUp = new Components.LabelPopUpTitle();
        label1 = new Components.Label();
        browse = new javax.swing.JLabel();
        buttonCancel = new ButtonPackage.ButtonCancel();
        buttonSave = new ButtonPackage.ButtonSave();
        lbFile = new javax.swing.JLabel();
        txtCountry = new FormComponent.JavaTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titlePopUp.setLabelTitle("Add Country");

        label1.setLabelName("Image");

        browse.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        browse.setForeground(new java.awt.Color(0, 51, 102));
        browse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        browse.setText("Browse here to Upload");
        browse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                browseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                browseMouseExited(evt);
            }
        });

        buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancelMouseClicked(evt);
            }
        });

        buttonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSaveMouseClicked(evt);
            }
        });

        lbFile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user_default.jpg"))); // NOI18N
        lbFile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtCountry.setLabelName("Country Name *");
        txtCountry.setPlaceHolder("Country Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(browse, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFile, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titlePopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbFile, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(browse, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
        this.dispose();
        ListCountry list = new ListCountry(new JFrame(), true);
        list.setVisible(true);
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked

        String countryName = txtCountry.getValueTextField();

        try {

            boolean isCheck = JavaValidation.checkValidation(jPanel1);
            if (isCheck) {
                if (path != null) {

                    String url = new JavaBaseUrl().getBaseUrl() + JavaRoute.addBackground;
                    OkHttpClient client = new OkHttpClient();

                    MultipartBody.Builder requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM);

                    File fileToUpload = new File(path);
                    requestBody.addFormDataPart("file", fileToUpload.getName(),
                            RequestBody.create(MediaType.parse("image/jpeg"), fileToUpload));

                    // Request
                    Request request = new Request.Builder()
                            .url(url)
                            .post(requestBody.build())
                            .header("Authorization", "Bearer " + JavaConstant.token)
                            .build();

                    try {
                        Response response = client.newCall(request).execute();

                        String responseData = response.body().string();
                        ObjectMapper objMap = new ObjectMapper();
                        GetFlagModel data = objMap.readValue(responseData, GetFlagModel.class);

                        if (response.isSuccessful()) {
                            fileName = data.getFileName();
                        }
                        // Do something with the response.
                    } catch (IOException e) {
                        System.out.println("err = " + e);
                    }
                }

                JSONObject json = new JSONObject();
                json.put("countryName", countryName);
                json.put("uuid", fileName);

                // create response 
                Response response = null;
                if (id != null) { // add new
                    response = JavaConnection.put(JavaRoute.country + '/' + id, json);
                } else { // update 
                    json.put("createBy", JavaConstant.cashierId);
                    response = JavaConnection.post(JavaRoute.country, json);
                }

                // check if name already exist
                List<JavaConflicValidation> fields = new ArrayList<>();

                fields.add(JavaConflicValidation.builder()
                        .key("Name") // specific word that exist in key "reason"
                        .msg("This name is already existed!") // message to show 
                        .field(txtCountry) // obj of JavaTextField
                        .build());

                /* 
                        isExist = true ( name not yet used )
                        isExist =  false ( name already used )
                 */
                boolean isExist = JavaValidation.checkNameExist(response, fields);

                try {
                    if (response.isSuccessful() && isExist) {
                        dispose();
                        ListCountry list = new ListCountry(new JFrame(), true);
                        list.setVisible(true);
                        listGetCountry.removeAll();
                        listGetCountry.revalidate();
                        listGetCountry.repaint();
                        list.getListCountry(listGetCountry, true, pageNumber);
                    }

                } catch (Exception e) {
                    System.err.println("error post country : " + e);
                }
            }

        } catch (Exception e) {
            System.err.println("errr -- " + e);
        }

    }//GEN-LAST:event_buttonSaveMouseClicked

    private void browseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseMouseClicked
        try {
            path = JNAFileChooser.funChooseFile();
            JavaConstant.coverImagePath(path, lbFile, 124, 235);
        } catch (IOException ex) {
            Logger.getLogger(AddCountry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_browseMouseClicked

    private void browseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseMouseEntered
        browse.setForeground(WindowColor.light_Blue);
        browse.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, WindowColor.light_Blue));
    }//GEN-LAST:event_browseMouseEntered

    private void browseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseMouseExited
        browse.setForeground(WindowColor.darkBlue);
        browse.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, WindowColor.darkBlue));
    }//GEN-LAST:event_browseMouseExited

    public JPanel getListGetCountry() {
        return listGetCountry;
    }

    public void setListGetCountry(JPanel listGetCountry) {
        this.listGetCountry = listGetCountry;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        titlePopUp.setLabelTitle("Edit Country");
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
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
            java.util.logging.Logger.getLogger(AddCountry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCountry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCountry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCountry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddCountry dialog = new AddCountry(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel browse;
    private ButtonPackage.ButtonCancel buttonCancel;
    private ButtonPackage.ButtonSave buttonSave;
    private javax.swing.JPanel jPanel1;
    private Components.Label label1;
    private javax.swing.JLabel lbFile;
    private Components.LabelPopUpTitle titlePopUp;
    private FormComponent.JavaTextField txtCountry;
    // End of variables declaration//GEN-END:variables
}
