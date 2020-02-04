package com.springboot.product.configuration;

import com.springboot.product.service.IProductService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;


@Configuration
public class CxfConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private IProductService productService;

    @Bean
    public Endpoint productServiceEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, productService);
        endpoint.publish("/productService");
        return endpoint;
    }
}
