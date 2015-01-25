package org.grobid.core.test;

import org.grobid.core.process.PdfToXmlCmdFactory;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class PdfToXmlCmdFactoryTest {
    private final File pdfToXml = new File("/usr/bin/pdftoxml");
    private final File input = new File("/tmp/input.pdf");
    private final File output = new File("/tmp/output.pdf");

    @Test
    public void factoryShouldProcessArguments() throws Exception {
        PdfToXmlCmdFactory testSubject = new PdfToXmlCmdFactory(this.pdfToXml);
        List<String> args = testSubject.create(
                0 /* startPage */,
                0 /* endPage */,
                false /* isFull */,
                this.input,
                this.output);

        assertArrayEquals(
                args.toArray(),
                new String[]{
                        this.pdfToXml.getAbsolutePath(),
                        "-blocks",
                        "-noImageInline",
                        "-fullFontName",
                        "-noImage",
                        this.input.getAbsolutePath(),
                        this.output.getAbsolutePath()
                });
    }

    @Test
    public void factoryShouldProcessStartPage() throws Exception {
        PdfToXmlCmdFactory testSubject = new PdfToXmlCmdFactory(this.pdfToXml);
        List<String> args = testSubject.create(
                2 /* startPage */,
                0 /* endPage */,
                false /* isFull */,
                this.input,
                this.output);

        assertArrayEquals(
                args.toArray(),
                new String[]{
                        this.pdfToXml.getAbsolutePath(),
                        "-blocks",
                        "-noImageInline",
                        "-fullFontName",
                        "-noImage",
                        "-f",
                        "2",
                        this.input.getAbsolutePath(),
                        this.output.getAbsolutePath()
                });
    }

    @Test
    public void factoryShouldPageMarkersArguments() throws Exception {
        PdfToXmlCmdFactory testSubject = new PdfToXmlCmdFactory(this.pdfToXml);
        List<String> args = testSubject.create(
                0 /* startPage */,
                7 /* endPage */,
                false /* isFull */,
                this.input,
                this.output);

        assertArrayEquals(
                args.toArray(),
                new String[]{
                        this.pdfToXml.getAbsolutePath(),
                        "-blocks",
                        "-noImageInline",
                        "-fullFontName",
                        "-noImage",
                        "-l",
                        "7",
                        this.input.getAbsolutePath(),
                        this.output.getAbsolutePath()
                });
    }

    @Test
    public void factoryShouldFullPage() throws Exception {
        PdfToXmlCmdFactory testSubject = new PdfToXmlCmdFactory(this.pdfToXml);
        List<String> args = testSubject.create(
                2 /* startPage */,
                6 /* endPage */,
                true /* isFull */,
                this.input,
                this.output);

        assertArrayEquals(
                args.toArray(),
                new String[]{
                        this.pdfToXml.getAbsolutePath(),
                        "-blocks",
                        "-noImageInline",
                        "-fullFontName",
                        "-f",
                        "2",
                        "-l",
                        "6",
                        this.input.getAbsolutePath(),
                        this.output.getAbsolutePath()
                });
    }
}