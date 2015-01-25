package org.grobid.core.document;

import org.grobid.core.exceptions.GrobidException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Christopher Boumenot on 1/24/2015.
 */
public class DocumentFactory {
    private final PdfXmlParser parser;

    public DocumentFactory(PdfXmlParser parser) {
        this.parser = parser;
    }

    public Document fromXmlPdf(InputStream inputStream) throws IOException, SAXException, ParserConfigurationException {
        PdfXmlParserResult result = this.parser.parser(inputStream);

        Document doc = new Document(
                result.getBlocks(),
                result.getTokenizations());

        if (doc.isEmpty()) {
            throw new GrobidException("PDF parsing resulted in empty content");
        }

        return doc;
    }
}
