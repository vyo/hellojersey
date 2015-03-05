package io.github.vyo.hello_jersey.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import io.github.vyo.hello_jersey.entity.Greeting;
import io.github.vyo.hello_jersey.repository.GreetingStore;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by Manuel Weidmann on 01.03.2015.
 */
@Singleton
@Api(value = "hellojersey", description = "'Hello World'-style endpoint")
@Path("rest/hellojersey")
public class HelloJersey {

    @Context
    GreetingStore greetingStore;

    public HelloJersey() {
    }

    @PostConstruct
    private void info() {
        //reminder: @Context injected resource are availabe AFTER construction, not during
        //reference here if needed
    }

    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns a simple greeting", notes = "Requires no parameter.", response =
            Greeting.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Jersey is alive and well!")})
    public Greeting getGreeting() {
        return greetingStore.defaultGreeting();
    }

    @GET
    @Path("echo/{echo}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Greets you with an echo of your message", notes = "Requires an alias to be " +
            "specified as path parameter, outputs a Greeting object in JSON representation.", response = Greeting.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Enjoy your echo!")})
    public Greeting getEcho(@PathParam("echo") String echo) {
        return new Greeting(new StringBuilder(echo).reverse().toString());
    }

    @GET
    @Path("hello/store/{alias}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns a simple greeting stored on the server", notes = "Requires an alias to be " +
            "specified as path parameter.", response = Greeting.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Stored Greeting retrieved."), @ApiResponse(code = 404, message
            = "No Greeting found for requested alias.")})
    public Greeting retrieveGreeting(@PathParam("alias") String alias) {
        return greetingStore.findGreeting(alias);
    }

    @POST
    @Path("hello/store/{alias}")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Stores a simple greeting on the server", notes = "Requires an alias to be specified as " +
            "path parameter, and a Greeting object to be posted")
    @ApiResponses({@ApiResponse(code = 204, message = "Message stored")})
    public void storeGreeting(@PathParam("alias") String alias, Greeting greeting) {
        greetingStore.storeGreeting(alias, greeting);
    }

    public void setGreetingStore(GreetingStore greetingStore) {
        this.greetingStore = greetingStore;
    }
}