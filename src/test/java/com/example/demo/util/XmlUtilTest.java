package com.example.demo.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.dto.ExampleLogDetail;
import com.example.demo.dto.LOGServiceUpdateRequest;
import com.example.demo.dto.LOGServiceUpdateRequest.LogDetails;

import jakarta.xml.bind.JAXBException;

public class XmlUtilTest {

    private XmlUtil xmlUtil;

    @BeforeEach
    void setUp() {
        xmlUtil = new XmlUtil();
    }

    @Test
    void testConvert_ShouldGenerateXmlWithLogDetails() throws JAXBException {

        // Arrange
        XmlUtil xmlUtil = new XmlUtil();

        LOGServiceUpdateRequest.LogDetails logDetails = new LOGServiceUpdateRequest.LogDetails();

        ExampleLogDetail detail1 = new ExampleLogDetail();
        detail1.setFieldName("transactionType");
        detail1.setFieldValue("INQUIRY");
        detail1.setFieldData("policy-lookup");

        ExampleLogDetail detail2 = new ExampleLogDetail();
        detail2.setFieldName("resultCount");
        detail2.setFieldValue("15");
        detail2.setFieldData("");

        logDetails.getLogDetail().add(detail1);
        logDetails.getLogDetail().add(detail2);

        // Act
        String xml = xmlUtil.convert(logDetails);

        // Assert
        assertNotNull(xml);
        assertTrue(xml.contains("LogDetails"));
        assertTrue(xml.contains("LogDetail"));

        // verify detail 1
        assertTrue(xml.contains("transactionType"));
        assertTrue(xml.contains("INQUIRY"));
        assertTrue(xml.contains("policy-lookup"));

        // verify detail 2
        assertTrue(xml.contains("resultCount"));
        assertTrue(xml.contains("15"));
    }

    @Test
    void testConvert_ShouldContainObjectData() throws JAXBException {

        // Arrange
        LogDetails details = new LogDetails();

        // Act
        String xml = xmlUtil.convert(details);

        // Assert
        assertNotNull(xml);
    }

    @Test
    void testConvert_WithEmptyLogDetails() throws JAXBException {

        // Arrange
        XmlUtil xmlUtil = new XmlUtil();
        LogDetails details = new LogDetails();

        // Act
        String xml = xmlUtil.convert(details);

        // Assert
        assertNotNull(xml);
        assertTrue(xml.contains("LogDetails"));
    }

    @Test
    void testConvert_WhenObjectIsNull_ShouldGenerateNilXml() throws JAXBException {

        // Act
        String xml = xmlUtil.convert(null);

        // Assert
        assertNotNull(xml);
        assertTrue(xml.contains("LogDetails"));
        assertTrue(xml.contains("nil"));
    }

}