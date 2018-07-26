package com.oocl.leader.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Table(name = "leader")
@Entity
public class Leader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="klass_id")
    private Klass klass;

    public Leader(Long id, String name ,Klass klass) {
        this.id = id;
        this.name = name;
        this.klass = klass;
    }
    public Leader(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
