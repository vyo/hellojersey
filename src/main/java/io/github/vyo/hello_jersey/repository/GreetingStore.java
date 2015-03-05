package io.github.vyo.hello_jersey.repository;

import io.github.vyo.hello_jersey.entity.Greeting;

import javax.ws.rs.Path;
import java.util.HashMap;

/**
 * Created by Manuel Weidmann on 03.03.2015.
 */
@Path("greeting-store")
public class GreetingStore {

    private HashMap<String, Greeting> greetings;

    public GreetingStore() {
        greetings = new HashMap<>();
        greetings.put("default", new Greeting("Hello Jersey!"));
    }

    public void storeGreeting(String alias, Greeting greeting) {
        greetings.put(alias, greeting);
    }

    public Greeting findGreeting(String alias) throws GreetingStoreException {
        if (greetings.containsKey(alias)) {
            return greetings.get(alias);
        } else {
            throw new GreetingStoreException(alias);
        }
    }

    public Greeting defaultGreeting() {
        return greetings.get("default");
    }
}
