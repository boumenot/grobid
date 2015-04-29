package org.grobid.core.engines.tagging;

import fr.limsi.wapiti.WapitiModel;
import org.grobid.core.GrobidModelStream;
import org.grobid.core.GrobidModels;
import org.grobid.core.GrobidWapitiIOFactory;
import org.grobid.core.exceptions.GrobidException;
import org.grobid.core.exceptions.GrobidExceptionStatus;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Christopher Boumenot on 4/26/2015.
 */
public class WapitiTaggerFactory implements TaggerFactory {
    private final GrobidWapitiIOFactory factory;

    public WapitiTaggerFactory(GrobidWapitiIOFactory factory) {
        this.factory = factory;
    }

    @Override
    public GenericTagger create(GrobidModels grobidModels) {
        try {
            WapitiModel wapitiModel = new WapitiModel(this.factory.openModel(grobidModels));
            WapitiTagger wapitiTagger = new WapitiTagger(wapitiModel);

            return wapitiTagger;
        }
        catch (FileNotFoundException e) {
            throw new GrobidException(e.getMessage());
        }
        catch (UnsupportedEncodingException e) {
            throw new GrobidException(e.getMessage());
        }
    }
}
