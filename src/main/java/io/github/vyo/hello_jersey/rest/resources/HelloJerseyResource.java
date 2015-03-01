package io.github.vyo.hello_jersey.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

/**
 * Created by Manuel Weidmann on 01.03.2015.
 */
@Path("hellojersey")
public class HelloJerseyResource {

    @GET
    @Produces("text/plain")
    public String getGreeting() {
        return "Hello Jersey!";
    }
}