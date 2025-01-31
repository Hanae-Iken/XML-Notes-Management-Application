package ma.ensat.PDF_GENERATORS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
public class CarteRecto {
    public static void main(String[] args) throws FileNotFoundException, TransformerConfigurationException {
        try {
            File xmlfile = new File("src/XML/Etudiants.xml");
            File xsltfile = new File("src/XSL_FO/Carte etudiant.xsl");
            File pdfDir = new File("src/Output/PDFS");
            pdfDir.mkdirs();
            File pdfFile = new File(pdfDir,"Recto2.pdf");
            System.out.println(pdfFile.getAbsolutePath());
            final FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            OutputStream out = new FileOutputStream(pdfFile);
            out = new java.io.BufferedOutputStream(out);
            try {
                Fop fop;
                fop = fopFactory.newFop(MimeConstants.MIME_PDF,foUserAgent,out);
                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer transformer = factory.newTransformer(new StreamSource(xsltfile));
                Source src = new StreamSource(xmlfile);
                Result res = new SAXResult(fop.getDefaultHandler());
                transformer.transform(src, res);
            } catch (FOPException | TransformerException e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
        }catch(IOException exp){
            exp.printStackTrace();
        }
    }

}
