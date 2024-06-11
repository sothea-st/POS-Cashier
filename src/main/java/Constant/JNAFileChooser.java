/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Constant;

import com.sun.jna.Library;
import com.sun.jna.Native;
import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author MOBILE-APP.02
 */
public class JNAFileChooser {

     public interface User32 extends Library {

          User32 INSTANCE = Native.load("user32", User32.class);

          int MessageBoxW(int hWnd, String lpText, String lpCaption, int uType);
     }

     public static String funChooseFile() throws IOException {
          JFrame frame = new JFrame("File Chooser Example");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(750, 650);
          FileDialog fileDialog = new FileDialog(frame, "Choose File", FileDialog.LOAD);
          fileDialog.setVisible(true);
          String path=null;
          String selectedFile = fileDialog.getFile();
          if (selectedFile != null) {
               String directory = fileDialog.getDirectory();
               String filePath = directory + selectedFile;
               // Now you have the absolute path of the selected file, you can further process it if needed
               File file = new File(filePath);
               path = file.getAbsolutePath();
//               JavaConstant.coverImagePath(path, lbPicture, 124, 235);

          } else {
               System.out.println("No file selected.");
          }

          return path;
     }
}
