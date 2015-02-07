package org.grobid.core.engines;

import org.grobid.core.GrobidModelStreamFactory;
import org.grobid.core.document.DocumentFactory;
import org.grobid.core.document.PdfXmlParser;
import org.grobid.core.engines.entities.ChemicalParser;
import org.grobid.core.engines.patent.ReferenceExtractor;
import org.grobid.core.process.PdfToXmlCmdFactory;
import org.grobid.core.process.PdfToXmlConverter;
import org.grobid.core.process.PdfToXmlConverterImpl;
import org.grobid.core.utilities.GrobidProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Slava
 * Date: 4/15/14
 */
public class EngineParsers implements Closeable {
    public static final Logger LOGGER = LoggerFactory.getLogger(EngineParsers.class);
    private final PdfToXmlConverter pdfToXmlConverter;
    private final DocumentFactory documentFactory;
    private final GrobidModelStreamFactory grobidModelStreamFactory;

    private AuthorParser authorParser = null;
    private AffiliationAddressParser affiliationAddressParser = null;
    private HeaderParser headerParser = null;
    private DateParser dateParser = null;
    private CitationParser citationParser = null;
    private FullTextParser fullTextParser = null;
    private ReferenceExtractor referenceExtractor = null;
    private ChemicalParser chemicalParser = null;
    private Segmentation segmentationParser = null;
    private ReferenceSegmenterParser referenceSegmenterParser = null;

    public static EngineParsers Create()
    {
        GrobidProperties.getInstance();

        return EngineParsers.Create(
                new PdfToXmlConverterImpl(
                        new PdfToXmlCmdFactory(GrobidProperties.getPdf2XMLPath()),
                        GrobidProperties.getTempPath()),
                new DocumentFactory(
                        new PdfXmlParser()),
                new GrobidModelStreamFactory());
    }

    public static EngineParsers Create(
            PdfToXmlConverter pdfToXmlConverter,
            DocumentFactory documentFactory,
            GrobidModelStreamFactory grobidModelStreamFactory) {
        return new EngineParsers(pdfToXmlConverter, documentFactory, grobidModelStreamFactory);
    }

    private EngineParsers(
            PdfToXmlConverter pdfToXmlConverter,
            DocumentFactory documentFactory,
            GrobidModelStreamFactory grobidModelStreamFactory) {
        this.pdfToXmlConverter = pdfToXmlConverter;
        this.documentFactory = documentFactory;
        this.grobidModelStreamFactory = grobidModelStreamFactory;
    }

    public AffiliationAddressParser getAffiliationAddressParser() {
        if (affiliationAddressParser == null) {
            synchronized (this) {
                if (affiliationAddressParser == null) {
                    affiliationAddressParser = new AffiliationAddressParser(this.grobidModelStreamFactory);
                }
            }
        }
        return affiliationAddressParser;
    }

    public AuthorParser getAuthorParser() {
        if (authorParser == null) {
            synchronized (this) {
                if (authorParser == null) {
                    authorParser = new AuthorParser(this.grobidModelStreamFactory);
                }
            }
        }
        return authorParser;
    }

    public HeaderParser getHeaderParser() {
        if (headerParser == null) {
            synchronized (this) {
                if (headerParser == null) {
                    headerParser = new HeaderParser(
                            this,
                            this.pdfToXmlConverter,
                            this.documentFactory,
                            this.grobidModelStreamFactory);
                }
            }
        }
        return headerParser;
    }

    public DateParser getDateParser() {
        if (dateParser == null) {
            synchronized (this) {
                if (dateParser == null) {
                    dateParser = new DateParser(this.grobidModelStreamFactory);
                }
            }
        }
        return dateParser;
    }

    public CitationParser getCitationParser() {
        if (citationParser == null) {
            synchronized (this) {
                if (citationParser == null) {
                    citationParser = new CitationParser(this, this.grobidModelStreamFactory);
                }
            }
        }

        return citationParser;
    }

    public FullTextParser getFullTextParser() {
        if (fullTextParser == null) {
            synchronized (this) {
                if (fullTextParser == null) {
                    fullTextParser = new FullTextParser(this, this.grobidModelStreamFactory);
                }
            }
        }

        return fullTextParser;
    }

    public Segmentation getSegmentationParser() {
        if (segmentationParser == null) {
            synchronized (this) {
                if (segmentationParser == null) {
                    segmentationParser = new Segmentation(
                            this.pdfToXmlConverter,
                            this.documentFactory,
                            this.grobidModelStreamFactory);
                }
            }
        }
        return segmentationParser;
    }

    public ReferenceExtractor getReferenceExtractor() {
        if (referenceExtractor == null) {
            synchronized (this) {
                if (referenceExtractor == null) {
                    referenceExtractor = new ReferenceExtractor(
                            this,
                            this.pdfToXmlConverter,
                            this.documentFactory,
                            this.grobidModelStreamFactory);
                }
            }
        }
        return referenceExtractor;
    }

    public ReferenceSegmenterParser getReferenceSegmenterParser() {
        if (referenceSegmenterParser == null) {
            synchronized (this) {
                if (referenceSegmenterParser == null) {
                    referenceSegmenterParser = new ReferenceSegmenterParser(this.grobidModelStreamFactory);
                }
            }
        }
        return referenceSegmenterParser;
    }

    public ChemicalParser getChemicalParser() {
        if (chemicalParser == null) {
            synchronized (this) {
                if (chemicalParser == null) {
                    chemicalParser = new ChemicalParser(this.grobidModelStreamFactory);
                }
            }
        }
        return chemicalParser;
    }

    @Override
    public void close() throws IOException {
        LOGGER.debug("==> Closing all resources...");
        if (authorParser != null) {
            authorParser.close();
            authorParser = null;
            LOGGER.debug("CLOSING authorParser");
        }
        if (affiliationAddressParser != null) {
            affiliationAddressParser.close();
            affiliationAddressParser = null;
            LOGGER.debug("CLOSING affiliationAddressParser");
        }

        if (headerParser != null) {
            headerParser.close();
            headerParser = null;
            LOGGER.debug("CLOSING headerParser");
        }

        if (dateParser != null) {
            dateParser.close();
            dateParser = null;
            LOGGER.debug("CLOSING dateParser");
        }

        if (citationParser != null) {
            citationParser.close();
            citationParser = null;
            LOGGER.debug("CLOSING citationParser");
        }

        if (fullTextParser != null) {
            fullTextParser.close();
            fullTextParser = null;
            LOGGER.debug("CLOSING fullTextParser");
        }

        if (referenceExtractor != null) {
            referenceExtractor.close();
            referenceExtractor = null;
            LOGGER.debug("CLOSING referenceExtractor");
        }

        if (chemicalParser != null) {
            chemicalParser.close();
            chemicalParser = null;
            LOGGER.debug("CLOSING chemicalParser");
        }

        if (segmentationParser != null) {
            segmentationParser.close();
            segmentationParser = null;
            LOGGER.debug("CLOSING segmentationParser");
        }

        LOGGER.debug("==>All resources closed");

    }
}
