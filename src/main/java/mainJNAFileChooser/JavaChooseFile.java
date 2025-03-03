package mainJNAFileChooser;

import com.sun.jna.Library;
import com.sun.jna.Native;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
 

public class JavaChooseFile {

     // Prepare lists to store the file data and responses
     private List<UploadFileResponse> uploadFileResponses = new ArrayList<>();

     public interface User32 extends Library {

          User32 INSTANCE = Native.load("user32", User32.class);

          int MessageBoxW(int hWnd, String lpText, String lpCaption, int uType);
     }

     private List<UploadFileResponse> fileResponses = new ArrayList<>();

     public List<UploadFileResponse> funChooseFiles() throws IOException {
          JFrame frame = new JFrame("File Chooser Example");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(992, 790);
          frame.setPreferredSize(new Dimension(992, 790));

          JFileChooser fileChooser = new JFileChooser();

          // Set the last used directory if available
          if (lastDirectory != null) {
               fileChooser.setCurrentDirectory(lastDirectory);
          }
          fileChooser.setMultiSelectionEnabled(true); // Enable multiple file selection
          fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
          fileChooser.setDialogTitle("Choose Files");

          // Set a file filter for PDF files only
          FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
          fileChooser.setFileFilter(filter);

          int result = fileChooser.showOpenDialog(frame);

          if (result == JFileChooser.APPROVE_OPTION) {
               // Get the selected files
               File[] selectedFiles = fileChooser.getSelectedFiles();

               // Update the last directory to the parent directory of the selected file(s)
               if (selectedFiles.length > 0) {
                    lastDirectory = selectedFiles[0].getParentFile();
               }

               for (File file : selectedFiles) {
                    String originalFileName = file.getName();

                    // Validate file extension
//                    if (!originalFileName.toLowerCase().endsWith(".pdf")) {
//                         JOptionPane.showMessageDialog(
//                              frame,
//                              JavaConstant.PDF_ONLY,
//                              JavaConstant.msg,
//                              JOptionPane.INFORMATION_MESSAGE
//                         );
//                         return new ArrayList<>(); // Return an empty list if any file is invalid
//                    }
                    // Check for duplicate file names
                    boolean exists = fileResponses.stream()
                         .anyMatch(response -> response.getFileName().equals(originalFileName));
                    if (exists) {
                         System.out.println("File already exists: " + originalFileName);
                         continue; // Skip adding this file
                    }

                    // Read file data
                    byte[] fileData = Files.readAllBytes(file.toPath());
                    // Determine the content type
                    String contentType = Files.probeContentType(file.toPath());

                    if (contentType == null) {
                         // If content type cannot be determined, default to "application/octet-stream"
                         contentType = "application/octet-stream";
                    }
                    fileResponses.add(
                         UploadFileResponse.builder()
                              .byteData(fileData)
                              .fileName(originalFileName)
                              .originalName(originalFileName)
                              .contentType(contentType)
                              .build()
                    );

                    System.out.println("Selected file: " + file.getAbsolutePath());
               }
          } else {
               System.out.println("No file selected.");
          }

          return fileResponses;
     }

     private static File lastDirectory = null;  // Store the last directory

     public static File funChooseFile() throws IOException {
          File file = null;
          // Create the JFrame for the file dialog
          JFrame frame = new JFrame("File Chooser Example");
          frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ensure the frame closes properly
          frame.setSize(750, 650);

          // Create and configure the file dialog
          FileDialog fileDialog = new FileDialog(frame, "Choose File", FileDialog.LOAD);
          if (lastDirectory != null) {
               fileDialog.setDirectory(lastDirectory.getAbsolutePath()); // Convert File to String
          }
          fileDialog.setVisible(true);

          String path = null;
          String selectedFile = fileDialog.getFile();
          if (selectedFile != null) {
               String directory = fileDialog.getDirectory();
               lastDirectory = new File(directory);  // Convert String to File
               path = directory + selectedFile;
               System.out.println("Selected File Path: " + path);

               file = new File(path);
 

          } else {
               System.out.println("No file selected.");
          }

          return file;
     }

     public static byte[] getFileBytes(String absolutePath) {
          File file = new File(absolutePath);

          if (file.exists() && file.isFile()) {
               try {
                    return Files.readAllBytes(file.toPath());
               } catch (IOException e) {
                    System.err.println("❌ Error reading file: " + e.getMessage());
               }
          } else {
               System.out.println("❌ File does not exist or is not a valid file.");
          }
          return null;
     }

     public static String uploadFile() throws IOException {
          String lastDirectory = null;
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
