package com.mateuszskocz.server.APBFRANCE.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Mati on 26.07.2017.
 */
@Document
public class Recipient {

    @Id
    public String id;

    public String email;

    public Recipient(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public Recipient() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
