package Constant;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formdev.flatlaf.FlatLightLaf;

import Components.BoxItem;
import Components.NotFound;
import Components.countCircleShape;
import Components.CustomeUI.CustomScrollBarUI;
import Components.Fonts.WindowFonts;
import feature.LoginAndLogoutForm.model.RoleHasPermissionModel;
import Model.HoldOrder.HoldOrderModel;
import Model.HoldOrder.NewHoldOrderModel;
import Model.ProductModel.ProductDataModel;
import feature.Stock.Products.ProductBox;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.format.DateTimeParseException;
import okhttp3.MediaType;

public class JavaConstant {

     public static String invoiceNo;
     public static String reasonId;
     public static Integer returnerId;
     public static String token;
     public static String fullName;
     public static String userCode;
     public static String posId;
     public static Integer cashierId;
     public static Integer empId;
     public static Long checkCloseShift;
     public static int numberOpenShift = 0;
     public static int productId;
     public static int productQTyLeft;
     public static double discountAmount = 1;
     public static String roleName;
     public static Integer roleId;
     public static String isReturn;
     public static String tmpInvoice;
     public static Integer saleId;
     public static String returnByBarcode;
     public static Integer qtyReturn;
     public static int limit = 21;
     public static int limitPagination = 21;
     public static int page = 0;
     public static int brandId = 0;
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
     public static String removeItem = "You have to remove the produt that has been bought or do the payment first!";
     public static double exchangeRate = 4150;
     public static double exchangeRate4050 = 4050;
     public static String urlImage = "http://103.101.80.108:8082//";
     public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
     public static String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
     public static ArrayList<HoldOrderModel> listHoldData = new ArrayList<>();
     public static ArrayList<NewHoldOrderModel> listHoldOrder = new ArrayList<>();
     public static int indexArrayListHold = 0;
     public static boolean checkOpenShift = false;
     public static String openShiftFirst = "You have to open shift first!";
     public static String byBrandId = "Select By BrandID";
     public static String byCatID = "Select By CatID";
     public static String noResult = "No Result";
     public static int rowNum = 5;
     public static ProductDataModel[] listData;
     public static boolean isCheckProductAll = false;
     public static String categoryName = "new items";
     public static int seconds = 500;
     public static RoleHasPermissionModel.RoleHasPermissionDetail permissionDetail;

     public static void setLookAndFeel() {
          // Set FlatDarkLaf Look and Feel
          try {
               UIManager.setLookAndFeel(new FlatLightLaf());
          } catch (Exception ex) {
               ex.printStackTrace();
          }
     }

     public static boolean checkImageExists(String imageUrl) {
          try {
               URL url = new URL(imageUrl);
               HttpURLConnection connection = (HttpURLConnection) url.openConnection();
               connection.setRequestMethod("HEAD");
               int responseCode = connection.getResponseCode();
               return responseCode == HttpURLConnection.HTTP_OK;
          } catch (Exception e) {
               // If there's an exception, assume the image doesn't exist
               return false;
          }
     }

     public static boolean doesUrlExist(String urlString) { // return true url exist
          try {
               HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
               connection.setRequestMethod("HEAD"); // Use HEAD to check resource existence
               connection.setConnectTimeout(5000); // Timeout for connection
               connection.setReadTimeout(5000); // Timeout for reading
               return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
          } catch (IOException e) {
               // URL does not exist or is unreachable
               return false;
          }
     }

     public static void setResultNotFound(JPanel panelProduct, JPanel panelPagination) {
          panelPagination.setVisible(false);
          JLabel lb = new JLabel("No Product Available!");
          lb.setFont(WindowFonts.timeNewRomanBold16);
          panelProduct.setLayout(new GridBagLayout());
          panelProduct.removeAll();
          panelProduct.revalidate();
          panelProduct.repaint();
          panelProduct.setBorder(new EmptyBorder(10, 0, 0, 0));
          // Set the label to be centered within the panel
          GridBagConstraints constraints = new GridBagConstraints();
          constraints.gridx = 0;
          constraints.gridy = 0;
          constraints.weighty = 1.0; // Expand horizontally
          constraints.anchor = GridBagConstraints.NORTH; // Align to the top
          panelProduct.add(lb, constraints);
     }

