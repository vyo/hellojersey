package io.github.vyo.hello_jersey.rest;

import io.github.vyo.hello_jersey.entity.Greeting;
import io.github.vyo.hello_jersey.repository.GreetingStore;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.ws.rs.core.Application;

import static org.junit.Assert.assertEquals;

public class HelloJerseyJerseyTest extends JerseyTest {

    @Override
    protected Application configure() {
        GreetingStore greetingStore = Mockito.mock(GreetingStore.class);
        Mockito.when(greetingStore.findGreeting(Mockito.anyString())).thenAnswer(new Answer<Greeting>() {
            @Override
            public Greeting answer(InvocationOnMock invocation)
                    throws Throwable {
                String name = (String) invocation.getArguments()[0];
                return new Greeting("Hello, I am a mock response. (" + name + ")");
            }

        });

        ResourceConfig resourceConfig = new ResourceConfig(HelloJersey.class);
        return resourceConfig;
    }

    @Test
    public void testHelloObject() {
        final String hello = target("rest/hellojersey/hello").request().get(Greeting.class).getMessage();
        assertEquals("Hello Jersey!", hello);
    }

    @Test
    public void testHelloJSON() {
        final String hello = target("rest/hellojersey/hello").request().get().readEntity(String.class);
        assertEquals("{\"message\":\"Hello Jersey!\"}", hello);
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
        String echoResultJSON = "{\"message\":\"" + new StringBuilder(echoString).reverse().toString() + "\"}";

        final String helloJSON = target("rest/hellojersey/echo/" + echoString).request().get().readEntity(String
                .class);
        assertEquals(echoResultJSON, helloJSON);
    }
}