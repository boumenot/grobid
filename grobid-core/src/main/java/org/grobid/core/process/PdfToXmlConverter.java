package org.grobid.core.process;

import org.grobid.core.exceptions.GrobidException;

import java.io.File;
import java.util.concurrent.TimeoutException;

/**
 * Created by Christopher Boumenot on 1/24/2015.
 */
public interface PdfToXmlConverter {
    public File convertHeaderOnly(File input) throws GrobidException, TimeoutException;
    public File convertExtractImages(File input) throws GrobidException, TimeoutException;
    public File convert(File input) throws GrobidException, TimeoutException;
}

