package org.grobid.core.process;

import org.grobid.core.exceptions.GrobidException;
import org.grobid.core.utilities.KeyGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class PdfToXmlConverterImpl implements PdfToXmlConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PdfToXmlConverterImpl.class);
    private static final int KILLED_DUE_2_TIMEOUT = 143;

    private final PdfToXmlCmdFactory factory;
    private final File tmpPath;
    private final Duration maxDurationPdfToXml;

    public PdfToXmlConverterImpl(
            PdfToXmlCmdFactory factory,
            File tmpPath,
            Duration maxDurationPdfToXml) {
        this.factory = factory;
        this.tmpPath = tmpPath;
        this.maxDurationPdfToXml = maxDurationPdfToXml;
    }

    public PdfToXmlConverterImpl(
            PdfToXmlCmdFactory factory,
            File tmpPath) {
        this(factory, tmpPath, Duration.ofMillis(Long.MAX_VALUE));
    }

    @Override
    public File convertHeaderOnly(File input) throws GrobidException, TimeoutException {
        File tmpFile = this.getTmpFile();
        List<String> cmd = this.factory.create(
                0 /* startPage */,
                3 /* endPage */,
                false /* isFull */,
                input,
                tmpFile);

        this.runTimedCommand(this.maxDurationPdfToXml, cmd, this.getThreadName(input));
        return tmpFile;
    }

    @Override
    public File convertExtractImages(File input) throws GrobidException, TimeoutException {
        File tmpFile = this.getTmpFile();
        List<String> cmd = this.factory.create(
                -1 /* startPage */,
                -1 /* endPage */,
                true /* isFull */,
                input,
                tmpFile);

        this.runTimedCommand(this.maxDurationPdfToXml, cmd, this.getThreadName(input));
        return tmpFile;
    }

    public File convert(File input) throws GrobidException, TimeoutException {
        File tmpFile = this.getTmpFile();
        List<String> cmd = this.factory.create(
                -1 /* startPage */,
                -1 /* endPage */,
                false /* isFull */,
                input,
                tmpFile);

        this.runTimedCommand(this.maxDurationPdfToXml, cmd, this.getThreadName(input));
        return tmpFile;
    }

    private void runTimedCommand(Duration duration, List<String> cmd, String threadName) throws TimeoutException {
        LOGGER.info("Executing: {}", cmd);
        ProcessRunner worker = new ProcessRunner(cmd, threadName, true);

        worker.start();

        try {
            worker.join(duration.toMillis());
            if (worker.getExitStatus() == null) {
                throw new GrobidException("PDF to XML conversion timed out");
            }

            if (worker.getExitStatus() == PdfToXmlConverterImpl.KILLED_DUE_2_TIMEOUT) {
                throw new TimeoutException("PDF to XML conversion exceeded time limit and was killed");
            }

            if (worker.getExitStatus() != 0) {
                throw new GrobidException(
                        "PDF to XML conversion failed due to: "
                                + worker.getErrorStreamContents());
            }
        } catch (InterruptedException ex) {
            LOGGER.error("runTimedCommand interrupted {}", ex);
            worker.interrupt();
            Thread.currentThread().interrupt();
        } finally {
            worker.interrupt();
        }
    }

    private String getThreadName(File pdfPath) {
        return "pdf2xml[" + pdfPath + "]";
    }

    private File getTmpFile() {
        String filename = this.tmpPath + "/" + KeyGen.getKey() + ".lxml";
        return new File(filename);
    }
}
