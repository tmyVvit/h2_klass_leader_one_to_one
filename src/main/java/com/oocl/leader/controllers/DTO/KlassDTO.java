package com.oocl.leader.controllers.DTO;

import com.oocl.leader.entities.Klass;

import java.time.ZonedDateTime;

public class KlassDTO {
    private final Long id;
    private final String name;
    private final ZonedDateTime create_date;
    private Long leaderId = null;

    public KlassDTO(Klass klass){
        id = klass.getId();
        create_date = klass.getCreate_date();
        name = klass.getName();
        if(klass.getLeader() != null)
            leaderId = klass.getLeader().getId();
    }
    public Long getId() {
        return id;
    }

    public ZonedDateTime getCreate_date() {
        return create_date;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public String getName() {
        return name;
    }
}
