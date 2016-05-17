package io.github.vyo.hellojersey.application;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.jersey.listing.ApiListingResourceJSON;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


/**
 * Created by Manuel Weidmann on 01.03.2015.
 */
@ApplicationPath("api")
public class Application extends ResourceConfig {

    public Application() {
        packages(true, "io.github.vyo.hellojersey");

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Hello Jersey");
        beanConfig.setVersion("3.2.0");
        beanConfig.setHost("hellojersey.herokuapp.com");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("io.github.vyo.hellojersey");
        beanConfig.setScan(true);
        register(new ApiListingResourceJSON());
        register(new SwaggerSerializers());
    }
}
