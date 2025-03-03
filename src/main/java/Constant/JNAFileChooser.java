 
package Constant;

import com.sun.jna.Library;
import com.sun.jna.Native;
import java.awt.FileDialog;
import java.io.IOException;
import javax.swing.JFrame;

 
public class JNAFileChooser {

     public interface User32 extends Library {

          User32 INSTANCE = Native.load("user32", User32.class);

          int MessageBoxW(int hWnd, String lpText, String lpCaption, int uType);
     }
     private static String lastDirectory = null;

     public static String funChooseFile() throws IOException {
          // Create the JFrame for the file dialog
          JFrame frame = new JFrame("File Chooser Example");
          frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ensure the frame closes properly
          frame.setSize(750, 650);

          // Create and configure the file dialog
          FileDialog fileDialog = new FileDialog(frame, "Choose File", FileDialog.LOAD);
          if (lastDirectory != null) {
               fileDialog.setDirectory(lastDirectory); // Set the last used directory
          }
          fileDialog.setVisible(true);

          String path = null;
          String selectedFile = fileDialog.getFile();
          if (selectedFile != null) {
               String directory = fileDialog.getDirectory();
               lastDirectory = directory; // Save the selected directory for future use
               path = directory + selectedFile;
               System.out.println("Selected File Path: " + path);
          } else {
               System.out.println("No file selected.");
          }

          return path;
     }
     
     
     
     
}
