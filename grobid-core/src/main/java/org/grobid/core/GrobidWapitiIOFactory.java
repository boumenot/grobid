package org.grobid.core;

import org.grobid.core.jni.GrobidWapitiIO;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by Christopher Boumenot on 4/26/2015.
 */
public interface GrobidWapitiIOFactory {
    GrobidWapitiIO openModel(GrobidModels grobidModels) throws FileNotFoundException, UnsupportedEncodingException;
    GrobidWapitiIO create(InputStream inputStream, OutputStream outputStream) throws UnsupportedEncodingException;
}

