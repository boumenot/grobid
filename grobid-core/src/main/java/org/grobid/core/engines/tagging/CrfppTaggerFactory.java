package org.grobid.core.engines.tagging;

import org.grobid.core.GrobidModelStream;
import org.grobid.core.GrobidModelStreamFactory;
import org.grobid.core.GrobidModels;

/**
 * Created by Christopher Boumenot on 4/26/2015.
 */
public class CrfppTaggerFactory implements TaggerFactory {
    private final GrobidModelStreamFactory factory;

    public CrfppTaggerFactory(GrobidModelStreamFactory factory) {
        this.factory = factory;
    }

    @Override
    public GenericTagger create(GrobidModels grobidModels) {
        GrobidModelStream grobidModelStream = this.factory.create(grobidModels);
        CRFPPTagger crfppTagger = new CRFPPTagger(grobidModelStream);
        return crfppTagger;
    }
}
