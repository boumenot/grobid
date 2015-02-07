package org.grobid.core;

import org.grobid.core.utilities.GrobidProperties;

import java.io.File;

/**
 * Created by Christopher Boumenot on 2/6/2015.
 */
public class GrobidModelStreamFactory {
    public GrobidModelStream Create(GrobidModels grobidModels) {
        File grobidModelFile = GrobidProperties.getModelPath(grobidModels);
        GrobidModelStream grobidModelStream = new GrobidModelStream(grobidModels, grobidModelFile);

        return grobidModelStream;
    }
}
