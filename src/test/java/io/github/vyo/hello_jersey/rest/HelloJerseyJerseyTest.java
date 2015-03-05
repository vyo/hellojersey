package io.github.vyo.hello_jersey.rest;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import io.github.vyo.hello_jersey.entity.Greeting;
import io.github.vyo.hello_jersey.repository.GreetingDatabase;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;

public class HelloJerseyJerseyTest extends JerseyTest {

    @Mock
    GreetingDatabase greetingDatabase;


    @Override
    protected Application configure() {
        ResourceConfig resourceConfig = new ResourceConfig(HelloJersey.class);
        MockitoAnnotations.initMocks(this);

        Mockito.when(greetingDatabase.findGreeting(Mockito.anyString())).thenAnswer(invocation -> {
            String name = (String) invocation.getArguments()[0];

            if (name.equals("hawaii")) {
                return new Greeting("Aloha!");
            } else {
                return new Greeting("Hello, I am a mock response.");
            }
        });
        Mockito.when(greetingDatabase.defaultGreeting()).thenAnswer(invocation -> new Greeting("Hello mock Jersey!"));
        Mockito.doNothing().when(greetingDatabase).storeGreeting(Mockito.anyString(), Mockito.any(Greeting.class));

        // context injection will not work, thus specify injection behaviour explicitly
        resourceConfig.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(greetingDatabase).to(GreetingDatabase.class);
            }
        });
        return resourceConfig;
    }

    @Test
    public void testHelloObject() {
        final String hello = target("rest/hellojersey/hello").request().get(Greeting.class).getMessage();
        assertEquals("Hello mock Jersey!", hello);
    }

    @Test
    public void testHelloJSON() {
        final String hello = target("rest/hellojersey/hello").request().get().readEntity(String.class);
        //overkill at this point, but a simple string comparison does not suffice for JSON - contents may be in any
        // order
        JsonParser parser = new JsonParser();
        assertEquals(parser.parse(new Gson().toJson(new Greeting("Hello mock Jersey!"))), parser.parse(hello));
    }

    @Test
    public void testHelloEchoObject() {

        String echoString = "I am an echo!";
        String echoResult = new StringBuilder(echoString).reverse().toString();

        final String hello = target("rest/hellojersey/echo/" + echoString).request().get(Greeting.class).getMessage();
        assertEquals(echoResult, hello);
    }

    @Test
    public void testHelloEchoJSON() {

        String echoString = "I am an echo!";
        String echoResult = new StringBuilder(echoString).reverse().toString();

        final String helloJSON = target("rest/hellojersey/echo/" + echoString).request().get().readEntity(String
                .class);
        //overkill at this point, but a simple string comparison does not suffice for JSON - contents may be in any
        // order
        JsonParser parser = new JsonParser();
        assertEquals(parser.parse(new Gson().toJson(new Greeting(echoResult))), parser.parse(helloJSON));
    }

    @Test
    public void testStoreGreeting() {
        Greeting greeting = new Greeting("");
        Entity<Greeting> entity = Entity.entity(greeting, MediaType.APPLICATION_JSON_TYPE);

        assertEquals(204, target("rest/hellojersey/hello/store/newgreeting").request().post(entity).getStatus());
    }
}