package org.grobid.core.engines.tagging;

import com.google.common.base.Joiner;
import fr.limsi.wapiti.WapitiModel;
import org.apache.commons.io.IOUtils;
import org.grobid.core.exceptions.GrobidException;
import org.grobid.core.jni.GrobidWapitiIO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: zholudev
 * Date: 3/20/14
 */
public class WapitiTagger implements GenericTagger {
    private final WapitiModel wapitiModel;

    public WapitiTagger(WapitiModel wapitiModel) {
        this.wapitiModel = wapitiModel;
    }

    @Override
    public String label(Iterable<String> data) {
        return label(Joiner.on('\n').join(data));
    }

    @Override
    public String label(String data) {
        // FIXME(chrboum): don't swallow and convert to a RuntimeException.
        // FIXME(chrboum): why are you hard coding UTF-8?
        try {
            InputStream in = IOUtils.toInputStream(data, "UTF-8");
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            GrobidWapitiIO io = new GrobidWapitiIO(in, out);
            wapitiModel.label(io);

            return out.toString("UTF-8");
        } catch (Exception e) {
            throw new GrobidException("Failed to label!", e);
        }
    }

    @Override
    public void close() throws IOException {
        this.wapitiModel.delete();
    }
}
