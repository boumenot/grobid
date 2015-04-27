package org.grobid.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Christopher Boumenot on 2/7/2015.
 */
public class GrobidModelStream implements GrobidModel {
    private final GrobidModels grobidModels;
    private final File grobidModelFile;

    public GrobidModelStream(
            GrobidModels grobidModels,
            File grobidModelFile) {
        this.grobidModels = grobidModels;
        this.grobidModelFile = grobidModelFile;
    }

    public InputStream load() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(this.grobidModelFile);
        return inputStream;
    }

    @Override
    public String getFolderName() {
        return this.grobidModels.getFolderName();
    }

    @Override
    public String getModelName() {
        return this.grobidModels.getModelName();
    }

    @Override
    public String getTemplateName() {
        return this.grobidModels.getTemplateName();
    }
}
