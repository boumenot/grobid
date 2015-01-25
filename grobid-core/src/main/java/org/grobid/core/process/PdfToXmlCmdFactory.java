package org.grobid.core.process;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PdfToXmlCmdFactory {
    public List<String> create(
            File pdfToXml,
            int startPage,
            int endPage,
            Boolean isFull,
            File pdfFilename,
            File tmpFilename) {

        List<String> cmd = new ArrayList<String>();
        cmd.add(pdfToXml.getAbsolutePath());
        cmd.add("-blocks");
        cmd.add("-noImageInline");
        cmd.add("-fullFontName");

        if (!isFull) {
            cmd.add("-noImage");
        }

        if (startPage > 0) {
            cmd.add("-f");
            cmd.add(String.valueOf(startPage));
        }

        if (endPage > 0) {
            cmd.add("-l");
            cmd.add(String.valueOf(endPage));
        }

        cmd.add(pdfFilename.getAbsolutePath());
        cmd.add(tmpFilename.getAbsolutePath());

        return cmd;
    }
}
