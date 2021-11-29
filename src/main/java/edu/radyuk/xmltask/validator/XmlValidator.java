package edu.radyuk.xmltask.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    private static final Logger logger = LogManager.getLogger();

    public XmlValidator() {
    }

    public boolean isXmlFileValid(String xmlFilepath, String xsdFilepath) {
        boolean result = true;
        SchemaFactory factory =
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = factory.newSchema(new File(xsdFilepath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFilepath)));
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while reading file: {}", xmlFilepath, e);
            result = false;
        } catch (SAXException e) {
            logger.log(Level.ERROR, "File {} is not valid", xmlFilepath, e);
            result = false;
        }
        return result;
    }
}

