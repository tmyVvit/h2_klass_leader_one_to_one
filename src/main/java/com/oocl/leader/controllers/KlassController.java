package com.oocl.leader.controllers;


import com.oocl.leader.controllers.DTO.KlassDTO;
import com.oocl.leader.entities.Klass;
import com.oocl.leader.entities.Leader;
import com.oocl.leader.exceptions.BadRequestException;
import com.oocl.leader.exceptions.ResourceNotFoundException;
import com.oocl.leader.repositories.KlassRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<KlassDTO> getAllKlasses(){
        List<KlassDTO> klassDTOS = new ArrayList<>();
        klassRepository.findAll().forEach(klass -> klassDTOS.add(new KlassDTO(klass)));
        return klassDTOS;
    }

    @Transactional
    @GetMapping(path = "/{klassID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public KlassDTO getKlassById(@PathVariable Long klassID){
        Klass klass = klassRepository.findById(klassID).orElseThrow(()->new ResourceNotFoundException("klass not found"));
        return new KlassDTO(klass);
    }

    @Transactional
    @PutMapping(path = "/{klassID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateKlassById(@PathVariable Long klassID, @RequestBody Klass klass){
        Klass k = klassRepository.findById(klassID).orElseThrow(()-> new BadRequestException("bad request"));
        klass.setId(klassID);
        klass.setLeader(k.getLeader());
        klassRepository.save(klass);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
