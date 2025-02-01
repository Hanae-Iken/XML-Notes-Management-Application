package ma.ensat.PDF_GENERATORS;

import org.apache.fop.apps.*;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class AttestationReussite {
    public static void main(String[] args) {
        try {
            File xmlfile = new File("src/XML/resultReussit.xml");
            File xsltfile = new File("src/XSL_FO/attestation_de_reussite.xsl");
            File pdfDir = new File("src/Output/PDFS");
            pdfDir.mkdirs();
            File pdfFile = new File(pdfDir, "Attestation_De_Reussite.pdf");
            System.out.println(pdfFile.getAbsolutePath());

            // Initialisation de FOP
            final FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            OutputStream out = new FileOutputStream(pdfFile);
            out = new java.io.BufferedOutputStream(out);

            try {
                Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
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
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }
}