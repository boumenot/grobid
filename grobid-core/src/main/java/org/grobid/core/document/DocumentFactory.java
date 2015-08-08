package org.grobid.core.document;

import org.grobid.core.exceptions.GrobidException;
import org.grobid.core.features.FeatureTester;
import org.grobid.core.lexicon.Lexicon;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Christopher Boumenot on 1/24/2015.
 */
public class DocumentFactory {
    private final PdfXmlParser parser;
    private final FeatureTester featureTester;
    private final Lexicon lexicon;

    public DocumentFactory(PdfXmlParser parser, FeatureTester featureTester, Lexicon lexicon) {
        this.parser = parser;
        this.featureTester = featureTester;
        this.lexicon = lexicon;
    }

    public Document fromXmlPdf(InputStream inputStream) throws IOException, SAXException, ParserConfigurationException {
        PdfXmlParserResult result = this.parser.parser(inputStream);

        Document doc = new Document(
                result.getBlocks(),
                result.getTokenizations(),
                this.featureTester,
                this.lexicon);

        if (doc.isEmpty()) {
            throw new GrobidException("PDF parsing resulted in empty content");
        }

        return doc;
    }
}
