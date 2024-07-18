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

        return new ServletRegistrationBean<>(servlet, "/ws/*", "/auth/*");
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

    @Bean(name = "event")
    public DefaultWsdl11Definition eventWsdlDefinition(XsdSchema eventSchema) {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();

        wsdlDefinition.setPortTypeName("EventPort");
        wsdlDefinition.setLocationUri("/ws/");
        wsdlDefinition.setTargetNamespace("http://www.rightcode.com/mtc/event");
        wsdlDefinition.setSchema(eventSchema);

        return wsdlDefinition;
    }

    @Bean(name = "eventParticipant")
    public DefaultWsdl11Definition eventParticipantWsdlDefinition(XsdSchema eventParticipantSchema) {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();

        wsdlDefinition.setPortTypeName("EventParticipantPort");
        wsdlDefinition.setLocationUri("/ws/");
        wsdlDefinition.setTargetNamespace("http://www.rightcode.com/mtc/event-participant");
        wsdlDefinition.setSchema(eventParticipantSchema);

        return wsdlDefinition;
    }

    @Bean(name = "medicalSpeciality")
    public DefaultWsdl11Definition medicalSpecialityWsdlDefinition(XsdSchema medicalSpecialitySchema) {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();

        wsdlDefinition.setPortTypeName("MedicalSpecialityPort");
        wsdlDefinition.setLocationUri("/ws/");
        wsdlDefinition.setTargetNamespace("http://www.rightcode.com/mtc/medical-speciality");
        wsdlDefinition.setSchema(medicalSpecialitySchema);

        return wsdlDefinition;
    }

    @Bean(name = "user")
    public DefaultWsdl11Definition userWsdlDefinition(XsdSchema userSchema) {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();

        wsdlDefinition.setPortTypeName("UserPort");
        wsdlDefinition.setLocationUri("/ws/");
        wsdlDefinition.setTargetNamespace("http://www.rightcode.com/mtc/user");
        wsdlDefinition.setSchema(userSchema);

        return wsdlDefinition;
    }

    @Bean(name = "auth")
    public DefaultWsdl11Definition authenticationWsdlDefinition(XsdSchema authenticationSchema) {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();

        wsdlDefinition.setPortTypeName("AuthenticationPort");
        wsdlDefinition.setLocationUri("/auth");
        wsdlDefinition.setTargetNamespace("http://www.rightcode.com/mtc/authentication");
        wsdlDefinition.setSchema(authenticationSchema);

        return wsdlDefinition;
    }

    @Bean(name = "eventApplicationSchema")
    public XsdSchema eventApplicationSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/event-application.xsd"));
    }

    @Bean(name = "eventSchema")
    public XsdSchema eventSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/event.xsd"));
    }

    @Bean(name = "eventParticipantSchema")
    public XsdSchema eventParticipantSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/event-participant.xsd"));
    }

    @Bean(name = "medicalSpecialitySchema")
    public XsdSchema medicalSpecialitySchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/medical-speciality.xsd"));
    }

    @Bean(name = "userSchema")
    public XsdSchema userSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/user.xsd"));
    }

    @Bean(name = "authenticationSchema")
    public XsdSchema authenticationSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/authentication.xsd"));
    }
}
