package io.github.vyo.hello_jersey.application;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;
import io.github.vyo.hello_jersey.filter.CORSResponseFilter;
import io.github.vyo.hello_jersey.repository.GreetingStore;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


/**
 * Created by Manuel Weidmann on 01.03.2015.
 */
@ApplicationPath("app")
public class Application extends ResourceConfig {

    public Application() {
        register(CORSResponseFilter.class);
        register(GreetingStore.class);
        register(Binder.class);

        packages(true, "io.github.vyo.hello_jersey");
        packages(true, "com.wordnik.swagger.jersey.listing");

        SwaggerConfig beanConfig = new SwaggerConfig();
        ConfigFactory.setConfig(beanConfig);
        beanConfig.setApiVersion("1.0.0");
        beanConfig.setBasePath("http://localhost:8080/app");
        ScannerFactory.setScanner(new DefaultJaxrsScanner());
        ClassReaders.setReader(new DefaultJaxrsApiReader());
    }
}