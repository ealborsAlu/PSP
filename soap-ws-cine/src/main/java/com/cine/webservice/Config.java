package com.cine.webservice;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


 
@EnableWs
@Configuration
public class Config extends WsConfigurerAdapter 
{
	//NO TOCAR
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) 
    {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/service/*");
    }
 
    //nombre con el que se publica el servicio web
    //es el que usaremos para el buscador y SoapUI
    //http://localhost:8080/service/CineWsdl.wsdl
    @Bean(name = "CineWsdl")
   
    
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) 
    {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CinePort");
        wsdl11Definition.setLocationUri("/service/Cine");
        wsdl11Definition.setTargetNamespace("http://www.cine.com/xml/peliculas");
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }
 
    @Bean
    public XsdSchema countriesSchema() 
    {
    	//nombre del fichero xsd que vayamos a usar
        return new SimpleXsdSchema(new ClassPathResource("peliculas.xsd"));
    }
}