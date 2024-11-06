package com.kuzmins.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@MappedSuperclass
public abstract class BasicEntity implements Printable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    public BasicEntity(){
        this.id = UUID.randomUUID();;
    }

}
