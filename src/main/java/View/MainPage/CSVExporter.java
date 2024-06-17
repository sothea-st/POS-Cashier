/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.MainPage;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
 
public class CSVExporter {
       public static void exportToCSV(String filePath, String fileName, ArrayList<ProductModel> listProduct) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath + "\\" + fileName + ".csv"))) {

            // Write headers
            writer.writeNext(new String[]{"Product Name", "Barcode", "Price", "Quantity", "Status"});

            // Write data
            for (ProductModel p : listProduct) {
                String[] data = new String[]{
                        p.getProductNameEn(),
                        "\"" + p.getBarcode() + "\"", // Enclose barcode in quotes
                        String.valueOf(p.getPrice()),
                        String.valueOf(p.getQty()),
                        p.getProductStatus()
                };
                writer.writeNext(data);
            }

            System.out.println("CSV file exported successfully to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArrayList<ProductModel> listProduct = new ArrayList<>();
        listProduct.add(new ProductModel("Product A", "8850585992496", 10.99, 100, "Active"));
        listProduct.add(new ProductModel("Product B", "1234567890123", 5.99, 50, "Inactive"));

        String filePath = "C:\\Users\\mobile-app.02\\Downloads\\CSV_Downloads";
        String fileName = "products";

        exportToCSV(filePath, fileName, listProduct);
    }

    // Define ProductModel class according to your structure
    static class ProductModel {
        private String productNameEn;
        private String barcode;
        private double price;
        private int qty;
        private String productStatus;

        public ProductModel(String productNameEn, String barcode, double price, int qty, String productStatus) {
            this.productNameEn = productNameEn;
            this.barcode = barcode;
            this.price = price;
            this.qty = qty;
            this.productStatus = productStatus;
        }

        public String getProductNameEn() {
            return productNameEn;
        }

        public String getBarcode() {
            return barcode;
        }

        public double getPrice() {
            return price;
        }

        public int getQty() {
            return qty;
        }

        public String getProductStatus() {
            return productStatus;
        }
    }
}
