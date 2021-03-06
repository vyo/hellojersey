package io.github.vyo.hellojersey.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Manuel Weidmann on 03.03.2015.
 */
@Entity
@Table(name = "GREETING")
@ApiModel(description = "A simple greeting; nothing out of the ordinary.")
public class Greeting {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @ApiModelProperty(hidden = true)
    private int id;

    @Column(name = "ALIAS")
    @ApiModelProperty(value = "An alias for this greeting.", required = false)
    private String alias;

    @Column(name = "MESSAGE", nullable = false)
    @ApiModelProperty(value = "The greeting's actual content.", required = true)
    private String message;

    public Greeting() {
        message = "How rude!";
    }

    public Greeting(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
