package ma.ensat.Xquery;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

public class AttestationReussite {
    public static void main(String[] args) {
        try {
            File xmlfile = new File("src/XML/resultReussit.xml");
            File xsltfile = new File("src/XSL_FO/attestation_de_reussite.xsl");
            File pdfDir = new File("src/Output/PDFS");
            pdfDir.mkdirs();
            File pdfFile = new File(pdfDir, "Attestation_De_Reussite.pdf");
            System.out.println(pdfFile.getAbsolutePath());

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
