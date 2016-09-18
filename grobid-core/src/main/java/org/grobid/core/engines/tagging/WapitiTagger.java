package org.grobid.core.engines.tagging;

import com.google.common.base.Joiner;
import fr.limsi.wapiti.WapitiModel;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public static final Logger LOGGER = LoggerFactory.getLogger(WapitiTagger.class);
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
        // FIXME(chrboum): why are you hard coding ISO-8859-1 - because ... so document the WHY!
        try {
            LOGGER.debug("label(): data={}", data);
            InputStream in = IOUtils.toInputStream(data, "ISO-8859-1");
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            GrobidWapitiIO io = new GrobidWapitiIO(in, out);
            wapitiModel.label(io);
            io.close();

            String label = out.toString("ISO-8859-1");
            LOGGER.debug("label={}", label);

            //TODO: VZ: Grobid currently expects tabs as separators whereas wapiti uses spaces for separating features.
            // for now it is safer to replace, although it does not look nice
            return label.replaceAll(" ", "\t");
        } catch (Exception e) {
            throw new GrobidException("Failed to label!", e);
        }
    }

    @Override
    public void close() throws IOException {
        this.wapitiModel.delete();
    }
}
