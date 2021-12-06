package edu.radyuk.xmltask.parser;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class PlantErrorHandler implements ErrorHandler {
    public static final Logger logger = LogManager.getLogger();

    @Override
    public void warning(SAXParseException e) throws SAXException {
        logger.warn(e.getLineNumber() + " : " + e.getColumnNumber());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        logger.error(e.getLineNumber() + " : " + e.getColumnNumber());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        logger.fatal(e.getLineNumber() + " : " + e.getColumnNumber());
    }

}
