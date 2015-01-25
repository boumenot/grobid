package org.grobid.core.document;

import org.grobid.core.layout.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christopher Boumenot on 1/24/2015.
 */

public class PdfXmlParserResult {
    private final List<String> images;
    private List<String> tokenizations;
    private final List<Block> blocks;

    public PdfXmlParserResult() {
        this.images = new ArrayList<String>();
        this.tokenizations = new ArrayList<String>();
        this.blocks = new ArrayList<Block>();
    }

    public List<String> getImages() { return this.images; }
    public List<Block> getBlocks() { return this.blocks; }

    public List<String> getTokenizations() { return this.tokenizations; }
    public void setTokenizations(List<String> tokenizations) { this.tokenizations = tokenizations; }
}
