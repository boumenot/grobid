package org.grobid.core.engines;

import org.grobid.core.GrobidModelStream;
import org.grobid.core.engines.tagging.GenericTagger;
import org.grobid.core.engines.tagging.TaggerFactory;
import org.grobid.core.engines.tagging.TaggerFactoryOld;
import org.grobid.core.utilities.counters.CntManager;
import org.grobid.core.utilities.counters.impl.CntManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

public abstract class AbstractParser implements GenericTagger, Closeable {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractParser.class);
    private GenericTagger genericTagger;

    protected CntManager cntManager;

    protected AbstractParser(GenericTagger genericTagger) {
        this(genericTagger, CntManagerFactory.getNoOpCntManager());
    }

    protected AbstractParser(GenericTagger genericTagger, CntManager cntManager) {
        this.genericTagger = genericTagger;
        this.cntManager = cntManager;
    }

    @Override
    public String label(Iterable<String> data) {
        return genericTagger.label(data);
    }

    @Override
    public String label(String data) {
        return genericTagger.label(data);
    }

//    protected String augmentResultWithType(String data, String type) {
//        Iterable<String> it = Splitter.on("\n").split(data);
//        StringBuilder sb = new StringBuilder((int) (data.length() * 1.5));
//        for (String s : it) {
//            String[] split = s.split(" \t");
//            int len = split.length;
//            for ()
//        }
//
//    }

    @Override
    public void close() throws IOException {
        try {	
            genericTagger.close();
        } catch (Exception e) {
            LOGGER.warn("Cannot close the parser: " + e.getMessage());
            //no op
        }
    }
}
