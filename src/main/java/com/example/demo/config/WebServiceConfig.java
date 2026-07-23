package com.example.demo.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WebServiceConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext context) {

        MessageDispatcherServlet servlet = new MessageDispatcherServlet();

        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<>(
                servlet,
                "/ws/*");
    }

    @Bean(name = "log")
    public DefaultWsdl11Definition wsdl(XsdSchema schema) {

        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();

        wsdl.setPortTypeName("LOGServicePort");
        wsdl.setLocationUri("/ws");
        wsdl.setTargetNamespace("http://webservices.ins.com/WsLogServiceExample");
        wsdl.setSchema(schema);

        return wsdl;
    }

    @Bean
    public XsdSchema schema() {

        return new SimpleXsdSchema(
            new ClassPathResource("xsd/log-service.xsd"));
    }

}
