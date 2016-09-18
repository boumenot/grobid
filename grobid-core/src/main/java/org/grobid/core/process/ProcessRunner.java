package org.grobid.core.process;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Date: 6/26/12
 * Time: 3:55 PM
 *
 * @author Vyacheslav Zholudev, Patrice Lopez
 */
public class ProcessRunner extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessRunner.class);

	private List<String> cmd;
    private Integer exit;

    public String getErrorStreamContents() {
        return errorStreamContents;
    }

    private String errorStreamContents;

    private boolean useStreamGobbler;
    StreamGobbler sgIn;
    StreamGobbler sgErr;

	public ProcessRunner(List<String> cmd, String name, boolean useStreamGobbler) {
        super(name);
        this.cmd = cmd;
        this.useStreamGobbler = useStreamGobbler;
    }

    public void run() {
        Process process = null;
        try {
			ProcessBuilder builder = new ProcessBuilder(cmd);
			process = builder.start();
			
            if (useStreamGobbler) {
                sgIn = new StreamGobbler(process.getInputStream());
                sgErr = new StreamGobbler(process.getErrorStream());
            }

            exit = process.waitFor();
            LOGGER.info("ProcessRunner::run() exit={}", exit);
        } 
		catch (InterruptedException ignore) {
            LOGGER.error("Process worker was interrupted");
            LOGGER.error(" -> {}", ignore);
            //Process needs to be destroyed -- it's done in the finally block
        } 
		catch (IOException e) {
            LOGGER.error("IOException while launching the command {} : {}", cmd.toString(), e.getMessage());
            LOGGER.error(" -> {}", e);
        } 
		finally {
            if (process != null) {
                IOUtils.closeQuietly(process.getInputStream());
                IOUtils.closeQuietly(process.getOutputStream());
                try {
                    errorStreamContents = IOUtils.toString(process.getErrorStream());
                } catch (IOException e) {
                    LOGGER.error("Error retrieving error stream from process: {}", e);
                }
                IOUtils.closeQuietly(process.getErrorStream());

                process.destroy();
            }

            if (useStreamGobbler) {
                try {
                    if (sgIn != null) {
                        sgIn.close();
                    }
                } 
				catch (IOException e) {
                    LOGGER.error("IOException while closing the stream gobbler: {}", e);
                }

                try {
                    if (sgErr != null) {
                        sgErr.close();
                    }
                } 
				catch (IOException e) {
                    LOGGER.error("IOException while closing the stream gobbler: {}", e);
                }
            }
        }
    }

    public Integer getExitStatus() {
        return exit;
    }
}
