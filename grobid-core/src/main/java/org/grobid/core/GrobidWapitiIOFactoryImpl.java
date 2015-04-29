package org.grobid.core;

import org.grobid.core.jni.GrobidWapitiIO;
import org.grobid.core.utilities.GrobidProperties;

import java.io.*;

/**
 * Created by Christopher Boumenot on 4/26/2015.
 */
public class GrobidWapitiIOFactoryImpl implements GrobidWapitiIOFactory {
    private final GrobidModelStreamFactory factory;

    public GrobidWapitiIOFactoryImpl(GrobidModelStreamFactory factory) {
        this.factory = factory;
    }

    @Override
    public GrobidWapitiIO openModel(GrobidModels grobidModels) throws FileNotFoundException, UnsupportedEncodingException {
        GrobidModelStream grobidModelStream = this.factory.create(grobidModels);
        String modelPath = GrobidProperties.getInstance().getModelPath(grobidModelStream).getAbsolutePath();

        InputStream inputStream = new FileInputStream(modelPath);
        return this.create(inputStream, null);
    }

    @Override
    public GrobidWapitiIO create(InputStream inputStream, OutputStream outputStream) throws UnsupportedEncodingException {
        GrobidWapitiIO io = new GrobidWapitiIO(inputStream, outputStream);
        return io;
    }
}
