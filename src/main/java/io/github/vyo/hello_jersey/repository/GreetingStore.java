package io.github.vyo.hello_jersey.repository;

import io.github.vyo.hello_jersey.entity.Greeting;

import javax.inject.Singleton;
import java.util.HashMap;

/**
 * Created by Manuel Weidmann on 03.03.2015.
 */
@Singleton
public class GreetingStore {

    private HashMap<String, Greeting> greetings;

    private static GreetingStore instance;

    public static GreetingStore getInstance() {
        if (instance == null) {
            instance = new GreetingStore();
        }
        return instance;
    }

    private GreetingStore() {
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
