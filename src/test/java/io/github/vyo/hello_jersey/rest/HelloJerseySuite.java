package io.github.vyo.hello_jersey.rest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Manuel Weidmann on 05.03.2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({HelloJerseyUnitTest.class, HelloJerseyRestAssuredTest.class, HelloJerseyJerseyTest.class})
public class HelloJerseySuite {
}
