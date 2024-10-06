package model;

import java.util.UUID;

public abstract class BasicEntity implements Printable {
    protected UUID id = UUID.randomUUID();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
