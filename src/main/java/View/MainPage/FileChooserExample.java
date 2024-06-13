/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.MainPage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileChooserExample {
     // create folder in specific path

     public static String downloadFolderPath = System.getProperty("user.home");
     public static String folderPath = downloadFolderPath + "\\Downloads\\EXCEL_Downloads";

     public static void exportToCSV(List<String[]> data, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String[] row : data) {
                StringBuilder rowString = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    rowString.append(row[i]);
                    if (i < row.length - 1) {
                        rowString.append(",");
                    }
                }
                writer.write(rowString.toString());
                writer.newLine();
            }
            System.out.println("CSV file exported successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example data: a 2x2 matrix
        List<String[]> data = List.of(
                new String[]{"Name", "Age", "City"},
                new String[]{"John", "30", "New York"},
                new String[]{"Alice", "25", "Los Angeles"}
        );

        // Export the data to a CSV file
        exportToCSV(data, "output.csv");
    }
}
