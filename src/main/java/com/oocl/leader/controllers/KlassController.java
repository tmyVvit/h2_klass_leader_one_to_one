package com.oocl.leader.controllers;


import com.oocl.leader.controllers.DTO.KlassDTO;
import com.oocl.leader.entities.Klass;
import com.oocl.leader.repositories.KlassRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/klasses")
public class KlassController {

    private KlassRepository klassRepository;

    public KlassController(KlassRepository klassRepository){
        this.klassRepository = klassRepository;
    }

    @Transactional
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public KlassDTO saveKlass(@RequestBody Klass klass){
        klassRepository.save(klass);
        return new KlassDTO(klass);
    }
}
