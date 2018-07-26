package com.oocl.leader.controllers;


import com.oocl.leader.controllers.DTO.LeaderDTO;
import com.oocl.leader.entities.Leader;
import com.oocl.leader.repositories.LeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/leaders")
public class LeaderController {

    private LeaderRepository leaderRepository;

    @Autowired
    public  LeaderController(LeaderRepository leaderRepository){
        this.leaderRepository = leaderRepository;
    }

    @Transactional
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public LeaderDTO saveLeader(@RequestBody Leader leader){
        return new LeaderDTO(leaderRepository.save(leader));
    }
}
