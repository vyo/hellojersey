package io.github.vyo.hello_jersey.application;

import io.github.vyo.hello_jersey.repository.GreetingStore;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Created by Manuel Weidmann on 03.03.2015.
 */
public class Binder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(GreetingStore.class).to(GreetingStore.class);
    }
}
