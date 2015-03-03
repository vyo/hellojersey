package io.github.vyo.hello_jersey.entity;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by Manuel Weidmann on 03.03.2015.
 */
@ApiModel(value = "A simple greeting; nothing out of the ordinary.")
public class Greeting {

    @ApiModelProperty(value = "The greeting's actual content.", required = true)
    private String message;

    public Greeting() {
        message = "How rude!";
    }

    public Greeting(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        return message.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Greeting && object.hashCode() == this.hashCode()) {
            return true;
        } else {
            return false;
        }
    }
}
