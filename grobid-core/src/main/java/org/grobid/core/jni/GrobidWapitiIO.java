package org.grobid.core.jni;

import fr.limsi.wapiti.WapitiIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Christopher Boumenot on 2/7/2015.
 */
public class GrobidWapitiIO extends WapitiIO {
    final static private String Latin1 = "ISO-8859-1";

    private BufferedReader reader;
    private BufferedWriter writer;

    public GrobidWapitiIO(
            InputStream inputStream,
            OutputStream outputStream) throws UnsupportedEncodingException {
        super();
        this.reader = new BufferedReader(
                new InputStreamReader(inputStream, GrobidWapitiIO.Latin1));

        if (outputStream != null) {
            this.writer = new BufferedWriter(
                    new OutputStreamWriter(outputStream, GrobidWapitiIO.Latin1));
        }
    }

    public void close() {
        try {
            if (this.writer != null)
                this.writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readline() {
        try {
            return this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void append(String data) {
        try {
            this.writer.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
