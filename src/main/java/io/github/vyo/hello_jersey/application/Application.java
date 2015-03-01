package io.github.vyo.hello_jersey.application;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


/**
 * Created by Manuel Weidmann on 01.03.2015.
 */
@ApplicationPath("")
public class Application extends ResourceConfig {

    public Application() {
        packages(true, "io.github.vyo.hello_jersey.rest.resources");
    }

}
