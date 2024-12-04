
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelComboBoxExample {

     public static void main(String[] args) {
        // ComboBox values that you want to export to Excel, including the placeholder
        String[] comboBoxValues = {"---select---", "Option 1", "Option 2", "Option 3", "Option 4"};

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Data");

            // Add a header row for context
            XSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Select an Option:");

            // Add ComboBox (Dropdown) to a range of cells (e.g., B2 to B10)
            addComboBox(sheet, comboBoxValues, 1, 1, 10);  // Apply dropdown in cells B2 to B10

            // Save to file
            try (FileOutputStream fos = new FileOutputStream("ComboBoxExampleWithPlaceholder.xlsx")) {
                workbook.write(fos);
                System.out.println("Excel file with ComboBox created successfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    
    private static void addComboBox(XSSFSheet sheet, String[] values, int startRow, int startCol, int endRow) {
        // Define the cell range where the ComboBox will appear
        CellRangeAddressList addressList = new CellRangeAddressList(startRow, endRow, startCol, startCol);

        // Create a data validation helper
        XSSFDataValidationHelper validationHelper = new XSSFDataValidationHelper(sheet);

        // Create the data validation constraint with the comboBoxValues array
        XSSFDataValidationConstraint constraint = (XSSFDataValidationConstraint)
                validationHelper.createExplicitListConstraint(values);

        // Create the data validation and apply it to the range
        XSSFDataValidation validation = (XSSFDataValidation) validationHelper.createValidation(constraint, addressList);

        // Optionally, show an error box if an invalid value is entered
        validation.setShowErrorBox(true);

        // Apply validation to the sheet
        sheet.addValidationData(validation);
    }
}
