package ma.ensat.XML;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

public class Etudiants_Converter {
    public static void main(String[] args) {
        String excelFilePath = "src/Excel_Csv/Etudiants1.xlsx";
        String xmlFilePath = "src/XML/Etudiants.xml";

        try {
            convertExcelToXml(excelFilePath, xmlFilePath);
            System.out.println("Conversion terminée avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void convertExcelToXml(String excelFilePath, String xmlFilePath) throws Exception {
        Workbook workbook = new XSSFWorkbook(new File(excelFilePath));
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        Element rootElement = document.createElement("Etudiants");
        document.appendChild(rootElement);

        Sheet sheet = workbook.getSheetAt(0);

        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // Correction : ne pas soustraire 1
            Row row = sheet.getRow(rowIndex);

            if (row == null) continue;

            Element etudiantElement = document.createElement("Etudiant");
            rootElement.appendChild(etudiantElement);

            // Code Apogee
            Element codeApogeeElement = document.createElement("CodeApogee");
            codeApogeeElement.appendChild(document.createTextNode(getCellValueAsString(row.getCell(0))));
            etudiantElement.appendChild(codeApogeeElement);

            // Nom
            Element nomElement = document.createElement("Nom");
            nomElement.appendChild(document.createTextNode(getCellValueAsString(row.getCell(1))));
            etudiantElement.appendChild(nomElement);

            // Prénom
            Element prenomElement = document.createElement("Prenom");
            prenomElement.appendChild(document.createTextNode(getCellValueAsString(row.getCell(2))));
            etudiantElement.appendChild(prenomElement);

            // CIN
            Element cinElement = document.createElement("CIN");
            cinElement.appendChild(document.createTextNode(getCellValueAsString(row.getCell(4))));
            etudiantElement.appendChild(cinElement);

            // Ajout de la date de naissance
            Element dateNaissanceElement = document.createElement("DateNaissance");
            dateNaissanceElement.appendChild(document.createTextNode(getCellValueAsString(row.getCell(5))));
            etudiantElement.appendChild(dateNaissanceElement);

            // Ajout du lieu de naissance
            Element lieuNaissanceElement = document.createElement("LieuNaissance");
            lieuNaissanceElement.appendChild(document.createTextNode(getCellValueAsString(row.getCell(6))));
            etudiantElement.appendChild(lieuNaissanceElement);
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");

        // Écriture du fichier XML
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new FileOutputStream(xmlFilePath));
        transformer.transform(source, result);

        workbook.close();
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                DecimalFormat df = new DecimalFormat("#");
                return df.format(cell.getNumericCellValue());
            default:
                return "";
        }
    }
}

