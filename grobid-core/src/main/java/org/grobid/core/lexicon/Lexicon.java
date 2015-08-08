package org.grobid.core.lexicon;

import org.grobid.core.utilities.OffsetPosition;

import java.util.List;

/**
 * Created by Christopher Boumenot on 8/6/2015.
 */
public interface Lexicon {
    boolean inDictionary(String s);

    List<OffsetPosition> inCityNames(String s);
}
