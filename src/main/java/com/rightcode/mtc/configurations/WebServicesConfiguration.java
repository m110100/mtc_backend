package com.rightcode.mtc.configurations;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
@EnableTransactionManagement
public class WebServicesConfiguration {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();

        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "eventApplication")
    public DefaultWsdl11Definition eventApplicationsWsdlDefinition(XsdSchema eventApplicationSchema) {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();

        wsdlDefinition.setPortTypeName("EventApplicationPort");
        wsdlDefinition.setLocationUri("/ws/");
        wsdlDefinition.setTargetNamespace("http://www.rightcode.com/mtc/event-application");
        wsdlDefinition.setSchema(eventApplicationSchema);

        return wsdlDefinition;
    }

    @Bean(name = "scheduleSlot")
    public DefaultWsdl11Definition scheduleSlotWsdl11Definition(XsdSchema scheduleSlotSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();

        wsdl11Definition.setPortTypeName("ScheduleSlotPort");
        wsdl11Definition.setLocationUri("/ws/");
        wsdl11Definition.setTargetNamespace("http://www.rightcode.com/mtc/schedule-slot");
        wsdl11Definition.setSchema(scheduleSlotSchema);

        return wsdl11Definition;
    }

    @Bean(name = "eventApplicationSchema")
    public XsdSchema eventApplicationSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/event-application.xsd"));
    }

    @Bean(name = "scheduleSlotSchema")
    public XsdSchema scheduleSlotSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/schedule-slot.xsd"));
    }
}
