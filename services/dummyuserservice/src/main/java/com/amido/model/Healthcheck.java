package com.amido.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class Healthcheck {

    public Healthcheck(String message) {
        this.message = message;
    }

    @NotNull
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
