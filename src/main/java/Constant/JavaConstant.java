package Constant;

import Components.BoxItem;
import Components.countCircleShape;
import Model.HoldOrder.HoldOrderModel;
import Model.ProductModel.ProductDataModel;
import Model.HoldOrder.NewHoldOrderModel;
import Products.ProductBox;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import okhttp3.MediaType;
import org.w3c.dom.events.DocumentEvent;

public class JavaConstant {

     public static String insertComma(String str) {
          StringBuilder sb = new StringBuilder(str);
          int length = sb.length();
          // Start from the end of the string and insert comma every 3 characters
          for (int i = length - 3; i > 0; i -= 3) {
               sb.insert(i, ",");
          }
          return sb.toString();
     }

     public static void setCircleLoadingCursor(Component component) {
          component.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     }

     public static void restoreDefaultCursor(Component component) {
          component.setCursor(Cursor.getDefaultCursor());
     }

     public static String urlImage = "http://103.101.80.108:8082//";

     public static void setPointer(JLabel jLabel) {
          jLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     }

     public static void setPointer(JTextField txt) {
          txt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     }

     public static void setPointer(JComboBox cmd) {
          cmd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     }

     public static void setPointer(JScrollPane jb) {
          jb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     }

     public static void setPointer(JPanel jPanel) {
          jPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     }

     public static String invoiceNo;
     public static String reasonId;
     public static Integer returnerId;
     public static String token;
     public static String fullName;
     public static String userCode;
     public static String posId;
     public static Integer cashierId;
     public static Long checkCloseShift;
     public static int numberOpenShift = 0;
     public static int productId;
     public static int productQTyLeft;
     public static double discountAmount = 1;
//     public static Component[] listHoldData;
     public static String roleName;
     public static String isReturn;
     public static String returnByBarcode;
     public static Integer qtyReturn;

     public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
     public static String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

     public static String typeCash = "cash";
     public static String typeCredit = "credit";
     public static String typeQRaba = "aba";
     public static String typeQRmnk = "mnk";
     public static String typeExpress = "express";
     public static String titleClose = "Close";
     public static String success = "success";
     public static String closeShift = "Close Shift";
     public static String supervisor = "Supervisor";
     public static String admin = "Admin";

     public static double exchangeRate = 4150;
     public static double exchangeRate4050 = 4050;

     public static double getReplace(String value) {
          String data = value.replace("$", "");
          data = data.replace(",", "");
          double doubleValue = Double.valueOf(data);
          return doubleValue;
     }

     public static ArrayList<HoldOrderModel> listHoldData = new ArrayList<>();
     public static ArrayList<NewHoldOrderModel> listHoldOrder = new ArrayList<>();
     public static int indexArrayListHold = 0;
     public static boolean checkOpenShift = false;
     public static String openShiftFirst = "You have to open shift first!";

     public static double get4Length(String value) {
          if (value.length() > 4) {
               String data = value.substring(0, 4);
               double d = Double.parseDouble(data);
               return d;
          }
          return Double.parseDouble(value);
     }

     public static String byBrandId = "Select By BrandID";
     public static String byCatID = "Select By CatID";
     public static String noResult = "No Result";
     public static int rowNum = 5;
     public static ProductDataModel[] listData;
     public static boolean isCheckProductAll = false;

     public static String getDeviceName() {
          Map<String, String> env = System.getenv();
          if (env.containsKey("COMPUTERNAME")) {
               return env.get("COMPUTERNAME");
          } else if (env.containsKey("HOSTNAME")) {
               return env.get("HOSTNAME");
          } else {
               return "Unknown";
          }
     }

     public static countCircleShape circleShape;
     public static int holdId = 0;
     public static boolean holdSameProduct = false;

     public static String isOpenShift;

     public static void coverImage(String url, JLabel lable, int labelWidth, int labelHeight) throws MalformedURLException, IOException {
          URL imageUrl = new URL(url);

          Image image = ImageIO.read(imageUrl);

          if (image != null) {
               // Get dimensions of the JLabel
//               int labelWidth = 160;
//               int labelHeight = 105;

               // Calculate the scale factor
               double scaleX = (double) labelWidth / image.getWidth(null);
               double scaleY = (double) labelHeight / image.getHeight(null);
               double scale = Math.min(scaleX, scaleY);

               // Scale the image
               int scaledWidth = (int) (image.getWidth(null) * scale);
               int scaledHeight = (int) (image.getHeight(null) * scale);
               Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
               ImageIcon icon = new ImageIcon(scaledImage);
               lable.setIcon(icon);
          }
     }

     public static void setCommaIn3Length(JTextField txtText) {
          ((AbstractDocument) txtText.getDocument()).setDocumentFilter(new DocumentFilter() {
               @Override
               public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                    StringBuilder builder = new StringBuilder(string.replaceAll(",", ""));
                    for (int i = builder.length() - 3; i > 0; i -= 3) {
                         builder.insert(i, ",");
                    }
                    super.insertString(fb, offset, builder.toString(), attr);
               }

               @Override
               public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    if (text == null) {
                         super.replace(fb, offset, length, text, attrs);
                         return;
                    }
                    StringBuilder builder = new StringBuilder(text.replaceAll(",", ""));
                    for (int i = builder.length() - 3; i > 0; i -= 3) {
                         builder.insert(i, ",");
                    }
                    super.replace(fb, offset, length, builder.toString(), attrs);
               }
          });
     }

     public static void setBackQty(JPanel detailItem, JPanel panelProduct) {
          Component[] listDetailItem = detailItem.getComponents();
          Component[] listPanelProduct = panelProduct.getComponents();

          for (Component c : listDetailItem) {
               var data = ((BoxItem) c);

               int saleQty = data.getQty();

               for (Component cc : listPanelProduct) {

                    var pro = ((ProductBox) cc);

                    int qty = Integer.parseInt(pro.getQty());

                    if (data.getLabelBarcode().equals(pro.getBarcode())) {
                         qty = qty + saleQty;
                         pro.setQty("" + qty);
                         pro.setProductStatus(JavaMessage.inStock);
                         break;
                    }

               }

          }
     }

     public static String removeItem = "You have to remove the produt that has been bought or do the payment first!";

    

}
