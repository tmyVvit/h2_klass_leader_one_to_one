package com.oocl.leader.controllers.DTO;

import com.oocl.leader.entities.Klass;
import com.oocl.leader.entities.Leader;

public class LeaderDTO {
    private final Long id;
    private final String name;
    private Long klassId = null ;

    public LeaderDTO(Leader leader){
        id = leader.getId();
        name = leader.getName();
        if(leader.getKlass()!=null)
            klassId = leader.getKlass().getId();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getKlassId() {
        return klassId;
    }

}
