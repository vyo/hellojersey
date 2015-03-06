package io.github.vyo.hellojersey.rest;

import io.github.vyo.hellojersey.entity.Greeting;
import io.github.vyo.hellojersey.repository.GreetingRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

/**
 * Created by Manuel Weidmann on 03.03.2015.
 */
public class HelloJerseyUnitTest {

    @Mock
    GreetingRepository greetingRepository;

    @InjectMocks
    HelloJersey helloJersey;

    @Before
    public void setUp() {
        helloJersey = new HelloJersey();
        MockitoAnnotations.initMocks(this);

        Mockito.when(greetingRepository.findGreeting(Mockito.anyString())).thenAnswer(invocation -> {
            String name = (String) invocation.getArguments()[0];

            if (name.equals("hawaii")) {
                return new Greeting("Aloha!");
            } else {
                return new Greeting("Hello, I am a mock response.");
            }
        });
        Mockito.when(greetingRepository.defaultGreeting()).thenAnswer(invocation -> new Greeting("Hello mock Jersey."));

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
