package org.grobid.core;

/**
 * Created by Christopher Boumenot on 2/6/2015.
 */
public interface GrobidModel {
    String getFolderName();

    String getModelPath();

    String getModelName();

    String getTemplateName();
}
