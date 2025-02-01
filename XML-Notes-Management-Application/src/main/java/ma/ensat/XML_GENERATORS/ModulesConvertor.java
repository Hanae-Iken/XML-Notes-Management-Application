package ma.ensat.XML_GENERATORS;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.IOException;

public class ModulesConvertor {
    static void converter() {
        try {
            FileInputStream file = new FileInputStream(new File("src/Excel_Csv/Modules.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);  // Assurez-vous de choisir la bonne feuille

            File myWriter = new File("src/XML/Modules.xml");
            myWriter.createNewFile();
            FileWriter output = new FileWriter(myWriter);
            output.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?> \n");
            output.write("<Modules>\n");

            // Itérer à travers les lignes du fichier Excel
            for (int i = 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String codeModule = getCellValue(row.getCell(0));
                String nomModule = getCellValue(row.getCell(1));
                String elementModule1 = getCellValue(row.getCell(2));
                String elementModule2 = getCellValue(row.getCell(3));
                String elementModule3 = getCellValue(row.getCell(4));
                String deptAttachement = getCellValue(row.getCell(5));

                if (codeModule.isEmpty() || nomModule.isEmpty()) continue;  // Ignorer les lignes vides ou mal formées

                output.write("\t<Module CodeModule=\"" + codeModule + "\">\n");
                output.write("\t\t<NomModule>" + nomModule + "</NomModule>\n");
                output.write("\t\t<ElementModule>" + elementModule1 + "</ElementModule>\n");
                output.write("\t\t<ElementModule>" + elementModule2 + "</ElementModule>\n");
                output.write("\t\t<ElementModule>" + elementModule3 + "</ElementModule>\n");
                output.write("\t\t<Dept_Attachement>" + deptAttachement + "</Dept_Attachement>\n");
                output.write("\t</Module>\n");
            }

            output.write("</Modules>\n");
            System.out.println("Génération faite avec succès");
            output.close();
            workbook.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    // Méthode pour obtenir la valeur d'une cellule en tant que String
    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    public static void main(String... args) {
        converter();
    }
}
