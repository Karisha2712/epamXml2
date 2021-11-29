package edu.radyuk.xmltask.validator;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

class XmlValidatorTest {
    @Test
    void ifXmlValidatorReturnsTrue() {
        URL XmlFileUrl = XmlValidatorTest.class.getClassLoader().getResource("files/greenhouse.xml");
        URL XsdFileUrl = XmlValidatorTest.class.getClassLoader().getResource("files/greenhouse.xsd");
        File xmlFile = new File(XmlFileUrl.getFile());
        File xsdFile = new File(XsdFileUrl.getFile());
        String xmlFilepath = xmlFile.getAbsolutePath();
        String xsdFilepath = xsdFile.getAbsolutePath();
        XmlValidator xmlValidator = new XmlValidator();
        assertTrue(xmlValidator.isXmlFileValid(xmlFilepath, xsdFilepath));
    }
}
