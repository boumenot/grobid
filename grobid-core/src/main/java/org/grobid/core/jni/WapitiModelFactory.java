package org.grobid.core.jni;

import fr.limsi.wapiti.WapitiModel;
import org.grobid.core.GrobidModelStream;
import org.grobid.core.exceptions.GrobidException;

/**
 * Created by Christopher Boumenot on 2/7/2015.
 */
public class WapitiModelFactory {
    public WapitiModel create(GrobidModelStream grobidModelStream) {
        // FIXME(chrboum): I still hate Java exceptions.
        try {
            GrobidWapitiIO io = new GrobidWapitiIO(
                    grobidModelStream.load(),
                    null);

            WapitiModel model = new WapitiModel(io);
            return model;
        } catch (Exception e) {
            throw new GrobidException("Failed to create a WapitiModel from a GrobidModelStream!", e);
        }
    }
}
