package Setting.Country;

import Color.WindowColor;
import Constant.JNAFileChooser;
import Constant.JavaBaseUrl;
import Constant.JavaConnection;
import Constant.JavaConstant;
import Constant.JavaRoute;
import Event.ButtonEvent;
import Model.Country.GetFlagModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

public class AddCountry extends javax.swing.JDialog {

     private Integer id;
     String path;
     String fileName;

     private JPanel listGetCountry;

     public AddCountry(java.awt.Frame parent, boolean modal) {
          super(parent, modal);
          initComponents();
          txtCountry.requestFocus();
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setResizable(false);

          browse.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, WindowColor.darkBlue));
          event();
     }

     //Place Holder
     void event() {
          ButtonEvent btnevent = new ButtonEvent() {
               @Override
               public void onFocusGain() {

               }
          };
          txtCountry.initEvent(btnevent);
     }

     //Value Edit
     public void setValueEdit(
          String country,
          String urlImg,
          String uuid
     ) throws IOException {
          txtCountry.setValueTextField(country);
          JavaConstant.coverImage(urlImg, lbFile, 150, 135);
          fileName = uuid;
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
        label3 = new Components.Label();
        jLabel12 = new javax.swing.JLabel();
        txtCountry = new Components.TextField();
        lbFile = new javax.swing.JLabel();

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

        label3.setLabelName("Country Name");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setText("*");

        txtCountry.setLabelTextField("Country Name");

        lbFile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePopUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(browse)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCountry, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbFile, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(titlePopUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCountry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbFile, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(browse, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
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
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSaveMouseClicked

         String countryName = txtCountry.getValueTextField();

         if (countryName == null || countryName.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Country Name can not be empty!");
              return;
         }

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

         try {

              JSONObject json = new JSONObject();
              json.put("countryName", countryName);
              json.put("uuid", fileName);

              System.out.println("fileName : " + fileName);

              if (id != null) {
                   Response response = JavaConnection.put(JavaRoute.country + "/" + id, json);

                   System.out.println("response : " + response);
                   System.out.println("json : " + json);

                   if (response.isSuccessful()) {
                        ListCountry list = new ListCountry(new JFrame(), true);
                        listGetCountry.removeAll();
                        listGetCountry.revalidate();
                        listGetCountry.repaint();
                        list.getListCountry(listGetCountry);
                        dispose();
                   } else {
                        JOptionPane.showMessageDialog(this, "Save Failed!");
                   }
              } else {
                   json.put("createBy", JavaConstant.cashierId);
                   Response response = JavaConnection.post(JavaRoute.country, json);

                   if (response.isSuccessful()) {
                        ListCountry list = new ListCountry(new JFrame(), true);
                        listGetCountry.removeAll();
                        listGetCountry.revalidate();
                        listGetCountry.repaint();
                        list.getListCountry(listGetCountry);
                        dispose();
                   } else {
                        JOptionPane.showMessageDialog(this, "Save Failed!");
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
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private Components.Label label1;
    private Components.Label label3;
    private javax.swing.JLabel lbFile;
    private Components.LabelPopUpTitle titlePopUp;
    private Components.TextField txtCountry;
    // End of variables declaration//GEN-END:variables
}
