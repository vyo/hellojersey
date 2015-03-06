package io.github.vyo.hellojersey.repository;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Manuel Weidmann on 03.03.2015.
 */
public class GreetingRepositoryException extends WebApplicationException {

    public GreetingRepositoryException(String alias) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity("Greeting " + alias + " could not be found.").type(MediaType.TEXT_PLAIN).build());
    }
}
