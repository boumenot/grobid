package org.grobid.core.engines.tagging;

import org.grobid.core.GrobidModelStream;
import org.grobid.core.GrobidModels;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Christopher Boumenot on 4/26/2015.
 */
public interface TaggerFactory {
    GenericTagger create(GrobidModels grobidModelStream);
}
