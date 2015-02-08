package org.grobid.core.engines.tagging;

import org.grobid.core.GrobidModelStream;
import org.grobid.core.GrobidModelStreamFactory;
import org.grobid.core.GrobidModels;

import java.util.HashMap;

/**
 * Created by Christopher Boumenot on 2/7/2015.
 */
public class GrobidModelStreamCache {
    private final GrobidModelStreamFactory grobidModelStreamFactory;
    private final HashMap<GrobidModels, GrobidModelStream> models;

    public GrobidModelStreamCache(GrobidModelStreamFactory grobidModelStreamFactory) {
        this.grobidModelStreamFactory = grobidModelStreamFactory;
        this.models = new HashMap<GrobidModels, GrobidModelStream>();
    }

    public synchronized GrobidModelStream get(GrobidModels grobidModels) {
        if (this.models.containsKey(grobidModels)) {
            return this.models.get(grobidModels);
        }

        GrobidModelStream grobidModelStream = this.grobidModelStreamFactory.create(grobidModels);
        this.models.put(grobidModels, grobidModelStream);

        return grobidModelStream;
    }
}
