package org.grobid.core.engines.tagging;

import org.grobid.core.GrobidModel;
import org.grobid.core.GrobidModelStream;
import org.grobid.core.utilities.GrobidProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * User: zholudev
 * Date: 3/20/14
 */
public class TaggerFactory {
    private static Map<GrobidModel, GenericTagger> cache = new HashMap<GrobidModel, GenericTagger>();

    public static synchronized GenericTagger getTagger(GrobidModelStream grobidModelStream) {
        GenericTagger t = cache.get(grobidModelStream);
        if (t == null) {
            switch (GrobidProperties.getGrobidCRFEngine()) {
                case CRFPP:
                    t = new CRFPPTagger(grobidModelStream);
                    break;
                case WAPITI:
                    t = new WapitiTagger(grobidModelStream);
                    break;
                default:
                    throw new IllegalStateException("Unsupported Grobid CRF engine: " + GrobidProperties.getGrobidCRFEngine());
            }
            cache.put(grobidModelStream, t);
        }
        return t;
    }
}
