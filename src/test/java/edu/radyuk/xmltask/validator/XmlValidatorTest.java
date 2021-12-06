package edu.radyuk.xmltask.validator;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XmlValidatorTest {
    @Test
    void ifXmlValidatorReturnsTrue() {
        URL xmlFileUrl = XmlValidatorTest.class.getClassLoader().getResource("files/greenhouse.xml");
        URL xsdFileUrl = XmlValidatorTest.class.getClassLoader().getResource("files/greenhouse.xsd");
        File xmlFile = new File(xmlFileUrl.getFile());
        File xsdFile = new File(xsdFileUrl.getFile());
        String xmlFilepath = xmlFile.getAbsolutePath();
        String xsdFilepath = xsdFile.getAbsolutePath();
        XmlValidator xmlValidator = new XmlValidator();
        assertTrue(xmlValidator.isXmlFileValid(xmlFilepath, xsdFilepath));
    }

    @Test
    void ifXmlValidatorReturnsFalse() {
        URL xmlFileUrl = XmlValidatorTest.class.getClassLoader().getResource("files/greenhouseinvalid.xml");
        URL xsdFileUrl = XmlValidatorTest.class.getClassLoader().getResource("files/greenhouse.xsd");
        File xmlFile = new File(xmlFileUrl.getFile());
        File xsdFile = new File(xsdFileUrl.getFile());
        String xmlFilepath = xmlFile.getAbsolutePath();
        String xsdFilepath = xsdFile.getAbsolutePath();
        XmlValidator xmlValidator = new XmlValidator();
        assertFalse(xmlValidator.isXmlFileValid(xmlFilepath, xsdFilepath));
    }
}
