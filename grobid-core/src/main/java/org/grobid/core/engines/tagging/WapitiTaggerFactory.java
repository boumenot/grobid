package org.grobid.core.engines.tagging;

import org.grobid.core.GrobidModelStream;
import org.grobid.core.GrobidModels;
import org.grobid.core.GrobidWapitiIOFactory;

/**
 * Created by Christopher Boumenot on 4/26/2015.
 */
public class WapitiTaggerFactory implements TaggerFactory {
    public WapitiTaggerFactory(GrobidWapitiIOFactory factory) {
    }

    @Override
    public GenericTagger create(GrobidModels grobidModelStream) {
        return null;
    }
}
