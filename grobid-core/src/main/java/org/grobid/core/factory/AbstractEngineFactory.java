package org.grobid.core.factory;

import org.grobid.core.engines.Engine;
import org.grobid.core.engines.ModelMap;
import org.grobid.core.engines.tagging.GrobidCRFEngine;
import org.grobid.core.lexicon.LexiconImpl;
import org.grobid.core.main.LibraryLoader;
import org.grobid.core.utilities.GrobidProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Abstract factory to get engine instance.
 * 
 */
public class AbstractEngineFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEngineFactory.class);

	/**
	 * The engine.
	 */
	private static Engine engine;

	/**
	 * Return a new instance of engine if it doesn't exist, the existing
	 * instance else.
	 * 
	 * @return Engine
	 */
	protected synchronized Engine getEngine() {
		if (engine == null) {
			engine = createEngine();
		}
		return engine;
	}

	/**
	 * Return a new instance of engine.
	 * 
	 * @return Engine
	 */
	protected Engine createEngine() {
		Engine retVal = null;
		retVal = new Engine();
		return retVal;
	}

	/**
	 * Initializes all necessary things for starting grobid. 
	 */
	public static void init() {
        LOGGER.debug("AbstractEngineFactory::init()");
		GrobidProperties.getInstance();
		LibraryLoader.load();
	}
	
	/**
	 * Initializes all necessary things for starting grobid.
	 */
	public static void fullInit() {
		init();
        if (GrobidProperties.getGrobidCRFEngine() == GrobidCRFEngine.CRFPP) {
		    ModelMap.initModels();
        }
		LexiconImpl.getInstance();
	}
}
