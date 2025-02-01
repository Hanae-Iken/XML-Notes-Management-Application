package ma.ensat.HTML_GENERATORS;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class AffichageGlobale {
    public static void main(String[] args) {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            StreamSource xslDoc = new StreamSource("src/XSLT/AffichageGlobale.xsl");
            StreamSource xmlDoc = new StreamSource("src/XML/Notes.xml");
            String outputFileName = "src/Output/HTML/affichage_globale.html";
            OutputStream htmlFile = new FileOutputStream(outputFileName);
            Transformer transform = tFactory.newTransformer(xslDoc);
            System.out.println("HTML file for Affichage Globale generated successfully");
            transform.transform(xmlDoc, new StreamResult(htmlFile));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
            System.err.println("Error configuring transformer: " + e.getMessage());
        } catch (TransformerException e) {
            System.err.println("Transformation error: " + e.getMessage());
        }
    }
}
