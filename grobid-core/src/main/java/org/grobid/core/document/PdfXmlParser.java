package org.grobid.core.document;

import org.grobid.core.exceptions.GrobidException;
import org.grobid.core.sax.PDF2XMLSaxParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public class PdfXmlParser {

    /**  Parse PDF2XML output representation and get the tokenized form of the document.
     */
    public PdfXmlParserResult parser(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        PdfXmlParserResult result = new PdfXmlParserResult();

        PDF2XMLSaxParser parser = new PDF2XMLSaxParser(
                result.getBlocks(),
                result.getImages());

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser p = spf.newSAXParser();

        try {
            p.parse(inputStream, parser);
            result.setTokenizations(parser.getTokenization());

            return result;
        } catch (Exception e) {
            throw new GrobidException("An exception occurred.", e);
        } finally {
            inputStream.close();
        }
    }
}
