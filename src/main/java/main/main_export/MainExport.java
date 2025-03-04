package main.main_export;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import main.man_message.MainAlertMessage;

public class MainExport {

     protected static String downloadFolderPath = System.getProperty("user.home");
     protected static String folderPath = downloadFolderPath + "\\Downloads\\EXCEL_Downloads";

     protected String getFileName(String name) {
          LocalDateTime currentDateTime = LocalDateTime.now();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH-mm-ss a");
          String formattedDateTime = currentDateTime.format(formatter);
          String fileName = name + " " + formattedDateTime;
          return fileName;
     }

     protected static void msgPrint(String path) {
          MainAlertMessage j = new MainAlertMessage(new JFrame(), true);
          j.setIsShow(true);
          j.setMessage("File was saved to path " + path);
          j.setPathOpen(path);
          j.setVisible(true);
     }
}
