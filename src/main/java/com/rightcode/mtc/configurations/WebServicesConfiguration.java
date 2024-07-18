package com.rightcode.mtc.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
public class WebServicesConfiguration {
    private final CorsProps corsProps;

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(corsProps.isAllowedCredentials());
        corsProps.getAllowedOrigins().forEach(configuration::addAllowedOrigin);
        configuration.addAllowedHeader(corsProps.getAllowedHeaders());
        corsProps.getAllowedMethods().forEach(configuration::addAllowedMethod);
        configuration.setMaxAge(corsProps.getMaxAge());

        source.registerCorsConfiguration(corsProps.getCorsConfiguration(), configuration);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);

        return bean;
    }

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

    @Bean(name = "scheduleSlot")
    public DefaultWsdl11Definition scheduleSlotWsdl11Definition(XsdSchema scheduleSlotSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();

        wsdl11Definition.setPortTypeName("ScheduleSlotPort");
        wsdl11Definition.setLocationUri("/ws/");
        wsdl11Definition.setTargetNamespace("http://www.rightcode.com/mtc/schedule-slot");
        wsdl11Definition.setSchema(scheduleSlotSchema);

        return wsdl11Definition;
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

    @Bean(name = "medicalOrganization")
    public DefaultWsdl11Definition medicalOrganizationWsdlDefinition(XsdSchema medicalOrganizationSchema) {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();

        wsdlDefinition.setPortTypeName("MedicalOrganizationPort");
        wsdlDefinition.setLocationUri("/ws/");
        wsdlDefinition.setTargetNamespace("http://www.rightcode.com/mtc/medical-organization");
        wsdlDefinition.setSchema(medicalOrganizationSchema);

        return wsdlDefinition;
    }

    @Bean(name = "medicalPosition")
    public DefaultWsdl11Definition medicalPositionWsdlDefinition(XsdSchema medicalPositionSchema) {
        DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();

        wsdlDefinition.setPortTypeName("MedicalPositionPort");
        wsdlDefinition.setLocationUri("/ws/");
        wsdlDefinition.setTargetNamespace("http://www.rightcode.com/mtc/medical-position");
        wsdlDefinition.setSchema(medicalPositionSchema);

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

    @Bean(name = "eventType")
    public DefaultWsdl11Definition eventTypeWsdl11Definition(XsdSchema eventTypeSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();

        wsdl11Definition.setPortTypeName("EventTypePort");
        wsdl11Definition.setLocationUri("/ws/");
        wsdl11Definition.setTargetNamespace("http://www.rightcode.com/mtc/event-type");
        wsdl11Definition.setSchema(eventTypeSchema);

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

    @Bean(name = "medicalPositionSchema")
    public XsdSchema medicalPositionSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/medical-position.xsd"));
    }

    @Bean(name = "medicalOrganizationSchema")
    public XsdSchema medicalOrganizationSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/medical-organization.xsd"));
    }

    @Bean(name = "userSchema")
    public XsdSchema userSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/user.xsd"));
    }

    @Bean(name = "eventTypeSchema")
    public XsdSchema eventTypeSchema(){
        return new SimpleXsdSchema(new ClassPathResource("schemas/event-type.xsd"));
    }

    @Bean(name = "authenticationSchema")
    public XsdSchema authenticationSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schemas/authentication.xsd"));
    }
}
