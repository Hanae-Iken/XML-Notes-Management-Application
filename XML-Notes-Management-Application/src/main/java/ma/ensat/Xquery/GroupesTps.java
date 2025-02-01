package ma.ensat.Xquery;

import java.io.File;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class GroupesTps {
    public static void main(String[] args) throws Exception {

        String fichierGroupes = "src/XML/Groupes.xml";
        String fichierXSL = "src/XSLT/GroupesTps.xsl";
        String fichierHTML = "src/Output/HTML/GroupesTps.html";

        String xmlGroupes = new String(Files.readAllBytes(Paths.get(fichierGroupes)));

        Files.write(Paths.get(fichierGroupes), xmlGroupes.getBytes());

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(new File(fichierXSL)));
        transformer.transform(new StreamSource(new StringReader(xmlGroupes)), new StreamResult(new File(fichierHTML)));

        System.out.println("Fichier HTML généré avec succès : " + fichierHTML);
    }
}
