package org.grobid.core.engines.tagging;

import com.google.common.base.Joiner;
import org.grobid.core.GrobidModel;
import org.grobid.core.GrobidModelStream;
import org.grobid.core.jni.WapitiModel;

import java.io.IOException;

/**
 * User: zholudev
 * Date: 3/20/14
 */
public class WapitiTagger implements GenericTagger {

    private final WapitiModel wapitiModel;

    public WapitiTagger(GrobidModelStream grobidModelStream) {
        wapitiModel = new WapitiModel(grobidModelStream);
    }

    @Override
    public String label(Iterable<String> data) {
        return label(Joiner.on('\n').join(data));
    }

    @Override
    public String label(String data) {
        return wapitiModel.label(data);
    }

    @Override
    public void close() throws IOException {
        wapitiModel.close();
    }
}
