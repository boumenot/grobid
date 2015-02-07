package org.grobid.trainer;

import org.grobid.core.GrobidModel;

import java.io.File;

/**
 * User: zholudev
 * Date: 3/20/14
 */
public interface GenericTrainer {
    void train(File template, File trainingData, File outputModel, int numThreads, GrobidModel model);
    String getName();
}
