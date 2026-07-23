package com.example.demo.util;

import java.io.StringWriter;

import javax.xml.namespace.QName;

import org.springframework.stereotype.Component;

import com.example.demo.dto.LOGServiceUpdateRequest;
import com.example.demo.dto.LOGServiceUpdateRequest.LogDetails;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

@Component
public class XmlUtil {

    public String convert(LogDetails obj) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(
                LOGServiceUpdateRequest.class);

        Marshaller marshaller = context.createMarshaller();

        StringWriter writer = new StringWriter();

        QName qName = new QName(
                "http://webservices.ins.com/WsLogServiceExample",
                "LogDetails");

        JAXBElement<LogDetails> element = new JAXBElement<>(
                qName,
                LogDetails.class,
                obj);

        marshaller.marshal(element, writer);

        return writer.toString();
    }

}