     public static void resetValueReturn() {
          JavaConstant.isReturn = null;
          JavaConstant.returnByBarcode = null;
          JavaConstant.qtyReturn = null;
          JavaConstant.tmpInvoice = null;
          JavaConstant.saleId = null;
     }

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

     public static void setPointer(JLabel jLabel) {
          jLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     }

     public static void setPointer(JButton icon) {
          icon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

     public static void resetValuePagination() {
          limit = 21;
          page = 0;
          brandId = 0;
     }

     public static double getReplace(String value) {
          String data = value.replace("$", "");
          data = data.replace(",", "");
          double doubleValue = Double.valueOf(data);
          return doubleValue;
     }

     public static double get4Length(String value) {
          if (value.length() > 4) {
               String data = value.substring(0, 4);
               double d = Double.parseDouble(data);
               return d;
          }
          return Double.parseDouble(value);
     }

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

     public static String getIpAddressPC() throws UnknownHostException {
          InetAddress localHost = InetAddress.getLocalHost();

          // Get the IP address as a string
          String ipAddress = localHost.getHostAddress();
          return ipAddress;
     }

     public static countCircleShape circleShape;
     public static int holdId = 0;
     public static boolean holdSameProduct = false;

     public static String isOpenShift;

     public static void coverImagePath(String url, JLabel lable, int labelWidth, int labelHeight) throws MalformedURLException, IOException {
          Image image = ImageIO.read(new File(url));

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

     public static void filePath(String url, JLabel lable) throws MalformedURLException, IOException {

          File file = new File(url);

          if (file != null) {
               lable.setText(file.getName());
          }
     }

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

     public static boolean onlyDigits(String str) {
          for (int i = 0; i < str.length(); i++) {
               if (str.charAt(i) == '.') {
                    continue;
               }
               if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                    return false;
               }
          }
          return true;
     }

     public void errorResponse(String responseData) throws JsonProcessingException {
          ObjectMapper objMap = new ObjectMapper();
          ErrorResponse data = objMap.readValue(responseData, ErrorResponse.class);
          JOptionPane.showMessageDialog(null, data.getError().getReason());
     }

     public static void addTitleAndLogo(JDialog jDialog, String title) {
          jDialog.setTitle(title);
          //jDialog.setIconImage(new ImageIcon(JavaBlogImage.getImage(JavaRoute.bgImage + "King Mart Small Logo.png")).getImage());
     }

     public static String getCurrentLocalTime() {
          LocalDateTime currentDateTime = LocalDateTime.now();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
          String formattedDateTime = currentDateTime.format(formatter);
          return formattedDateTime;
     }

     public static String getCurrentDate() {
          LocalDate currentDate = LocalDate.now(); // Get only the date
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
          return currentDate.format(formatter);
     }

     public static String formateDateYYYYMMDD(String dateValue) {
          DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
          LocalDate date = LocalDate.parse(dateValue, inputFormatter);
          DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
          return date.format(outputFormatter);
     }

     public static String formateDateDDMMYYYY(String dateValue) {
          if (dateValue == null) {
               return null;
          }
          DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
          LocalDate date = LocalDate.parse(dateValue, inputFormatter);
          DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
          return date.format(outputFormatter);
     }

     public static String getFileName(String name) {
          LocalDateTime currentDateTime = LocalDateTime.now();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH-mm-ss a");
          String formattedDateTime = currentDateTime.format(formatter);
          String fileName = name + " " + formattedDateTime;
          return fileName;
     }

     public static void noContent(JPanel panel) {
          panel.setLayout(new BorderLayout());
          NotFound nofound = new NotFound();
          panel.add(nofound, BorderLayout.CENTER);
          panel.add(nofound);
          panel.revalidate();
          panel.repaint();
     }
//Set Font khmer

     public static boolean isKhmerCharacter(char c) {
          return (c >= '\u1780' && c <= '\u17FF') || (c >= '\u19E0' && c <= '\u19FF');
     }

     // Method to detect if a string contains any Khmer characters
     public static boolean containsKhmer(String text) {
          if (text == null || text.isEmpty()) {
               return false;
          }
          for (char c : text.toCharArray()) {
               if (isKhmerCharacter(c)) {
                    return true;
               }
          }
          return false;
     }

     public static void setScroll(JScrollPane jScrollPane1) {
          // custome scrollbar ui
          jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
          jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
          // custom scroll speed jscrollPane for vertical
          JScrollBar verticalScrollBars = jScrollPane1.getVerticalScrollBar();
          verticalScrollBars.setUnitIncrement(30);
          verticalScrollBars.setBlockIncrement(35);
     }

     public static String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
          + "[a-zA-Z0-9_+&*-]+)*@"
          + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
          + "A-Z]{2,7}$";

     public static String formatPhoneNumber(String number) {
          if (number == null) {
               return null;
          }

          // Remove all non-digit characters
          number = number.replaceAll("\\D", "");

          // Create a StringBuilder to build the formatted phone number
          StringBuilder formattedNumber = new StringBuilder();

          int length = number.length();

          // Check if the number length is 9 (format into three groups of 3 digits)
          if (length == 9) {
               for (int i = 0; i < length; i++) {
                    formattedNumber.append(number.charAt(i));

                    // Insert space after every 3 digits except at the end
                    if ((i + 1) % 3 == 0 && i < length - 1) {
                         formattedNumber.append(" ");
                    }
               }
          } // Check if the number length is 10 (format into three groups of 3 digits, and last group of 4 digits)
          else if (length == 10) {
               for (int i = 0; i < length; i++) {
                    formattedNumber.append(number.charAt(i));

                    // Insert space after every 3 digits, but not before the last 4 digits
                    if ((i + 1) % 3 == 0 && i < length - 4) {
                         formattedNumber.append(" ");
                    }
               }
          }

          return formattedNumber.toString();
     }

     public static String setAmount(BigDecimal amount) {
          if (amount == null) {
               return "";
          }

          // Define the pattern for grouping every 3 digits
          DecimalFormat decimalFormat = new DecimalFormat("#,###.##");

          // Ensure two decimal places are always displayed when there are no decimals
          decimalFormat.setMinimumFractionDigits(2);

          // Ensure it only shows up to two decimal places
          decimalFormat.setMaximumFractionDigits(2);

          // Format the number
          return "$".concat(decimalFormat.format(amount));
     }

     public static String formatDate(String dateValue) {

          // Define two possible input date formats
          DateTimeFormatter inputFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
          DateTimeFormatter inputFormatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

          // Define the output date format ("dd-MM-yyyy")
          DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

          try {
               LocalDate date = null;

               // Try to parse using the first input format ("yyyy-MM-dd")
               try {
                    date = LocalDate.parse(dateValue, inputFormatter1);
               } catch (DateTimeParseException e1) {
                    // If parsing fails, try the second format ("dd-MM-yyyy")
                    try {
                         date = LocalDate.parse(dateValue, inputFormatter2);
                    } catch (DateTimeParseException e2) {
                         // If both formats fail, throw an exception
                         throw new DateTimeParseException("Invalid date format", dateValue, 0);
                    }
               }

               // Convert the LocalDate object to the desired format ("dd-MM-yyyy")
               return date.format(outputFormatter);
          } catch (DateTimeParseException e) {
               System.err.println("Error parsing date: " + e.getMessage());
               return null;  // Return null or handle differently if needed
          }
     }

     public static String getEndDate() {
          // Get current date
          LocalDate currentDate = LocalDate.now();
          String endDate = currentDate.toString();
          return endDate;
     }

     public static String getStartDate() {
          LocalDate currentDate = LocalDate.now();
          // Get the start of the month
          LocalDate startOfMonth = currentDate.withDayOfMonth(1);
          String statDate = startOfMonth.toString();
          return statDate;
     }

}
