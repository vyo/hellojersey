package io.github.vyo.hello_jersey.rest;

import io.github.vyo.hello_jersey.entity.Greeting;
import io.github.vyo.hello_jersey.repository.GreetingStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Created by Manuel Weidmann on 03.03.2015.
 */
public class HelloJerseyUnitTest {

    HelloJersey helloJersey;

    @Before
    public void setUp() {
        helloJersey = new HelloJersey();
        GreetingStore greetingStore = Mockito.mock(GreetingStore.class);
        Mockito.when(greetingStore.findGreeting(Mockito.anyString())).thenAnswer(invocation -> {
            String name = (String) invocation.getArguments()[0];

            if (name.equals("hawaii")) {
                return new Greeting("Aloha!");
            } else {
                return new Greeting("Hello, I am a mock response.");
            }
        });
        Mockito.when(greetingStore.defaultGreeting()).thenAnswer(invocation -> new Greeting("Hello mock Jersey."));
        helloJersey.setGreetingStore(greetingStore);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testHello() {
        assertEquals(new Greeting("Hello mock Jersey.").getMessage(), helloJersey.getGreeting().getMessage());
    }

    @Test
    public void testHelloEcho() {
        assertEquals(new Greeting(new StringBuilder("Hello, I am a mock response.").reverse().toString())
                        .getMessage(),
                helloJersey.getEcho
                        ("Hello, I am a mock response.").getMessage());
    }

    @Test
    public void testRetrieveStoredGreeting() {
        assertEquals(new Greeting("Hello, I am a mock response.").getMessage(), helloJersey.retrieveGreeting
                ("anything").getMessage());
    }

    @Test
    public void testStoreGreeting() {
        Greeting greeting = new Greeting("Aloha!");
        helloJersey.storeGreeting("hawaii", greeting);
        assertEquals(helloJersey.retrieveGreeting("hawaii").getMessage(), greeting.getMessage());
    }
}
