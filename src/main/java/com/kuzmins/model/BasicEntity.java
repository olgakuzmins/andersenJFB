package com.kuzmins.model;



import com.kuzmins.service.Printable;

import java.util.UUID;

public abstract class BasicEntity implements Printable {

    protected UUID id;

    public BasicEntity(){
        this.id = UUID.randomUUID();;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
