/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.MainPage;

import com.sun.jna.Library;
import com.sun.jna.Native;
import java.awt.FileDialog;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class FileChooserExample {

 
     public interface User32 extends Library {

          User32 INSTANCE = Native.load("user32", User32.class);

          int MessageBoxW(int hWnd, String lpText, String lpCaption, int uType);
     }

     public static void main(String[] args) {
          SwingUtilities.invokeLater(() -> {
               JFrame frame = new JFrame("File Chooser Example");
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

               FileDialog fileDialog = new FileDialog(frame, "Choose File", FileDialog.LOAD);
               fileDialog.setVisible(true);

               String selectedFile = fileDialog.getFile();
               if (selectedFile != null) {
                    String directory = fileDialog.getDirectory();
                    String filePath = directory + selectedFile;
                    System.out.println("Selected file: " + filePath);

                    // Now you have the absolute path of the selected file, you can further process it if needed
                    File file = new File(filePath);
                    String absolutePath = file.getAbsolutePath();
                    System.out.println("Absolute Path: " + absolutePath);
               } else {
                    System.out.println("No file selected.");
               }

               frame.pack();
               frame.setVisible(true);
          });
     }
}
